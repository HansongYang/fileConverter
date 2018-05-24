package fileConvertor;

import java.awt.Color;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.File;  
  
import javax.swing.JButton;  
import javax.swing.JFileChooser;  
import javax.swing.JFrame;  
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;  
  
public class FileChooser extends JPanel{  
    File file;
	int num;
      
    public void setUp() {  
        JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
        jfc.showOpenDialog(new JLabel());  
        file = jfc.getSelectedFile();  
        
        if(file.isDirectory()){  
            System.out.println("Folder:"+file.getAbsolutePath());  
        }else if(file.isFile()){  
            System.out.println("File:"+file.getAbsolutePath());  
        }  
        System.out.println(jfc.getSelectedFile().getName());   
    }  
    
    public void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setUpChooseType(){
    	Object[] options ={ "gif", "jpg", "png"};
    	num = JOptionPane.showOptionDialog(null, "Please choose your image type.", "Information",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
    
    public int chooseType(){
    	setUpChooseType();
    	return num;
    }
}  