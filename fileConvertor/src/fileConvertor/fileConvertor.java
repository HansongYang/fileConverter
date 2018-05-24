package fileConvertor;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

import com.itextpdf.text.DocumentException;

import java.awt.*; 

public class fileConvertor extends JFrame{
	pdfAndDoc pdfAndDoc = new pdfAndDoc();
	pptAndPDF pptAndPDF = new pptAndPDF();
	toText toText = new toText();
	pdfAndDocx pdfAndDocx = new pdfAndDocx();
	pdfAndImage pdfAndImage = new pdfAndImage();
	pdfAndText pdfAndText = new pdfAndText();
	FileChooser FileChooser = new FileChooser();
    
	JPanel aPanel = new JPanel(new GridLayout(4, 1,10,10));
	JFrame window = new JFrame();
	JLabel label = new JLabel(" Welcome to File Convertor!");
	
	JButton PTD = new JButton("PDF to DOC");
	JButton DTP = new JButton("DOC to PDF");
	JButton PTP = new JButton("PPT to PDF");
	JButton PTDX = new JButton("PDF to DOCX");
	JButton DXTP = new JButton("DOCX to PDF");
	JButton PTI = new JButton("PDF to Image");
	JButton ITP = new JButton("Image to PDF");
	JButton PTT = new JButton("PDF to TEXT");
	JButton TTP = new JButton("TEXT to PDF");
	JButton DTT = new JButton("DOC to TEXT");
	JButton DXTT = new JButton("DOCX to TEXT");
	
	public fileConvertor(String title){
		super(title);
		setUp();
	}
	
	public void setUp(){
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		aPanel.setBounds(0, 100, 1600, 700);
		window.add(aPanel);
		aPanel.setBackground(Color.GRAY);
		label.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,35));
		label.setForeground(Color.RED);
		
		Dimension d = new Dimension(120,60);
		PTD.setPreferredSize(d);
		DTP.setPreferredSize(d);
		PTP.setPreferredSize(d);
		PTDX.setPreferredSize(d);
		DXTP.setPreferredSize(d);
		PTI.setPreferredSize(d);
		ITP.setPreferredSize(d);
		PTT.setPreferredSize(d);
		TTP.setPreferredSize(d);
		DTT.setPreferredSize(d);
		DXTT.setPreferredSize(d);
		
		PTD.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,30));
		DTP.setFont(new Font("Century",Font.BOLD|Font.ITALIC,30));
		PTP.setFont(new Font("Century",Font.BOLD|Font.ITALIC,30));
		PTDX.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,30));
		DXTP.setFont(new Font("Century",Font.BOLD|Font.ITALIC,30));
		PTI.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,30));
		ITP.setFont(new Font("Times New Roman",Font.BOLD|Font.ITALIC,30));
		PTT.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,30));
		TTP.setFont(new Font("Century",Font.BOLD|Font.ITALIC,30));
		DTT.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,30));
		DXTT.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,30));
		
		aPanel.add(label);
		aPanel.add(PTD);
		aPanel.add(DTP);
		aPanel.add(PTP);
		aPanel.add(PTDX);
		aPanel.add(DXTP);
		aPanel.add(PTI);
		aPanel.add(ITP);
		aPanel.add(PTT);
		aPanel.add(TTP);
		aPanel.add(DTT);
		aPanel.add(DXTT);

		window.setVisible(true);
		window.setSize(1450,730);
	}
	
	public void listener(){
		PTD.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				try {
					FileChooser.setUp();
					String type = null;
					Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
				    type = Files.probeContentType(path);
					System.out.println(type);
					
					if(type.equals("application/pdf")){
						pdfAndDoc.generateDocFromPDF(FileChooser.file.getAbsolutePath());
					}else{
						FileChooser.infoBox("This is not a PDF file, please try again.", "Error");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		DTP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				FileChooser.setUp();
				String type = null;
				Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
			    try {
					type = Files.probeContentType(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(type);
				
				if(type.equals("application/msword")){
					String output;
					output = pdfAndDoc.readDocFile(FileChooser.file.getAbsolutePath());
					
					try {
						pdfAndDoc.writePdfFile(output);
					} catch (FileNotFoundException | DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else{
					FileChooser.infoBox("This is not a DOC file, please try again.", "Error");
				}
			}
		});
		
		PTP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				try {
					FileChooser.setUp();
					String type = null;
					Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
				    type = Files.probeContentType(path);
					System.out.println(type);
					
					if(type.equals("application/vnd.ms-powerpoint")){
						pptAndPDF.convertPPTToPDF(FileChooser.file.getAbsolutePath(), FileChooser.file.getAbsolutePath().substring(0,FileChooser.file.getAbsolutePath().lastIndexOf("."))+ ".pdf" , ".ppt");
			    	}else{
						FileChooser.infoBox("This is not a PPT file, please try again.", "Error");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		PTDX.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				try {
					FileChooser.setUp();
					String type = null;
					Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
				    type = Files.probeContentType(path);
					System.out.println(type);
					
					if(type.equals("application/pdf")){
						pdfAndDocx.generateDocxFromPDF(FileChooser.file.getAbsolutePath());
					} else{
						FileChooser.infoBox("This is not a PDF file, please try again.", "Error");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		DXTP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				FileChooser.setUp();
				
				String type = null;
				Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
			    try {
					type = Files.probeContentType(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(type);
				
				if(type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
					String output;
					output = pdfAndDocx.readDocxFile(FileChooser.file.getAbsolutePath());
					try {
						pdfAndDocx.writePdfFile(output);
					} catch (FileNotFoundException | DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					FileChooser.infoBox("This is not a DOCX file, please try again.", "Error");
				}
			}
		});
		
		PTI.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				try {
					FileChooser.setUp();
					String type = null;
					Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
				    type = Files.probeContentType(path);
					System.out.println(type);
					
					if(type.equals("application/pdf")){
						int num = FileChooser.chooseType();
						switch(num){
						case 0: type = "gif"; break;
						case 1: type = "jpg"; break;
						case 2: type = "png"; break;
						default: break;
						}
						pdfAndImage.generateImageFromPDF(FileChooser.file.getAbsolutePath(), type);
					} else{
						FileChooser.infoBox("This is not a PDF file, please try again.", "Error");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		ITP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				try {
					FileChooser.setUp();
					String type = null;
					Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
				    type = Files.probeContentType(path);
					System.out.println(type);
					
					if(type.equals("image/jpeg") || type.equals("image/png") || type.equals("image/gif")){
						pdfAndImage.generatePDFFromImage("file:///" + FileChooser.file.getAbsolutePath(), FileChooser.file.getName());
					} else{
						FileChooser.infoBox("This is not an Image file, please try again. (only gif,png,jpg)", "Error");
					}
				} catch (IOException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		PTT.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				try {
					FileChooser.setUp();
					String type = null;
					Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
				    type = Files.probeContentType(path);
					System.out.println(type);
					
					if(type.equals("application/pdf")){
						pdfAndText.generateTxtFromPDF(FileChooser.file.getAbsolutePath());
					}else{
						FileChooser.infoBox("This is not a PDF file, please try again.", "Error");
					}					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		TTP.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				try {
					FileChooser.setUp();
					String type = null;
					Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
				    type = Files.probeContentType(path);
					System.out.println(type);
					
					if(type.equals("text/plain")){
						pdfAndText.generatePDFFromTxt(FileChooser.file.getAbsolutePath());
					}else{
						FileChooser.infoBox("This is not a TEXT file, please try again.", "Error");
					}
				} catch (IOException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		DTT.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				FileChooser.setUp();
				String type = null;
				Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
			    try {
					type = Files.probeContentType(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(type);
				if(type.equals("application/msword")){
					toText.parseDoc(FileChooser.file.getAbsolutePath());
				}else{
					FileChooser.infoBox("This is not a DOC file, please try again.", "Error");
				}
			}
		});
		
		DXTT.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				FileChooser.setUp();
				String type = null;
				Path path = Paths.get(FileChooser.file.getAbsolutePath()); 
			    try {
					type = Files.probeContentType(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(type);
				
				if(type.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")){
					toText.parseDocx(FileChooser.file.getAbsolutePath());
				}else{
					FileChooser.infoBox("This is not a DOCX file, please try again.", "Error");
				}
			}
		});
	}
	
    public static void main(String [] args){
    	fileConvertor convertor = new fileConvertor("Easy File Convertor.");
    	convertor.listener();
    }
}
