import java.awt.Color;
import java.util.ArrayList;


public class Point {

	private int x;
	private int y;
	private Color color;
	
	private Point ClosestPoint = null;
	
	public Point(int x, int y, Color color) {
		this.x=x;
		this.y=y;
		this.setColor(color);
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
	
	public Point getClosestPoint(){
		return ClosestPoint;
	}

	public double distance(Point A, Point B){
		int x_A=A.getX(); int y_A=A.getY();
		int x_B=B.getX(); int y_B=B.getY();		
		
		return Math.hypot(x_A - x_B, y_A - y_B);
	}
	
	public double minimumDistance(Point A, ArrayList<Point> list){
		int i = 0;
		double d,minimum = 0;
		
		for(i=0; i<list.size(); i++){
			d=distance(A,list.get(i));
			if(d<minimum || minimum==0)
				minimum=d;
		}
		return minimum;
	}
	
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
	
}
