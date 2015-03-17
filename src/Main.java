import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(new File("test3.txt")));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    byte[] buf = new byte[3*100];
		for(int u=0; u<100; u++){
			
			Random r=new Random();
			int a=r.nextInt(500);
			int b=r.nextInt(500);
			int c=r.nextInt(30+1);
			
			/*buf[u]=(byte) a;
			buf[u+2]=(byte) b;
			buf[u+3]=(byte) c;
			try {
				bos.write(buf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		*/
			
			Point A = new Point(a, b, null, c);
			listPoints.add(A);
		}
		
		System.out.println(listPoints);
		//Lire le fichier
	    /*Scanner scanner = new Scanner(new FileReader("path_to_file"));
	    String str = null;
	    String mot = null;
	    int compteur=1;
	    int x1,x2,y1,y2, nbParameters;
	    int bornInfParam1, bornSupParam1;
	    int bornInfParam2, bornSupParam2;
	    int a,b,c,d;
	    
	    while (scanner.hasNext()) {
	        mot = scanner.next();
	        
	        switch(compteur){
	        case 1:
	        	x1=Integer.parseInt(mot);
	        	break;
	        case 2:
	        	x2=Integer.parseInt(mot);
	        	break;
	        case 3:
	        	y1=Integer.parseInt(mot);
	        	break;
	        case 4:
	        	y2=Integer.parseInt(mot);
	        	break;
	        case 5:
	        	nbParameters=Integer.parseInt(mot);
	        	break;
	        case 6:
	        	bornInfParam1=Integer.parseInt(mot);
	        	break;
	        case 7:
	        	bornSupParam1=Integer.parseInt(mot);
	        	break;
	       /* case 8:
	        	bornInfParam2=Integer.parseInt(mot);
	        	break;
	        case 9:
	        	bornSupParam2=Integer.parseInt(mot);
	        	break;	        		
	        }
	        
	        if(compteur>7){
	        	switch(compteur%4){
	        	case 0:
	        		a=Integer.parseInt(mot);
	        		break;
	        	case 1:
	        		b=Integer.parseInt(mot);
	        		break;
	        	case 2:{
	               	c=Integer.parseInt(mot);
	               	Point A = new Point(a, b, null, c);
	    			listPoints.add(A);
	        	}
	               	break;
	        	/*case 3:{
	        		d=Integer.parseInt(mot);
	        		Point A = new Point(a, b, null, c);
	    			listPoints.add(A);
	        	}
	        		break;
	        	}
	        }
	        	
	       
	        compteur++;
	        }
	
	    int ncols=Math.abs(x2-x1);
		int nrows=Math.abs(y2-y1);
	    interpolator.setNrows(nrows);
	    interpolator.setNcols(ncols);*/
		
		interpolator.setNrows(500);
		interpolator.setNcols(500);
		int nrows = interpolator.getNrows();
		System.out.println(nrows);
		int ncols = interpolator.getNcols();
		
		//Les points concernés sont noirs
		for(int k=0; k<listPoints.size();k++){
			listPoints.get(k).setColor(Color.black);
		}
		
		//Choix de l'interpolateur à faire (Vornoi, PlusProche, Marching squares)
		boolean boucle = false;
		
		String choix, choix1;
		Scanner scanIn = new Scanner(System.in);
		
		while(boucle == false){
			
			boolean boucle1 = false;
			
			System.out.println("Choisir le type d'interpolateur : ");
			System.out.println("V pour les diagrammes de Vornoi");
			System.out.println("N pour la cartographie type météo/altitude");
			System.out.println("M pour le Marching Squares");
			System.out.println("Q pour Quitter");
	
			choix = scanIn.nextLine();
			char carac = choix.charAt(0);
			
	       
			if(carac == 'V'){
				System.out.println("Diagramme de Voronoi");
				interpolator.Voronoi(listPoints);
				interpolator.setName("Voronoi");
				boucle = true;				
				System.out.println(boucle);
			}
			else if(carac == 'N'){
				System.out.println("Cartographie");
				interpolator.nearestNeighbourSearch(listPoints);
				interpolator.setName("Cartographie");
				boucle = true;
			}
			else if(carac == 'M'){
				System.out.println("Marching Squares");
				interpolator.setName("Marching Squares");
				boucle = true;
			}
			else if(carac == 'Q'){
				System.out.println("Extinction du programme");
				boucle = true;
			}
			else{
				while(boucle1 == false){
					System.out.println("Erreur dans la saisie, voulez vous quitter ? (O/N)");
					choix1 = scanIn.nextLine();
					char carac1 = choix1.charAt(0);
					if(carac1 == 'N'){
						boucle1 = true;
					}
					else if (carac1 == 'O'){
						System.out.println("Extinction du programme");
						boucle = true;
						boucle1 = true;
					}		
				}
			}
				
				
		}
				
		scanIn.close();  
		String name = interpolator.getName();
		if(name=="Voronoi" || name=="Cartographie" || name=="Marching Squares"){
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
}
