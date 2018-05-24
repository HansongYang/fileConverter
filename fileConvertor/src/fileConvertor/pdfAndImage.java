package fileConvertor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class pdfAndImage {
	
	public void generateImageFromPDF(String filename, String extension) throws IOException {
		File file = new File(filename);
		PDDocument document = PDDocument.load(new File(filename));
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		for (int page = 0; page < document.getNumberOfPages(); ++page) {
			BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
			ImageIOUtil.writeImage(bim, String.format(filename.substring(0,filename.lastIndexOf(".")) + "-%d.%s", page + 1, extension), 300);
		}
		document.close();
	}
	
	public void generatePDFFromImage(String filepath, String filename) throws IOException, BadElementException, DocumentException {
		Document document = new Document();
		String input = filepath;
		
		String output = filepath.substring(8, filepath.lastIndexOf("."))+ ".pdf";
		System.out.println(output);
		FileOutputStream fos = new FileOutputStream(output);
		PdfWriter writer = PdfWriter.getInstance(document, fos);
		writer.open();
		document.open();
		document.add(Image.getInstance((new URL(input))));
		document.close();
		writer.close();
	}
}
