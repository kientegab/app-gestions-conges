package bf.mfptps.appgestionsconges.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import javax.xml.namespace.QName;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.AgentSolde;
import bf.mfptps.appgestionsconges.entities.AgentStructure;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.Privilege;
import bf.mfptps.appgestionsconges.entities.TypeActe;
import bf.mfptps.appgestionsconges.repositories.ActeRepository;
import bf.mfptps.appgestionsconges.repositories.AgentRepository;
import bf.mfptps.appgestionsconges.repositories.AgentSoldeRepository;
import bf.mfptps.appgestionsconges.repositories.AgentStructureRepository;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.repositories.TypeActeRepository;
import bf.mfptps.appgestionsconges.service.ActeService;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;
import bf.mfptps.appgestionsconges.service.dto.ResponseDto;
import bf.mfptps.appgestionsconges.service.mapper.ActeMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.utils.ResponseMessage;
import bf.mfptps.appgestionsconges.utils.WordReplacer;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import net.minidev.json.JSONObject;

/**
 * Service Implementation for managing {@link Privilege}.
 */
@Service
@Transactional
public class ActeServiceImpl implements ActeService {

	private final Logger log = LoggerFactory.getLogger(ActeServiceImpl.class);

	private final ActeRepository acteRepository;
	private final AgentStructureRepository agentStructureRepository;
	private final AgentSoldeRepository    agentSoldeRepository;
	private final AgentRepository      agentRepository;
	private final DemandeRepository    demandeRepository;
	private final TypeActeRepository  typeActeRepository;
	private final ActeMapper acteMapper;

	public ActeServiceImpl(ActeRepository acteRepository, ActeMapper acteMapper, AgentStructureRepository agentStructureRepository, AgentSoldeRepository agentSoldeRepository, DemandeRepository demandeRepository, TypeActeRepository typeActeRepository, AgentRepository agentRepository) {
		this.acteRepository = acteRepository;
		this.agentStructureRepository = agentStructureRepository;
		this.agentSoldeRepository = agentSoldeRepository;
		this.agentRepository = agentRepository;
		this.demandeRepository = demandeRepository;
		this.typeActeRepository = typeActeRepository;
		this.acteMapper = acteMapper;
	}

	@Override
	public ActeDTO create(ActeDTO acteDTO) {
		log.debug("Request to save Privilege : {}", acteDTO);
		Acte acte = acteMapper.toEntity(acteDTO);
		if(null==acte.getTypeActe()) {
			log.error("Type acte ne peut pas etre null");
			throw new CustomException("Type acte ne peut pas etre null");
		}
		if(null==acte.getDemandes() || acte.getDemandes().isEmpty()) {
			log.error("Aucune demande selectionnee pour la create de l'acte");
			throw new CustomException("Aucune demande selectionnee pour la create de l'acte");
		}

		TypeActe typeActe = typeActeRepository.findByReference(acte.getTypeActe().getReference()).orElseThrow(() -> new CustomException("Le type d'acte n'existe pas ou a ete supprime"));
		Set<Demande> acteDemandes = new HashSet<Demande>();
		acte.getDemandes().stream().forEach((demande) -> {
			Demande demandeFromDb = demandeRepository.findByNumeroDemande(demande.getNumeroDemande())
					.orElseThrow(()-> new CustomException("La demande portant le numero ["+ demande.getNumeroDemande() +"] n'existe pas ou a ete supprime"));
			demande.setActe(acte);
			acteDemandes.add(demandeFromDb);
		});
		acte.setDemandes(acteDemandes);
		acte.setTypeActe(typeActe);
		return acteMapper.toDto(acteRepository.save(acte));
	}


	@Override
	public ActeDTO update(ActeDTO acteDTO) {
		log.debug("Request to update acte : {}", acteDTO);
		Acte acte = acteMapper.toEntity(acteDTO);
		if(null== acte.getId() || !acteRepository.existsById(acte.getId())) {
			throw new CustomException("L'acte en cours de mise a jour, n'existe pas ou a ete supprime");
		}
		return acteMapper.toDto(acteRepository.save(acte));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Acte> findAll(Pageable pageable) {
		log.debug("Request to get all Privilege");
		return acteRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Acte> findOne(Long id) {
		log.debug("Request to get acte : {}", id);
		return acteRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		log.debug("Request to delete acte : {}", id);
		acteRepository.deleteById(id);
	}


	@Override
	public File generateActe(String referenceActe) {
		if(!StringUtils.hasText(referenceActe)) {
			throw new CustomException("La reference de l'acte ne peux pas etre null");
		}

		Acte acteTogenerate = acteRepository.findByReference(referenceActe).orElseThrow(() -> new CustomException("L'acte portant la reference ["+ referenceActe+ "] n'existe pas ou a ete supprime"));

		TypeActe typeActe = acteTogenerate.getTypeActe();
		if(null == typeActe) {
			throw new CustomException("Le type de l'acte en cours de generation n'a pas ete definie");
		}

		if(null == acteTogenerate.getDemandes() || acteTogenerate.getDemandes().isEmpty()) {
			throw new CustomException("Demande non definie dans l'acte ["+ referenceActe+"]");
		}

		try {
			log.debug("TEMPLATE URI {}", typeActe.getTemplateUri());
			InputStream in = new FileInputStream(new File(typeActe.getTemplateUri()));
			XWPFDocument document = new XWPFDocument(in);

			File resultFile = null;
			switch (typeActe.getPorteActe()) {
				case INDIVIDUEL:
					resultFile = generate_individual_acte(document, acteTogenerate);
					break;

				case GROUPE:
					resultFile = generate_groupe_acte(document, acteTogenerate);
				default:
					throw new CustomException("Portee du type d'acte non reconnue");
			}


			return resultFile;
		} catch (Exception e) {
			e.printStackTrace();

			log.debug("TEMPLATE URI {}", typeActe.getTemplateUri());
			throw new CustomException("Echec lors de la generation du fichier pdf de l'acte ["+ acteTogenerate.getReference()+"]");
		}

	}


	private File generate_individual_acte(XWPFDocument document,  Acte acte) throws XmlException {

		/*if( !document.getTables().isEmpty()) {
			throw new CustomException("Type de document exemple non definie pour un acte individuel");
		}*/

		String pattern =  "dd MMMMM yyyy";
		SimpleDateFormat simpleDateFormat =  new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		

		List<Demande> demandes = new ArrayList<>(acte.getDemandes());
		if(demandes.size()> 1) {
			throw new CustomException("Le nombre( "+ demandes.size() + ") de demande n'est pas supporte pour ce type d'acte.");
		}
		Demande demande = demandes.get(0);
		Agent agent =  demande.getAgent();

		AgentStructure agentstruct = agentStructureRepository.findOneByAgentMatriculeOrAgentEmail(agent.getMatricule(), agent.getEmail());

		AgentSolde agentSolde = agentSoldeRepository.findUserSoldeByYear(agent.getMatricule(), AppUtil.getCurrentYear(), demande.getTypeDemande().getCode())
				.orElseThrow(()-> new CustomException("Echec lors de la recuperation du solde restant de l'agent"));

		String qrCodeResponsableAgent = "";
		Agent responsableAgent = agentRepository.findAgentSystemByMatricule(agent.getMatricule());
		if(null!= responsableAgent) {
			String nomPrenom = "" + responsableAgent.getNom() + (StringUtils.hasText(responsableAgent.getNomJeuneFille())  ? "/"+ responsableAgent.getNomJeuneFille():"") + " " + responsableAgent.getPrenom();  
			qrCodeResponsableAgent = nomPrenom +"\n" + responsableAgent.getQualite();
		}
		
		
		if(null == agentstruct) {
			throw new CustomException("Failed to get the structure of agent");
		}
		 JSONObject keysValues = new JSONObject();

		keysValues.put("$ENTETE_MINISTERE$", acte.getEnteteMinistere());
		keysValues.put("$REFERENCE_ACTE$", acte.getReference());
		keysValues.put("$DATE_ACTE$", simpleDateFormat.format(new Date()));
		long soldePris = demande.getTypeDemande().getSoldeAnnuel() - agentSolde.getSoldeRestant();
		keysValues.put("$SOLDEPRIS$", ""+soldePris);
		keysValues.put("$SOLDERESTE$", ""+agentSolde.getSoldeRestant());
		Long hours = demande.getDureeAbsence()*24;
		keysValues.put("$TOTALHEURES$", ""+hours);
		String nomPrenom = "" + agent.getNom() + (StringUtils.hasText(agent.getNomJeuneFille())  ? "/"+ agent.getNomJeuneFille():"") + " " + agent.getPrenom();  				 
		keysValues.put("$NOM_PRENOM_AGENT$", nomPrenom);
		keysValues.put("$MATRICULE_AGENT$", agent.getMatricule());
		keysValues.put("$FONCTION_AGENT$", agent.getQualite());
		keysValues.put("$DATE_DEBUT$", simpleDateFormat.format(demande.getPeriodeDebut()));
		keysValues.put("$DATE_FIN$", simpleDateFormat.format(demande.getPeriodeFin()));
		String motifAbsence = null!=  demande.getMotifAbsence() ?  demande.getMotifAbsence().getLibelle(): "";
		keysValues.put("$MOTIF_ABSENCE$", motifAbsence);
		keysValues.put("$ANNEE$", acte.getAnnee());
		keysValues.put("$RESPONSABLE$", acte.getNomPrenomCreator());
		keysValues.put("$TITRE_RESPONSABLE$", acte.getTitreCreator());
		keysValues.put("$AMPLIATION$", acte.getAmpliation());
		String structureAgent = agentstruct.getStructure()!=null ? agentstruct.getStructure().getLibelle():"";
		keysValues.put("$STRUCTURE_AGENT$", structureAgent);
				
		try {
			return new WordReplacer().processDOCXWordReplace(acte.getTypeActe().getTemplateUri(), acte.getReference(), qrCodeResponsableAgent, keysValues);
		} catch (Exception e) {
			log.error("Failed to generate acte doc format", e);
			throw new CustomException("Failed to generate acte from XWPFConverterException format");
		}
	}
	
	 private void printContentsOfTextBox(XWPFParagraph paragraph) {

	        XmlObject[] textBoxObjects =  paragraph.getCTP().selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' "
	        		+ " declare namespace wps='http://schemas.microsoft.com/office/word/2010/wordprocessingShape' "
	        		+ " declare namespace v='urn:schemas-microsoft-com:vml' .//*/wps:txbx/w:txbxContent | .//*/v:textbox/w:txbxContent");

	        for (int i =0; i < textBoxObjects.length; i++) {
	            XWPFParagraph embeddedPara = null;
	            try {
	            XmlObject[] paraObjects = textBoxObjects[i].
	                selectChildren(
	                new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "p"));

	            for (int j=0; j<paraObjects.length; j++) {
	                embeddedPara = new XWPFParagraph(
	                    CTP.Factory.parse(paraObjects[j].xmlText()), paragraph.getBody());
	                //Here you have your paragraph; 
	                System.out.println(embeddedPara.getText());
	                log.error("PARAGRA TEXT ====> {}", embeddedPara.getText());
	            } 

	            } catch (XmlException e) {
	            //handle
	            }
	        }

	     } 
	 
	 
	 
	 private  List<CTDrawing> getAllDrawings(XWPFRun run) throws Exception {
		  CTR ctR = run.getCTR();
		  XmlCursor cursor = ctR.newCursor();
		  cursor.selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//*/w:drawing");
		  List<CTDrawing> drawings = new ArrayList<CTDrawing>();
		  while (cursor.hasNextSelection()) {
		   cursor.toNextSelection();
		   XmlObject obj = cursor.getObject();
		   CTDrawing drawing = CTDrawing.Factory.parse(obj.newInputStream());
		   drawings.add(drawing);
		  }
		  return drawings;
	}
	 
	 
	 private  CTTxbxContent getTextBoxContent(CTDrawing drawing) throws Exception {
		  XmlCursor cursor = drawing.newCursor();
		  cursor.selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//*/w:txbxContent");
		  List<CTTxbxContent> txbxContents = new ArrayList<CTTxbxContent>();
		  while (cursor.hasNextSelection()) {
		   cursor.toNextSelection();
		   XmlObject obj = cursor.getObject();
		   CTTxbxContent txbxContent = CTTxbxContent.Factory.parse(obj.newInputStream());
		   txbxContents.add(txbxContent);
		   break;
		  }
		  CTTxbxContent txbxContent = null;
		  if (txbxContents.size() > 0) {
		   txbxContent = txbxContents.get(0);
		  }
		  return txbxContent;
		 }
	 

	private void textReplaceProcess(Acte acte, SimpleDateFormat simpleDateFormat, Demande demande, Agent agent,  AgentStructure agentstruct, 
			AgentSolde agentSolde, XWPFRun run, String text) {
		if (text != null) {
			SimpleDateFormat df =  new SimpleDateFormat("dd/MM/yyyy", new Locale("fr", "FR"));
			if(text.contains("$ENTETE_MINISTERE$")){
				text = text.replace("$ENTETE_MINISTERE$", acte.getEnteteMinistere());
				run.setText(text, 0);
			} if(text.contains("$REFERENCE_ACTE$")) {
				text = text.replace("$REFERENCE_ACTE$", acte.getReference());
				run.setText(text, 0);
			} if(text.contains("$SOLDEPRIS$")) {
				long soldePris = demande.getTypeDemande().getSoldeAnnuel() - agentSolde.getSoldeRestant();
				text = text.replace("$SOLDEPRIS$", ""+soldePris);
				run.setText(text, 0);
			} if(text.contains("$SOLDERESTE$")) {
				text = text.replace("$SOLDERESTE$", ""+agentSolde.getSoldeRestant());
				run.setText(text, 0);
			}  if(text.contains("$DATE_ACTE$")) {

				text = text.replace("$DATE_ACTE$", simpleDateFormat.format(new Date()));
				run.setText(text, 0);
			} if(text.contains("$TOTALHEURES$")) {
				Long hours = demande.getDureeAbsence()*24;
				text = text.replace("$TOTALHEURES$", ""+hours);
				run.setText(text, 0);
			} if(text.contains("$NOM_PRENOM_AGENT$")) {
				String nomPrenom = "" + agent.getNom() + (StringUtils.hasText(agent.getNomJeuneFille()) ? "/"+ agent.getNomJeuneFille():"") + " " + agent.getPrenom();  				 
				text = text.replace("$NOM_PRENOM_AGENT$", nomPrenom);
				run.setText(text, 0);
			} if(text.contains("$MATRICULE_AGENT$")) {
				text = text.replace("$MATRICULE_AGENT$", agent.getMatricule());
				run.setText(text, 0);
			} if(text.contains("$FONCTION_AGENT$")) {
				String corps = agent.getCorps()!=null ? agent.getCorps().getLibelleCorps(): "";
				text = text.replace("$FONCTION_AGENT$",corps);
				run.setText(text, 0);
			} if(text.contains("$DATE_DEBUT$")) {
				text = text.replace("$DATE_DEBUT$", df.format(demande.getPeriodeDebut()));
				run.setText(text, 0);
			} if(text.contains("$DATE_FIN$")) {
				text = text.replace("$DATE_FIN$", df.format(demande.getPeriodeFin()));
				run.setText(text, 0);
			} if(text.contains("$MOTIF_ABSENCE$")) {
				String motifAbsence = null!=  demande.getMotifAbsence() ?  demande.getMotifAbsence().getLibelle(): "";
				text = text.replace("$MOTIF_ABSENCE$", motifAbsence);
				run.setText(text, 0);
			} if(text.contains("$ANNEE$")) {
				text = text.replace("$ANNEE$", acte.getAnnee());
				run.setText(text, 0);
			} if(text.contains("$RESPONSABLE_ACTE$")) {
				text = text.replace("$RESPONSABLE_ACTE$", acte.getNomPrenomCreator());
				run.setText(text, 0);
			} if(text.contains("$TITRE_RESPONSABLE$")) {
				text = text.replace("$TITRE_RESPONSABLE$", acte.getTitreCreator());
				run.setText(text, 0);
			} if(text.contains("$AMPLIATION$")) {
				text = text.replace("$AMPLIATION$", acte.getAmpliation());
				run.setText(text, 0);
			} if(text.contains("$STRUCTURE_AGENT$")) {
				String structureAgent = agentstruct.getStructure()!=null ? agentstruct.getStructure().getLibelle():"";
				text = text.replace("$STRUCTURE_AGENT$", structureAgent);
				run.setText(text, 0);
			}
		}
	}

	private File generate_groupe_acte(XWPFDocument document,Acte acte) {
		List<XWPFParagraph> paragraphs=document.getParagraphs();
		if( document.getTables().isEmpty()) {
			throw new CustomException("Type de document exemple non definie pour un acte de groupe");
		}


		return null;
	}

	@Override
	public ResponseDto ListOfReferenceByAgentMatriculeService(String matricule, String type_demande) {
		try {
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setCode(200);
			responseMessage.setDetails("Recupération de la liste des reference acte en fonction du matricule et du motif absence");
			responseMessage.setMessage("Recuperation de la liste avec success");
			ResponseDto reponse=new ResponseDto();
			
			reponse.setRepMessage(responseMessage);
			
			reponse.setData(acteRepository.ListOfReferenceByAgentMatricule(matricule, type_demande).orElse(null));
			
			return reponse;
			
		} catch (Exception e) {
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setCode(200);
			responseMessage.setDetails(e.getCause().toString());
			responseMessage.setMessage(e.getMessage());
			ResponseDto reponse=new ResponseDto();
			reponse.setRepMessage(responseMessage);
			reponse.setData(null);
			
			return reponse;
			
		}
		
	}

	@Override
	public ResponseDto totalOfAbsenceInYearByMAtricule(String matricule) {
		try {
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setCode(200);
			responseMessage.setDetails("Nombre de jour d’autorisation contracté par un matricule et par une année");
			responseMessage.setMessage("Recuperation de la liste avec success");
			ResponseDto reponse=new ResponseDto();
			
			reponse.setRepMessage(responseMessage);
			
			reponse.setData(acteRepository.totalOfAbsenceInYear(matricule).orElse(null));
			
			return reponse;
			
		} catch (Exception e) {
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setCode(200);
			responseMessage.setDetails(e.getCause().toString());
			responseMessage.setMessage(e.getMessage());
			ResponseDto reponse=new ResponseDto();
			reponse.setRepMessage(responseMessage);
			reponse.setData(null);
			return reponse;
			
		}
	}

	@Override
	public ResponseDto totalOfAbsenceByTypeAndMAtriculeAndYear(String year,String type_demande) {
		try {
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setCode(200);
			responseMessage.setDetails("Nombre de jour de congé par type qui prend en paramètre un matricule et une année");
			responseMessage.setMessage("Recuperation de la liste avec success");
			ResponseDto reponse=new ResponseDto();
			reponse.setRepMessage(responseMessage);
			
			reponse.setData(acteRepository.totalOfAbsenceByTypeAndMAtriculeAndYear(year,type_demande).orElse(null));
			
			return reponse;
			
		} catch (Exception e) {
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setCode(200);
			responseMessage.setDetails(e.getCause().toString());
			responseMessage.setMessage(e.getMessage());
			ResponseDto reponse=new ResponseDto();
			reponse.setRepMessage(responseMessage);
			reponse.setData(null);
			return reponse;
			
		}
	}

	
	@Override
	public ResponseDto totalOfAbsenceByYeayeAndMAtriculeAndYear(String matricule) {
		// TODO Auto-generated method stub
		
		try {
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setCode(200);
			responseMessage.setDetails("Nombre de jour de congé annuelle qui prend en paramètre un matricule");
			responseMessage.setMessage("Recuperation de la liste avec success");
			ResponseDto reponse=new ResponseDto();
			reponse.setRepMessage(responseMessage);			
			reponse.setData(acteRepository.totalOfAbsenceByYeayeAndMAtriculeAndYear(matricule).orElse(null));
			
			return reponse;
			
		} catch (Exception e) {
			ResponseMessage responseMessage=new ResponseMessage();
			responseMessage.setCode(200);
			responseMessage.setDetails(e.getCause().toString());
			responseMessage.setMessage(e.getMessage());
			ResponseDto reponse=new ResponseDto();
			reponse.setRepMessage(responseMessage);
			reponse.setData(null);
			return reponse;
			
		}
	}

	

}
