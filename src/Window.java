import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6158315784701438853L;
	
	private Panel pan = new Panel();
	private JButton bouton = new JButton("Voronoi");
	private JButton bouton2 = new JButton("Cartographie");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel();

	private Test test = new Test();
	private Interpolator interpolator = new Interpolator();;

	private BufferedImage img = null;

	public Window() {
		this.setTitle("Interpolation");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		bouton.addActionListener(new BoutonListener());
		bouton2.addActionListener(new Bouton2Listener());

		pan.remove(label);

		JPanel south = new JPanel();
		south.add(bouton);
		south.add(bouton2);
		container.add(south, BorderLayout.SOUTH);
		this.setContentPane(container);
		this.setVisible(true);
		
		
	}

	private void voronoi() {
		System.out.println(interpolator.getListPoints());
		interpolator.initArrays(test.getListPoint());
		interpolator.Voronoi();
		interpolator.saveInterpolatorImage();
	}

	private void cartographie() {
		interpolator.initArrays(test.getListPoint());
		interpolator.nearestNeighbourSearch();
		interpolator.saveInterpolatorImage();
	}

	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			// bouton.setEnabled(false);
			// bouton2.setEnabled(true);
			voronoi();
			img = interpolator.getImg();
			pan.newPicture(label, img);
			pan.revalidate();

		}
	}

	class Bouton2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// bouton.setEnabled(true);
			// bouton2.setEnabled(false);

			cartographie();
			img = interpolator.getImg();
			pan.newPicture(label, img);
			pan.revalidate();

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}