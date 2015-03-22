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
	}
	
	public void backGroundPicture(){
		JLabel label = new JLabel();
		
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
	
	public void newBackGroundPicture(){
		JLabel label = new JLabel();
		
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
	
	public void newPicture(BufferedImage inputfile){
		JLabel label = new JLabel();
		
		ImageIcon image = new ImageIcon(inputfile);
		label.setIcon(image);
		label.repaint();
		this.add(label);
	}

}
