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

public class Window extends JFrame implements ActionListener {

	private Panel pan = new Panel();
	private JButton bouton = new JButton("Voronoi");
	private JButton bouton2 = new JButton("Cartographie");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel();
	private ArrayList<Point> list = new ArrayList<Point>();
	private Test test = new Test();
	private Interpolator interpolator = new Interpolator();

	private BufferedImage img = null;

	public Window() {
		this.setTitle("Interpolation");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		interpolator.setNcols(500);
		interpolator.setNrows(500);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		bouton.addActionListener(new BoutonListener());
		// bouton.setEnabled(false);
		bouton2.addActionListener(new Bouton2Listener());
		
		pan.remove(label);
		//pan.revalidate();
		//pan.repaint();

		JPanel south = new JPanel();
		south.add(bouton);
		south.add(bouton2);
		container.add(south, BorderLayout.SOUTH);
		this.setContentPane(container);
		this.setVisible(true);

	}

	private void voronoi() {
		interpolator.initArrays(test.getListPoint());
		interpolator.Voronoi(test.getListPoint());
		
		int nrows = 500, ncols = 500;
		Point[] tabPoints = interpolator.getTable();
		
		img = new BufferedImage(nrows, ncols, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i<nrows; i++){
			for(int j=0;j<ncols;j++){
		  int index=i*nrows+j;
		  if(tabPoints[index].getColor()==null)
			  tabPoints[index].tempColor();
		  int rgb =  tabPoints[index].getColor().getRGB();
		  img.setRGB(i, j, rgb);
			}
		}
		
		
	}

	private void cartographie() {
		interpolator.initArrays(test.getListPoint());
		interpolator.nearestNeighbourSearch(test.getListPoint());
		
		int nrows = 500, ncols = 500;
		Point[] tabPoints = interpolator.getTable();
		
		img = new BufferedImage(nrows, ncols, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i<nrows; i++){
			for(int j=0;j<ncols;j++){
		  int index=i*nrows+j;
		  if(tabPoints[index].getColor()==null)
			  tabPoints[index].tempColor();
		  int rgb =  tabPoints[index].getColor().getRGB();
		  img.setRGB(i, j, rgb);
			}
		}
	}

	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			bouton.setEnabled(false);
			bouton2.setEnabled(true);
			
			voronoi();
			pan.newPicture(label, img);
			pan.revalidate();
			
		}
	}

	class Bouton2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bouton.setEnabled(true);
			bouton2.setEnabled(false);
			
			cartographie();
			pan.newPicture(label, img);
			pan.revalidate();
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}