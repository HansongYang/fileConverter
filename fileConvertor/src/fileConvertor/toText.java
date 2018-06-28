package fileConvertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class toText{
	public void parseDoc(String filename){
	  	 POIFSFileSystem fs = null;
	     try {
		fs = new POIFSFileSystem(new FileInputStream(filename));
		HWPFDocument doc = new HWPFDocument(fs);
		WordExtractor we = new WordExtractor(doc);
		String text = we.getText();
		File fil = new File(filename.substring(0,filename.lastIndexOf("."))+  ".txt");
		Writer output = new BufferedWriter(new FileWriter(fil));
		output.write(text);
		output.close();
		} catch (Exception exep) {
			 System.out.println(exep);
		}
	}
		
	public void parseDocx(String filename){
		File file = null;
	    try {
		file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		XWPFDocument doc = new XWPFDocument(fis);
		XWPFWordExtractor ex = new XWPFWordExtractor(doc);
		String text = ex.getText();
	        File fil = new File(filename.substring(0,filename.lastIndexOf("."))+  ".txt");
	        Writer output = new BufferedWriter(new FileWriter(fil));
	        output.write(text);
	        output.close();
	
	    } catch (Exception exep) {
			System.out.println(exep);
	    }
	}
}
