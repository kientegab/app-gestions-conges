package bf.mfptps.appgestionsconges.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.common.IOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;

import bf.mfptps.appgestionsconges.service.ReportService;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.core.io.IOUtils;

@RestController
@RequestMapping("/api")
public class ReportRessource {
	
	
	private ReportService service;
	
	private Logger log=LoggerFactory.getLogger(ReportRessource.class);
	
	
	public ReportRessource(ReportService service) {
		
		this.service = service;
	}



	@GetMapping(value = "/conge-report/{refAct}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getCongeMaladie(@PathVariable("refAct") String refAct, HttpServletResponse reponse) throws IOException, XDocReportException, SQLException
	{
		reponse.addHeader("Content-disposition", "attachment;filename=conge.pdf");
		reponse.setContentType("application/octet-stream");
		service.generateCongeMaladie(refAct);
		InputStream inputStream=new FileInputStream(new File("CongeReport.docx"));
		if (service.generateCongeMaladie(refAct)==null) {
			log.info("Le congé que vous rechercher n'existe pas");
		}else
		{
			IOUtils.copy(service.generateCongeMaladie(refAct),reponse.getOutputStream());
		}
		
	}

}
