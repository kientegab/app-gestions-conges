package bf.mfptps.appgestionsconges.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import bf.mfptps.appgestionsconges.entities.Acte;
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
		
		Acte acte=dao.findByReference("2022_0004").orElse(null);
		
		Demande demande=acte.getDemandes().stream().findFirst().orElse(null);
				
		if (acte==null) {
			log.error("Les réferences de l'acte n'existe pas");
			return null;
		}else {
			InputStream in = new FileInputStream(new File("CongeReport.docx")); 
			
			IXDocReport congeReport= XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
		
			java.util.List<String>visas=new ArrayList<>();
			
			System.out.println(demande.getTypeDemande().getId());
			
			demande.getTypeDemande().getTypeVisas().forEach(item->{
				
				
				visas.add(demande.getTypeDemande().getLibelle());
			});
			
			IContext context=congeReport.createContext();
			context.put("typeconge", demande.getTypeDemande().getLibelle());
			context.put("visas", demande.getTypeDemande().getTypeVisas());
			context.put("articles", demande.getTypeDemande().getArticleTypeDemandes());
			context.put("nom", demande.getAgent().getNom());
			context.put("prenom", demande.getAgent().getPrenom());
			context.put("matricule", demande.getAgent().getMatricule());
			context.put("datedebut", demande.getPeriodeDebut());
			context.put("datefin", demande.getPeriodeFin());
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
