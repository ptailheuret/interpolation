import java.util.ArrayList;
import java.util.Random;


public class Test {

	 private static ArrayList<Point> listPoints = new ArrayList<Point>();
	 
	 ArrayList<Point> getListPoint(){
		return listPoints;
	 }
	 
	 void setListPoint(ArrayList<Point> list){
			listPoints = list;
		 }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int width = 500;
		int height = 500;
		int tempMin = -10;
		int tempMax = 40;
		
		listPoints = new ArrayList<Point>();
		
		for(int u=0; u<100; u++){
			
			Random r=new Random();
			int a = r.nextInt(width+1);
			int b = r.nextInt(height+1);
			int c = tempMin + r.nextInt((tempMax-tempMin)+1);
					
			Point A = new Point(a, b, null, c);
			listPoints.add(A);
		}
		
				@SuppressWarnings("unused")
				Window fenetre = new Window();
	}

}
