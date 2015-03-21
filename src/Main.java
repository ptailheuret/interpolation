import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * Le principe du programme est de lire un fichier (ici crée en direct) .txt dont les "mots" sont pour l'instant les suivantes:
 * 
 * mot_1: Valeur minimum de l'intervalle horizontal
 * mot_2: Valeur maximum de l'intervalle horizontal
 * mot_3: Valeur minimum de l'intervalle vertical
 * mot_4: Valeur maximum de l'intervalle vertical
 * mot_5: Nombre de parametres (ici 1)
 * mot_6: Valeur minimum de l'intervalle du paramètre
 * mot_7: Valeur maximum de l'intervalle du paramètre
 * 
 * 
 * Pour n>7: Soit P un point tq P(i,j,null,temp)
 * 	Si n%3=2: Valeur de i
 * 	Si n%3=0: Valeur de j
 * 	Si n%3=1: Valeur de temp
 * 
 * Les points de cette liste sont noirs pour être vu sur l'image finale.
 * 
 * L'etape d'apres est le choix pour l'utilisateur du programme qu'il desire executer
 * 
 * On remplit alors une image via les donnees recuperees lors de l'execution de l'interpolateur choisis
 * 
 * 
 * @author Tailheuret
 *
 */

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		

		int cmt=5;
		

		Interpolator interpolator = new Interpolator();
		
		//Creation de la liste de points
		int width = 500;
		int height = 500;
		int tempMin = -20;
		int tempMax = 60;
		
		ArrayList<Point> listPoints = new ArrayList<Point>();
	
		for(int u=0; u<200; u++){
			
			Random r=new Random();
			int a = r.nextInt(width+1);
			int b = r.nextInt(height+1);
			int c = tempMin + r.nextInt((tempMax-tempMin)+1);
					
			Point A = new Point(a, b, null, c);
			listPoints.add(A);
		}
		
		//Enregistrer le fichier
	
		 FileWriter outFile = new FileWriter("StudentDataTest" + cmt + ".txt");
         BufferedWriter outStream = new BufferedWriter(outFile);
         
         outStream.write(String.valueOf(0));outStream.write(" "); 
         outStream.write(String.valueOf(width));outStream.write(" "); 
         outStream.write(String.valueOf(0));outStream.write(" "); 
         outStream.write(String.valueOf(height));outStream.write(" "); 
         outStream.write(String.valueOf(1));outStream.write(" "); 
         outStream.write(String.valueOf(tempMin));outStream.write(" "); 
         outStream.write(String.valueOf(tempMax));outStream.write(" "); 
         
         for (int k = 0; k < listPoints.size(); k++){
             outStream.write(String.valueOf(listPoints.get(k).getX()));
             outStream.write(" "); 
             outStream.write(String.valueOf(listPoints.get(k).getY()));
             outStream.write(" ");
             outStream.write(String.valueOf((listPoints.get(k).getTemp())));
             outStream.write(" ");
             
         }
         outStream.flush();
         outStream.close();
         
         listPoints = new ArrayList<Point>();

		
		//Lire le fichier
         
	    Scanner scanner = new Scanner(new FileReader("StudentDataTest" + cmt + ".txt"));
	    String mot = null;
	    int compteur=1;
	    int x1=0,x2=0,y1=0,y2=0, nbParameters;
	    int bornInfParam1, bornSupParam1;
	    int bornInfParam2, bornSupParam2;
	    int a=0,b=0,c=0,d=0;

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
	        default:
	        	break;	        		
	        }
	        
	        if(compteur>7){
	        	switch(compteur%3){
	        	case 2:
	        		a=Integer.parseInt(mot);
	        		break;
	        	case 0:
	        		b=Integer.parseInt(mot);
	        		break;
	        	case 1:{
	               	c=Integer.parseInt(mot);
	               	Point A = new Point(a, b, null, c);
	    			listPoints.add(A);
	    			System.out.print(A.getX()); System.out.print(" "); System.out.print(A.getY()); System.out.print(" "); System.out.print(A.getTemp()); System.out.println();
	        	}
	               	break;
	        	}
	        }
	        compteur++;
	        }
	    
	    scanner.close();
		        
	    //Definition de ncols et nrows
	 
	    int ncols=Math.abs(x2-x1);
		int nrows=Math.abs(y2-y1);
	    interpolator.setNrows(nrows);
	    interpolator.setNcols(ncols);
		
		//Les points de la liste lu dans le fichier sont noirs
	    
		for(int k=0; k<listPoints.size();k++){
			listPoints.get(k).setColor(Color.black);
		}
		
		//Choix de l'interpolateur a  faire (Vornoi, PlusProche, Marching squares)
		boolean boucle = false;
		
		String choix = null, choix1 = null;
		Scanner scanIn = new Scanner(System.in);
		
		while(boucle == false){
			
			boolean boucle1 = false;
			
			System.out.println("Choisir le type d'interpolateur : ");
			System.out.println("V pour les diagrammes de Vornoi");
			System.out.println("N pour la cartographie type mÃ©tÃ©o/altitude");
			System.out.println("M pour le Marching Squares");
			System.out.println("Q pour Quitter");
	
			choix = scanIn.nextLine();
			char carac = choix.charAt(0);
			
	       
			if(carac == 'V'){
				System.out.println("Diagramme de Voronoi");
				long start = System.currentTimeMillis();
				interpolator.Voronoi(listPoints);
				long duree = System.currentTimeMillis() - start;
				System.out.println();
				System.out.println("Execution time:" + duree/1000 + "s");
				interpolator.setName("Voronoi");
				boucle = true;				
			}
			else if(carac == 'N'){
				System.out.println("Cartographie");
				long start = System.currentTimeMillis();
				interpolator.nearestNeighbourSearch(listPoints);
				long duree = System.currentTimeMillis() - start;
				System.out.println();
				System.out.println("Execution time:" + duree/1000 + "s");
				interpolator.setName("Cartographie");
				boucle = true;
			}
			else if(carac == 'M'){
				System.out.println("Marching Squares");
				long start = System.currentTimeMillis();
				long duree = System.currentTimeMillis() - start;
				System.out.println();
				System.out.println("Execution time:" + duree/1000 + "s");
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
		
		//Verifie que l'algorithme selectionne est bien dans la liste
		
		if(name=="Voronoi" || name=="Cartographie" || name=="Marching Squares"){
		Point[] tabPoints = interpolator.getTable();
		
		//Remplit l'image avec le resultat de l'application de l'algorithme
		
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
		
		File file = new File(dossierImages + "test" + interpolator.getName() + cmt + ".png");
		try {
			ImageIO.write(img, "png", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Affichage de l'image dans un JFrame
		
		JFrame jframe = new JFrame(interpolator.getName());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel vidpanel = new JLabel();
        jframe.setContentPane(vidpanel);
        jframe.setSize(ncols, nrows);
        jframe.setVisible(true);
        
        ImageIcon image = new ImageIcon(img);
        vidpanel.setIcon(image);
        vidpanel.repaint();        
		}
	}
}
