package bf.mfptps.appgestionsconges.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.entities.Ampliation;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.repositories.ActeRepository;
import bf.mfptps.appgestionsconges.service.ReportService;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{
	
	private final Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	ActeRepository dao;
	
	
	@PersistenceContext
	EntityManager em;

	public ReportServiceImpl(ActeRepository dao) {
		super();
		this.dao = dao;
	}



	@Override
	public FileInputStream generateCongeMaladie(String refActe) throws IOException, XDocReportException {
		// TODO Auto-generated method stub
		
		Acte acte=dao.findByReference(refActe).orElse(null);
		
		Demande demande=acte.getDemandes().stream().findFirst().orElse(null);
				
		if (acte==null) {
			log.error("Les réferences de l'acte n'existe pas");
			return null;
		}else {
			InputStream in = new FileInputStream(new File("CongeReport.docx")); 
			
			IXDocReport congeReport= XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
		
			java.util.List<String>visas=new ArrayList<>();
			List<String>ampliation=new ArrayList<>();
			String verbiliage="";
			
			String ministere=acte.getEnteteMinistere();
			
			System.out.println(demande.getTypeDemande().getCode());
			
			if (demande.getTypeDemande().getCode().equalsIgnoreCase("CONGE_MATERNITE")) {
				verbiliage="de quatorze (14) semaines";
			}
			
			if (demande.getTypeDemande().getCode().equalsIgnoreCase("CONGE_MALADIE_LONGUE_DUREE")) {
				verbiliage="de courte durée";
			}
			
			if (demande.getTypeDemande().getCode().equalsIgnoreCase("CONGE_MALADIE_LONGUE_DUREE")) {
				
				verbiliage="de longue durée";
			}
			
			demande.getTypeDemande().getTypeVisas().forEach(item->{
				
				
				visas.add(item.getVisa().getLibelle());
			});
			
			demande.getTypeDemande().getAmpliation().forEach(item->{
				ampliation.add(item.getLibelle());
			});
			
			
			IContext context=congeReport.createContext();
			context.put("typeconge", demande.getTypeDemande().getLibelle());
			context.put("visas", visas);
			context.put("ampliations", ampliation);  
			context.put("articles", demande.getTypeDemande().getArticleTypeDemandes());
			context.put("nom", demande.getAgent().getNom());
			context.put("prenom", demande.getAgent().getPrenom());
			context.put("verbiliage", verbiliage);
			context.put("ministere", ministere);
			context.put("matricule", demande.getAgent().getMatricule());
			context.put("datedebut",new SimpleDateFormat("dd/MM/yyyy").format(demande.getPeriodeDebut()));
			context.put("datefin",new SimpleDateFormat("dd/MM/yyyy").format(demande.getPeriodeFin()));
			context.put("qualite", demande.getAgent().getQualite());
			context.put("libllestructure", demande.getAgent().getStructure().stream().findFirst().orElse(null).getLibelle());
			context.put("siglestructure", demande.getAgent().getStructure().stream().findFirst().orElse(null).getSigle());
			File file=new File("CongeReport1_"+new Date(0)+".pdf");
			OutputStream out=new FileOutputStream(file);
			Options option=Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
			
			congeReport.convert(context, option, out);
			
			
			
			return new FileInputStream(file);
			
		}
		
		
	}

}
