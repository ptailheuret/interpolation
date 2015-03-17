import java.awt.Color;
import java.util.ArrayList;


public class Interpolator {
	
	private int nrows;
	private int ncols;
	private int picsize = nrows*ncols;
	private Point[] tabPoints = new Point[ncols*nrows];
	private double[] tabTemp = new double[picsize];
	private String name;
	
	public Interpolator(){
	}
	
	public int getNrows(){
		return nrows;
	}
	
	public void setNrows(int rows){
		nrows=rows;
	}
	
	public int getNcols(){
		return ncols;
	}
	
	public void setNcols(int cols){
		ncols=cols;
	}
	
	public Point[] getTable(){
		return tabPoints;
	}
	
	public void setTable(Point[] tab){
		tabPoints=tab;
	}
	
	public double[] getTableTemp(){
		return tabTemp;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double carre(double x){
		return x*x;
	}
	
	
	/*******************************
	 * Initialisation des tableaux *
	 *******************************/
	
	public void initArrays(ArrayList<Point> listPoints){
		Point[] tab = new Point[ncols*nrows];
		
		//Remplissage du tableau      
		int i,j;
		for(i=0; i<nrows; i++){
			for(j=0;j<ncols;j++){
			Point A = new Point(i,j,null,0);
			tab[i*nrows+j]=A;
			}
		}
		
		for(i=0; i<listPoints.size(); i++){
			int x_i = listPoints.get(i).getX();
			int y_i = listPoints.get(i).getY();
			
			tab[x_i*nrows+y_i]=listPoints.get(i);
		}
		
		setTable(tab);
		
	}
	
	
	/***************************************
	 * Interpolation, diagramme de Voronoi *
	 ***************************************/
	
	public void Voronoi(ArrayList<Point> listPoints){
		
		int i;
		Point B = new Point(15, 2, Color.red, 0);
		
		initArrays(listPoints);
		
		for(i=0; i<ncols*nrows; i++){
			B.minimumDistancePoint(tabPoints[i], listPoints);
			Point ClosestPoint=B.getClosestPoint();
			tabPoints[i].setTemp(ClosestPoint.getTemp());
		}	
	}
	
	
	/*******************************
	 * Interpolation, cartographie *
	 *******************************/
	
	public void nearestNeighbourSearch(ArrayList<Point> listPoints){
		
		int i,j;
		int nBMax = 50;
		double distanceMin = 200;
		Point B = new Point(15, 243, Color.red,13);
		
		initArrays(listPoints);
		
		for(i=0; i<ncols*nrows; i++){
			
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
	
	
	/*******************
	 * Marching square *
	 *******************/
	

}
