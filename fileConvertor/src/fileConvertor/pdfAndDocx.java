package fileConvertor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class pdfAndDocx {
	String name = null;
	
	public void generateDocxFromPDF(String filename) throws IOException {
		XWPFDocument doc = new XWPFDocument();
		String pdf = filename;
		File file = new File(filename);
		PdfReader reader = new PdfReader(pdf);
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);

		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
			String text = strategy.getResultantText();
			XWPFParagraph p = doc.createParagraph();
			XWPFRun run = p.createRun();
			run.setText(text);
			run.addBreak(BreakType.PAGE);
		}

		FileOutputStream out = new FileOutputStream(filename.substring(0,filename.lastIndexOf(".")) + ".docx");
		doc.write(out);
		out.close();
		reader.close();
	}
	
    public String readDocxFile(String fileName) {
        String output = "";
        name = fileName;
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph para : paragraphs) {
                output = output + "\n" + para.getText() + "\n";
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

    public void writePdfFile(String output) throws FileNotFoundException, DocumentException {
    	File file = new File(name.substring(0,name.lastIndexOf(".")).replaceFirst("[.][^.]+$", "") + ".pdf");
        FileOutputStream fileout = new FileOutputStream(file);
        Document document = new Document();
        PdfWriter.getInstance(document, fileout);
        document.addAuthor("hY");
        document.addTitle("My Converted PDF");
        document.open();
        String[] splitter = output.split("\\n");

        for (int i = 0; i < splitter.length; i++) {
            Chunk chunk = new Chunk(splitter[i]);
            Font font = new Font();
            font.setStyle(Font.UNDERLINE);
            font.setStyle(Font.ITALIC);
            chunk.setFont(font);
            document.add(chunk);
            Paragraph paragraph = new Paragraph();
            paragraph.add("");
            document.add(paragraph);
        }
        document.close();
   }
}
