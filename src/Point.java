import java.awt.Color;
import java.util.ArrayList;


public class Point {

	private int x;
	private int y;
	private Color color;
	private int temp;
	private int altitude;
	
	private Point ClosestPoint = null;
	private ArrayList<Point> listNearestPoints = new ArrayList<Point>();
	
	
	public Point(int x, int y, Color color, int temp) {
		this.x=x;
		this.y=y;
		this.setColor(color);
		this.temp=temp;
	}
	
	
	public Point(int x, int y, Color color, int temp, int altitude) {
		this.x=x;
		this.y=y;
		this.setColor(color);
		this.temp=temp;
		this.altitude=altitude;
	}

	//Getters et Setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public Point getClosestPoint(){
		return ClosestPoint;
	}
	
	public ArrayList<Point> getListNearestPoints(){
		return listNearestPoints;
	}
	
	public void setListNearestPoints(ArrayList<Point> list){
		listNearestPoints=list;
	}

	/**
	 * Distance euclidienne standard, peut etre redefini si necessaire
	 * @param A
	 * @param B
	 * @return
	 */
	
	public double distance(Point A, Point B){
		int x_A=A.getX(); int y_A=A.getY();
		int x_B=B.getX(); int y_B=B.getY();		
		
		return Math.hypot(x_A - x_B, y_A - y_B);
	}
	
	/**
	 * Algorithme naif de recherche de la distance minimum d'un point à une liste
	 * de points. Optimisation possible mais l'effort de programmation n'est sans
	 * doute pas rentable
	 * @param A
	 * @param list
	 * @return
	 */
	
	public Point minimumDistancePoint(Point A, ArrayList<Point> list){
		int i = 0;
		double d,minimum = 0;
		
		for(i=0; i<list.size(); i++){
			if(A==null);
			else{
			d=distance(A,list.get(i));
			if(d<minimum || minimum==0){
				minimum=d;
				ClosestPoint=list.get(i);
			}
			}
		}
		
		return ClosestPoint;
	}
	
	/**
	 * Algorithme naif donnant une liste de "points les plus proches" suivant deux parametres
	 * Un point est dit proche d'un point A si il est suffisamment proche (au sens de la distance
	 * defini plus haut) ET si il n'y a pas deja nBMax points deja defini comme "proche".
	 * 
	 * @param distanceMin
	 * @param nBMax
	 * @param A
	 * @param listPoints
	 * @return
	 */
	public ArrayList<Point> NearestPoints(double distanceMin,int nBMax, Point A, ArrayList<Point> listPoints){
		double distance;
		for(int i=0; i<listPoints.size(); i++){
			distance = distance(A,listPoints.get(i));
			if(distance<distanceMin 
					&& listNearestPoints.size()<nBMax)
				listNearestPoints.add(listPoints.get(i));
		}
				
		return listNearestPoints;
	}
	
	/**
	 * Ici on associe des plages de temperatures a des couleurs specifiques
	 * Il est possible de faire de meme pour l'altitude
	 * 
	 */
	
	public void tempColor(){
		if(temp<=5)
			color = Color.blue;
		else if(temp>5 && temp<=10)
			color = Color.cyan;
		else if(temp>10 && temp<=15)
			color = Color.green;
		else if(temp>15 && temp<=20)
			color = Color.yellow;
		else if(temp>20 && temp<=25)
			color = Color.orange;
		else if(temp>25)
			color = Color.red;
	}
}
