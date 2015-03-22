import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame{

  private Panel pan = new Panel();
  private JButton bouton = new JButton("Voronoi");
  private JButton bouton2 = new JButton("Cartographie");
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("Le JLabel");
  private ArrayList<Point> list = new ArrayList<Point>();
  private Test test = new Test();
  private Interpolator interpolator = new Interpolator();
  
  private BufferedImage img=new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);

  public Window(){
    this.setTitle("Interpolation");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    container.add(pan, BorderLayout.CENTER);
    bouton.addActionListener(new BoutonListener()); 
    //bouton.setEnabled(false);
    bouton2.addActionListener(new Bouton2Listener());

    JPanel south = new JPanel();
    south.add(bouton);
    south.add(bouton2);
    container.add(south, BorderLayout.SOUTH);
    this.setContentPane(container);
    this.setVisible(true);
  }

  private void voronoi(){
  }
  
  private void cartographie(){
  }

  class BoutonListener implements ActionListener{
    public void actionPerformed(ActionEvent arg0) {
      bouton.setEnabled(false);
      bouton2.setEnabled(true);
     
      
      pan.newBackGroundPicture();
      pan.revalidate();
      pan = new Panel();
    }
  }

  class Bouton2Listener implements ActionListener{
     public void actionPerformed(ActionEvent e) {   
      bouton.setEnabled(true);
      bouton2.setEnabled(false);
      
      pan.backGroundPicture();
      pan.revalidate();
      pan = new Panel();
    }
  }     
}