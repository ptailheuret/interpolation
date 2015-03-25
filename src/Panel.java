import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel{

	public Panel(){
		super();
	}
	
	public void backGroundPicture(JLabel label){
		
		BufferedImage inputfile = null;
		try {
			inputfile = ImageIO.read(new File("/home/patrick/workspace/data/Lenna.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageIcon image = new ImageIcon(inputfile);
		label.setIcon(image);
		label.repaint();
		this.add(label);
	}
	
	public void newBackGroundPicture(JLabel label){
		
		BufferedImage inputfile = null;
		try {
			inputfile = ImageIO.read(new File("/home/patrick/workspace/data/TABLE dessus_imageContours_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageIcon image = new ImageIcon(inputfile);
		label.setIcon(image);
		label.repaint();
		this.add(label);
		
	}
	
	public void newPicture(JLabel label, BufferedImage inputfile){
		
		ImageIcon image = new ImageIcon(inputfile);
		label.setIcon(image);
		label.repaint();
		this.add(label);
	}
	
	public void clearLabel(){
		this.removeAll();
		this.revalidate();
	}
	
	public void paintComponent(String string){
		   try {
		      BufferedImage img = ImageIO.read(new File(string));
		      Graphics g = img.createGraphics();
		      g.drawImage(img, 0, 0, this);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }                
		  }  

}
