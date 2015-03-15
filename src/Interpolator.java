import java.awt.Color;
import java.util.ArrayList;


public class Interpolator {
	
	private int nrows = 500;
	private int ncols = 500;
	private int picsize = nrows*ncols;
	private Point[] tabPoints = new Point[picsize];
	
	public Interpolator(){
	}
	
	public int getNrows(){
		return nrows;
	}
	
	public int getNcols(){
		return ncols;
	}
	
	public Point[] getTable(){
		return tabPoints;
	}
	
	public void Voronoi(ArrayList<Point> listPoints){
		//Nombre de lignes et nombre de colonnes

		int i,j,l, compteur = 0;
		Point B = new Point(15, 2, Color.red);
			      
		//Remplissage du tableau      
		
		for(i=0; i<nrows; i++){
			for(j=0;j<ncols;j++){
			Point A = new Point(i,j,null);
			tabPoints[i*nrows+j]=A;
			}
		}
		
		System.out.println(picsize);
		
		for(i=0; i<listPoints.size(); i++){
			int x_i = listPoints.get(i).getX();
			int y_i = listPoints.get(i).getY();
			
			tabPoints[x_i*(ncols-1)+y_i]=listPoints.get(i);
		}
		
		for(i=0; i<picsize; i++){
			B.minimumDistancePoint(tabPoints[i], listPoints);
			Point ClosestPoint=B.getClosestPoint();
			System.out.println(ClosestPoint);
			System.out.println(compteur);
			tabPoints[i].setColor(ClosestPoint.getColor());
			//listPoints.add(tabPoints[i]);
			compteur++;
		}
		
	}
}
