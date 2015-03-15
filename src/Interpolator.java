import java.awt.Color;
import java.util.ArrayList;


public class Interpolator {
	
	private int nrows = 500;
	private int ncols = 500;
	private int picsize = nrows*ncols;
	private Point[] tabPoints = new Point[picsize];
	private double[] tabTemp = new double[picsize];
	
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
	
	public double[] getTableTemp(){
		return tabTemp;
	}
	
	public void initArrays(ArrayList<Point> listPoints){
		//Remplissage du tableau      
		int i,j;
		for(i=0; i<nrows; i++){
			for(j=0;j<ncols;j++){
			Point A = new Point(i,j,null,0);
			tabPoints[i*nrows+j]=A;
			}
		}
		
		for(i=0; i<listPoints.size(); i++){
			int x_i = listPoints.get(i).getX();
			int y_i = listPoints.get(i).getY();
			
			tabPoints[x_i*nrows+y_i]=listPoints.get(i);
		}		
	}
	
	public void Voronoi(ArrayList<Point> listPoints){
		
		int i,compteur = 0;
		Point B = new Point(15, 2, Color.red, 0);
			      
		initArrays(listPoints);
		
		for(i=0; i<picsize; i++){
			B.minimumDistancePoint(tabPoints[i], listPoints);
			Point ClosestPoint=B.getClosestPoint();
			System.out.println(ClosestPoint);
			System.out.println(compteur);
			tabPoints[i].setColor(ClosestPoint.getColor());
			compteur++;
		}	
	}
	
	public void nearestNeighbourSearch(ArrayList<Point> listPoints){
		
		int i,j;
		int nBMax = 5;
		double distanceMin = 160;
		Point B = new Point(15, 243, Color.red,13);
		
		initArrays(listPoints);
		
		for(i=0; i<picsize; i++){
			
			Point A=tabPoints[i];
			
			if((listPoints.contains(A)==false)){
				
				B.NearestPoints(distanceMin, nBMax, A, listPoints);
				ArrayList<Point> listNearestPoints = B.getListNearestPoints();
				double w = 0,n = 0;
				
				
				for(j=0; j<listNearestPoints.size(); j++){
					
					w = w + (1/carre(B.distance(tabPoints[i], listNearestPoints.get(j))));
					n = n + (listNearestPoints.get(j).getTemp()/carre(B.distance(tabPoints[i], listNearestPoints.get(j))));
				}
	
				tabPoints[i].setTemp((int) (n/w));
				
				ArrayList<Point> liste = new ArrayList<Point>();
				B.setListNearestPoints(liste);
			}
		}
		}
	
	public double carre(double x){
		return x*x;
	}
}
