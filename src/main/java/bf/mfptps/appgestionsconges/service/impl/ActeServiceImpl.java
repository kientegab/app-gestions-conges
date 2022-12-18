package bf.mfptps.appgestionsconges.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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
import bf.mfptps.appgestionsconges.repositories.AgentSoldeRepository;
import bf.mfptps.appgestionsconges.repositories.AgentStructureRepository;
import bf.mfptps.appgestionsconges.service.ActeService;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;
import bf.mfptps.appgestionsconges.service.mapper.ActeMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

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
    private final ActeMapper acteMapper;
    
    public ActeServiceImpl(ActeRepository acteRepository, ActeMapper acteMapper, AgentStructureRepository agentStructureRepository, AgentSoldeRepository agentSoldeRepository) {
        this.acteRepository = acteRepository;
		this.agentStructureRepository = agentStructureRepository;
		this.agentSoldeRepository = agentSoldeRepository;
		this.acteMapper = acteMapper;
    }

    @Override
    public ActeDTO create(ActeDTO acteDTO) {
        log.debug("Request to save Privilege : {}", acteDTO);
        Acte acte = acteMapper.toEntity(acteDTO);
        
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

		  try {
			InputStream in = new FileInputStream(new File(typeActe.getTemplateUri()));
			XWPFDocument document = new XWPFDocument(in);
			Set<Demande> demandes = acteTogenerate.getDemandes();
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
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
		return null;
	}

	
	private File generate_individual_acte(XWPFDocument document,  Acte acte) {
		 
		 if( !document.getTables().isEmpty()) {
			 throw new CustomException("Type de document exemple non definie pour un acte individuel");
		 }
		 
		 String pattern =  "dd MMMMM yyyy HH:mm:ss.SSSZ";
		 SimpleDateFormat simpleDateFormat =  new SimpleDateFormat(pattern, new Locale("fr", "FR"));
		 /**
		  * $ENTETE_MINISTERE$
		  * $REFERENCE_ACTE$
		  * $DATE_ACTE$
		  * $TOTAL_HEURES$
		  * $NOM_PRENOM_AGENT$
		  * $MATRICULE_AGENT$
		  * $FONCTION_AGENT$
		  * $STRUTURE_AGENT$
		  * $DATE_DEBUT$
		  * $DATE_FIN$
		  * $MOTIF_ABSENCE$
		  * $SOLDE_PRIS$
		  * $SOLDE_RESTE$
		  * $ANNEE$
		  * $RESPONSABLE_ACTE$
		  * $AMPLIATION$
		  * $TITRE_RESPONSABLE$
		  */
		 Set<Demande> demandes = acte.getDemandes();
		 if(demandes.size()>0) {
			 throw new CustomException("Le nombre( "+ demandes.size() + ") de demande n'est pas supporte pour ce type d'acte.");
		 }
		 Demande demande = demandes.iterator().next();
		 Agent agent =  demande.getAgent();
		 
		 AgentStructure agentstruct = agentStructureRepository.findOneByAgentMatriculeOrAgentEmail(agent.getMatricule(), agent.getEmail());
		 
		 AgentSolde agentSolde = agentSoldeRepository.findUserSoldeByYear(agent.getMatricule(), AppUtil.getCurrentYear(), demande.getTypeDemande().getCode())
				 .orElseThrow(()-> new CustomException("Echec lors de la recuperation du solde restant de l'agent"));
		 
		 if(null == agentstruct) {
			 throw new CustomException("Failed to get the structure of agent");
		 }
		 
		 List<XWPFParagraph> paragraphs=document.getParagraphs();
		 
		 for(XWPFParagraph paragraph : paragraphs) {
			 for (XWPFRun run : paragraph.getRuns()) {
				 String text = run.getText(0);
				 if (text != null) {
					 if(text.contains("$ENTETE_MINISTERE$")){
						 text = text.replace("$ENTETE_MINISTERE$", acte.getEnteteMinistere());
					     run.setText(text, 0);
					 }else if(text.contains("$REFERENCE_ACTE$")) {
						 text = text.replace("$REFERENCE_ACTE$", acte.getReference());
					     run.setText(text, 0);
					 }else if(text.contains("$DATE_ACTE$")) {
						  
						 text = text.replace("$DATE_ACTE$", simpleDateFormat.format(new Date()));
					     run.setText(text, 0);
					 }else if(text.contains("$TOTAL_HEURES$")) {
						 Long hours = demande.getDureeAbsence()*24;
						 text = text.replace("$TOTAL_HEURES$", ""+hours);
					     run.setText(text, 0);
					 }else if(text.contains("$NOM_PRENOM_AGENT$")) {
						 String nomPrenom = "" + agent.getNom() + StringUtils.hasText(agent.getNomJeuneFille()) != null ? "/"+ agent.getNomJeuneFille():"" + " " + agent.getPrenom();  				 
						 text = text.replace("$NOM_PRENOM_AGENT$", nomPrenom);
					     run.setText(text, 0);
					 }else if(text.contains("$MATRICULE_AGENT$")) {
						 text = text.replace("$MATRICULE_AGENT$", agent.getMatricule());
					     run.setText(text, 0);
					 }else if(text.contains("$FONCTION_AGENT$")) {
						 text = text.replace("$FONCTION_AGENT$", agent.getCorps().getLibelleCorps());
					     run.setText(text, 0);
					 }else if(text.contains("$DATE_DEBUT$")) {
						 text = text.replace("$DATE_DEBUT$", simpleDateFormat.format(demande.getPeriodeDebut()));
					     run.setText(text, 0);
					 }else if(text.contains("$DATE_FIN$")) {
						 text = text.replace("$DATE_FIN$", simpleDateFormat.format(demande.getPeriodeFin()));
					     run.setText(text, 0);
					 }else if(text.contains("$MOTIF_ABSENCE$")) {
						 String motifAbsence = null!=  demande.getMotifAbsence() ?  demande.getMotifAbsence().getLibelle(): "";
						 text = text.replace("$MOTIF_ABSENCE$", motifAbsence);
					     run.setText(text, 0);
					 }else if(text.contains("$SOLDE_PRIS$")) {
						 long soldePris = demande.getTypeDemande().getSoldeAnnuel() - agentSolde.getSoldeRestant();
						 text = text.replace("$SOLDE_PRIS$", ""+soldePris);
					     run.setText(text, 0);
					 }else if(text.contains("$SOLDE_RESTE$")) {
						 text = text.replace("$SOLDE_RESTE$", ""+agentSolde.getSoldeRestant());
					     run.setText(text, 0);
					 }else if(text.contains("$ANNEE$")) {
						 text = text.replace("$ANNEE$", acte.getAnnee());
					     run.setText(text, 0);
					 }else if(text.contains("$RESPONSABLE_ACTE$")) {
						 text = text.replace("$RESPONSABLE_ACTE$", acte.getNomPrenomCreator());
					     run.setText(text, 0);
					 }else if(text.contains("$TITRE_RESPONSABLE$")) {
						 text = text.replace("$TITRE_RESPONSABLE$", acte.getTitreCreator());
					     run.setText(text, 0);
					 }else if(text.contains("$AMPLIATION$")) {
						 text = text.replace("$AMPLIATION$", acte.getAmpliation());
					     run.setText(text, 0);
					 }
				 }
			 }
		 }
		 

		 
		 PdfOptions options = PdfOptions.create();
		
		 try {
			File resultFile = File.createTempFile("Acte_" +acte.getReference() , ".pdf");
			OutputStream out = new FileOutputStream(resultFile);
			PdfConverter.getInstance().convert(document, out, options);
			 document.close();
			  out.close();
			  
			  return resultFile;
		} catch (XWPFConverterException e) {
			log.error("Failed to generate acte from  XWPFConverterException format", e);
			throw new CustomException("Failed to generate acte from XWPFConverterException format");
		} catch (IOException e) {
			log.error("Failed to generate acte in pdf format", e);
			throw new CustomException("Failed to generate acte in pdf format");
		}
	}

	private File generate_groupe_acte(XWPFDocument document,Acte acte) {
		List<XWPFParagraph> paragraphs=document.getParagraphs();
		 if( document.getTables().isEmpty()) {
			 throw new CustomException("Type de document exemple non definie pour un acte de groupe");
		 }
		 
		 
		return null;
	}

}
