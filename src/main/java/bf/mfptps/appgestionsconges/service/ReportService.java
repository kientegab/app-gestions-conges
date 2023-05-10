package bf.mfptps.appgestionsconges.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import fr.opensagres.xdocreport.core.XDocReportException;

public interface ReportService {
	
	public FileInputStream generateCongeMaladie(String refActe) throws IOException, XDocReportException, SQLException;
	

}
