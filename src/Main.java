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
		
		/*
		Point A = new Point(27, 130, Color.blue);
		Point B = new Point(460, 45, Color.red);
		Point C = new Point(54, 200, Color.green);
		Point D = new Point(110, 86, Color.red);
		Point E = new Point(235, 56, Color.blue);
		Point F = new Point(190, 190, Color.red);
		Point G = new Point(368, 245, Color.green);
		Point H = new Point(378, 200, Color.blue);
		Point I = new Point(470, 490, Color.red);*/
		
		Point A = new Point(457, 453, null, 30);
		Point B = new Point(460, 45, null, 15);
		Point C = new Point(54, 200, null, 2);
		Point D = new Point(110, 456, null, 22);
		Point E = new Point(235, 56, null, 6);
		Point F = new Point(190, 190, null, 12);
		Point G = new Point(368, 245, null, 22);
		Point H = new Point(378, 200, null, 33);
		Point I = new Point(470, 490, null, 16);
		
		ArrayList<Point> listPoints = new ArrayList<Point>();
		
		listPoints.add(A);listPoints.add(B);listPoints.add(C);listPoints.add(D);
		listPoints.add(E);listPoints.add(F);listPoints.add(G);listPoints.add(H);
		listPoints.add(I);
		
		for(int k=0; k<listPoints.size();k++){
			listPoints.get(k).setColor(Color.black);
		}
	
		/*interpolator.Voronoi(listPoints);
		
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
	*/
		
		interpolator.nearestNeighbourSearch(listPoints);
		
		int nrows = interpolator.getNrows();
		int ncols = interpolator.getNcols();
		Point[] tabPoints = interpolator.getTable();
		 
		BufferedImage img=new BufferedImage(nrows, ncols, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i<nrows; i++){
			for(int j=0;j<ncols;j++){
		  int index=i*nrows+j;
		  if(tabPoints[index].getColor()==null)
			  tabPoints[index].tempColor();
		  int rgb =  tabPoints[index].getColor().getRGB();
		  img.setRGB(i, j, rgb);
			}
		}
		
		
	File file = new File("/home/patrick/test1");
	try {
		ImageIO.write(img, "png", file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		

}
}
