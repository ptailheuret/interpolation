import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Interpolator interpolator = new Interpolator();
		
		Point A = new Point(27, 130, Color.blue);
		Point B = new Point(460, 45, Color.red);
		Point C = new Point(54, 200, Color.green);
		Point D = new Point(110, 86, Color.black);
		Point E = new Point(235, 56, Color.blue);
		Point F = new Point(190, 190, Color.red);
		Point G = new Point(368, 245, Color.green);
		Point H = new Point(378, 200, Color.black);
		Point I = new Point(470, 490, Color.red);
		
		ArrayList<Point> listPoints = new ArrayList();
		
		listPoints.add(A);listPoints.add(B);listPoints.add(C);listPoints.add(D);
		listPoints.add(E);listPoints.add(F);listPoints.add(G);listPoints.add(H);
		listPoints.add(I);
	
		interpolator.Voronoi(listPoints);
		
		int nrows = interpolator.getNrows();
		int ncols = interpolator.getNcols();
		
		BufferedImage img=new BufferedImage(nrows, ncols, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i<nrows; i++){
			for(int j=0;j<ncols;j++){
		  int index=i*nrows+j;
		  Point[] tab = interpolator.getTable();
		  int rgb = tab[index].getColor().getRGB();
		  img.setRGB(i, j, rgb);
			}
		}
	
	File file = new File("/home/patrick/test");
	try {
		ImageIO.write(img, "png", file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		

}
}
