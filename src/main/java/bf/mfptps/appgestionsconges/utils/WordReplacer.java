package bf.mfptps.appgestionsconges.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.PositionInParagraph;
import org.apache.poi.xwpf.usermodel.TextSegment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import net.minidev.json.JSONObject;

public class WordReplacer
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WordReplacer.class);

   

    public File processDOCXWordReplace(String templateNodeURI, String refActe, String qrCodeText, JSONObject keysValues) throws IOException
    {

        try
        {
        	File f = new File(templateNodeURI);
        	  if ( !f.isFile() || !f.exists())
              {
                  LOGGER.warn("File with URI [{}] does not exist", templateNodeURI);
                  throw new CustomException("Le ficher template pour la generation de l'acte n'existe pas dans le chemin [" + templateNodeURI+"]");
              }
        	InputStream in = new FileInputStream(f);
			XWPFDocument doc = new XWPFDocument(in);
        	
          

          //  String  filename        = "Acte_" +acte.getReference() , ".pdf";
         

            Iterator<String> keysItr = keysValues.keySet().iterator();
            while (keysItr.hasNext())
            {
                String key = keysItr.next();
                // on remplace dans les paragraph simple
                replaceInParagraphs(doc.getParagraphs(), key, keysValues.getAsString(key));

                replaceTableText(keysValues, doc, key);
                
                List<XWPFHeader> headers = doc.getHeaderList();
                for(XWPFHeader header : headers) {
                	 replaceInParagraphs(header.getParagraphs(), key, keysValues.getAsString(key));
                	for (XWPFTable tbl : header.getTables())
            		{
            		    for (XWPFTableRow row : tbl.getRows())
            		    {
            		        for (XWPFTableCell cell : row.getTableCells())
            		        {
            		            // on remplace dans les paragraph inclus dans des cellules
            		            replaceInParagraphs(cell.getParagraphs(), key, keysValues.getAsString(key));
            		        }
            		    }
            		}
                }
                
            }
            
          
            
			if(StringUtils.hasText(qrCodeText)){
				BufferedImage qrCodeImqge = generateQRCodeImage(qrCodeText);
				
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(qrCodeImqge, "jpeg", os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
				InputStream is = new ByteArrayInputStream(os.toByteArray());
				 XWPFParagraph title = doc.createParagraph();    
				    XWPFRun run = title.createRun();
				    title.setAlignment(ParagraphAlignment.RIGHT);
				    String imgFile = "encabezado.jpg";
				   //run.addBreak();
				    run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, imgFile, Units.toEMU(70), Units.toEMU(70)); // 200x200 pixels
				    is.close();
            }
            
            PdfOptions options =PdfOptions.create();
            File resultFile = File.createTempFile("Acte_" +refActe , ".pdf");
            OutputStream out = new FileOutputStream(resultFile);
            PdfConverter.getInstance().convert(doc, out, options);
            doc.close();
			out.close();
            return resultFile;
        }
        catch (java.lang.Exception e)
        {
            LOGGER.error("Exception:", e);
            throw new CustomException("Failed to generate Acte ["+ e.getMessage()+"]");
            
        }
    }

	private void replaceTableText(JSONObject keysValues, XWPFDocument doc, String key) {
		for (XWPFTable tbl : doc.getTables())
		{
		    for (XWPFTableRow row : tbl.getRows())
		    {
		        for (XWPFTableCell cell : row.getTableCells())
		        {
		            // on remplace dans les paragraph inclus dans des cellules
		            replaceInParagraphs(cell.getParagraphs(), key, keysValues.getAsString(key));
		        }
		    }
		}
	}

    private void replaceInParagraphs(List<XWPFParagraph> xwpfParagraphs, String key, String value)
    {
        for (XWPFParagraph p : xwpfParagraphs)
        {
            List<XWPFRun> runs  = p.getRuns();
            // on cherche le text dans le paragraph
            TextSegment  found = p.searchText(key, new PositionInParagraph());
            if (found != null)
            {
                // on parcourt les runs qui contiennent la chaine trouvé
                int beginRun = found.getBeginRun();
                for (int runPos = beginRun; runPos <= found.getEndRun(); runPos++ )
                {
                    if (runPos != beginRun)
                    {
                        // on met le texte du run courant dans le 1er run
                        runs.get(beginRun).setText(runs.get(beginRun).getText(beginRun) + runs.get(runPos).getText(0), 0);
                        // on vide le texte du run courant
                        runs.get(runPos).setText("", 0);
                    }
                }
                LOGGER.error("TESTT ---->{}, {}, {}", runs.get(beginRun).getText(0), key, value);
                runs = p.getRuns();
                if(null!=runs.get(beginRun).getText(0)) {
                runs.get(beginRun).setText(runs.get(beginRun).getText(0).replace(key, ""+value), 0);
                LOGGER.info("Runs content - {}", runs.get(beginRun).getText(0));
                }
              
            }
        }
    }
    
    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = 
          barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 70, 70);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
