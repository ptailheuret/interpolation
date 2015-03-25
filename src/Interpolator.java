import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Interpolator {

	private int nrows;
	private int ncols;
	private int picsize;
	private Point[] tabPoints;
	private double[] tabTemp;
	private String name;
	private BufferedImage img;
	private ArrayList<Point> listPoints;

	public Interpolator() {
		nrows = 500;
		ncols = 500;
		picsize = nrows * ncols;
		tabPoints = new Point[ncols * nrows];
		tabTemp = new double[picsize];
		img = new BufferedImage(nrows, ncols, BufferedImage.TYPE_INT_RGB);
		listPoints = new ArrayList<Point>();
	}

	public int getNrows() {
		return nrows;
	}

	public void setNrows(int rows) {
		nrows = rows;
	}

	public int getNcols() {
		return ncols;
	}

	public void setNcols(int cols) {
		ncols = cols;
	}

	public Point[] getTable() {
		return tabPoints;
	}

	public void setTable(Point[] tab) {
		tabPoints = tab;
	}

	public double[] getTableTemp() {
		return tabTemp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public ArrayList<Point> getListPoints() {
		return listPoints;
	}

	public void setListPoints(ArrayList<Point> listPoints) {
		this.listPoints = listPoints;
	}

	public double carre(double x) {
		return x * x;
	}

	/**
	 * Initialisation des tableaux La premiere boucle remplit le tableaux de
	 * points de couleurs null et de temperature nulle La seconde ajoute les
	 * points lus dans le fichier (donc ecrase listPoints.size() des points
	 * precedemment definis
	 * 
	 * @param listPoints
	 */
	
	public void initArrays(ArrayList<Point> listPoints) {
		
		this.listPoints = listPoints;
		
		// Remplissage du tableau
		int i, j;
		for (i = 0; i < nrows; i++) {
			for (j = 0; j < ncols; j++) {
				Point A = new Point(i, j, null, 0);
				tabPoints[i * nrows + j] = A;
			}
		}

		for (i = 0; i < listPoints.size(); i++) {
			int x_i = listPoints.get(i).getX();
			int y_i = listPoints.get(i).getY();

			tabPoints[x_i * nrows + y_i] = listPoints.get(i);
		}
	}

	/**
	 * Implementation naive de l'algorithme n�1 Il permet la creation de
	 * diagrammes de Voronoi
	 * 
	 * @param listPoints
	 */

	public void Voronoi() {
		
		initArrays(listPoints);

		for (int i = 0; i < ncols * nrows; i++) {
			tabPoints[i].minimumDistancePoint(listPoints);
			Point ClosestPoint = tabPoints[i].getClosestPoint();
			tabPoints[i].setTemp(ClosestPoint.getTemp());
		}
	}

	/**
	 * Implementation de l'algorithme n�2 Il simule ce qui est effectue lors
	 * d'une cartographie (altitude) ou au niveau de la meteorologie
	 * (temperatures)
	 * 
	 * @param listPoints
	 */

	public void nearestNeighbourSearch() {

		int nBMax = 50;
		double distanceMin = 200 * 200;

		initArrays(listPoints);

		for (int i = 0; i < ncols * nrows; i++) {

			Point A = tabPoints[i];

			if ((listPoints.contains(A) == false)) {

				A.NearestPoints(distanceMin, nBMax, listPoints);
				ArrayList<Point> listNearestPoints = A.getListNearestPoints();
				double w = 0, n = 0;

				for (int j = 0; j < listNearestPoints.size(); j++) {

					w = w + (1 / A.distanceToCompare(tabPoints[i], listNearestPoints.get(j)));
					n = n
							+ (listNearestPoints.get(j).getTemp() / (A.distanceToCompare(
									tabPoints[i], listNearestPoints.get(j))));
				}

				tabPoints[i].setTemp((int) (n / w));

				ArrayList<Point> liste = new ArrayList<Point>();
				A.setListNearestPoints(liste);
			}
		}
	}

	public void saveInterpolatorImage() {
		
		for (int k = 0; k < getListPoints().size(); k++) {
			getListPoints().get(k).setColor(Color.black);
		}
		
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				int index = i * nrows + j;
				if (tabPoints[index].getColor() == null)
					tabPoints[index].tempColor();
				int rgb = tabPoints[index].getColor().getRGB();
				img.setRGB(i, j, rgb);
			}
		}
	}

	/*******************
	 * Marching square *
	 *******************/

}
