import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Interpolator interpolator = new Interpolator();
		
		/**
		 * Création
		 * de la liste
		 * de points
		 */
		ArrayList<Point> listPoints = new ArrayList<Point>();
		
		for(int u=0; u<100; u++){
			
			Random r=new Random();
			int a=r.nextInt(500);
			int b=r.nextInt(500);
			int c=r.nextInt(30+1);
			
			Point A = new Point(a, b, null, c);
			listPoints.add(A);
		}	
		
		//Les points concernés sont noirs
		for(int k=0; k<listPoints.size();k++){
			listPoints.get(k).setColor(Color.black);
		}
		
		//Choix de l'interpolateur à faire (Vornoi, PlusProche, Marching squares)
		boolean boucle = false;	
		String choix;
		Scanner scanIn = new Scanner(System.in);
		
		while(boucle == false){
			
			System.out.println("Choisir le type d'interpolateur : ");
			System.out.println("V pour les diagrammes de Vornoi");
			System.out.println("N pour la cartographie type météo/altitude");
			System.out.println("M pour le Marching Squares");
	
			choix = scanIn.nextLine();
			char carac = choix.charAt(0);
			
	       
			if(carac == 'V'){
				System.out.println("Diagramme de Voronoi");
				interpolator.Voronoi(listPoints);
				boucle = true;				
				System.out.println(boucle);
			}
			else if(carac == 'N'){
				System.out.println("Cartographie");
				interpolator.nearestNeighbourSearch(listPoints);
				boucle = true;
			}
			else if(carac == 'M'){
				System.out.println("Marching Squares");
				boucle = true;
			}
			else
				System.out.println("Erreur dans la saisie");	
		}
				
		scanIn.close();  
		
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
	
		//Selection du dossier des images selon le systeme d'exploitation
		FilesManager manager = new FilesManager();
		manager.SystemChoice();
		String dossierImages = manager.getDossierImages();
		
		File file = new File(dossierImages + "test15.png");
		try {
			ImageIO.write(img, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

}
}
