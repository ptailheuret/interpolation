/**
 * Cette classe n'a qu'une seule utilite
 * permettre d'enregistrer et charger les fichiers facilement
 * peut importe le systeme d'exploitation utilise
 * 
 * 
 * @author Tailheuret
 *
 */

public class FilesManager {
	
	private String dossierImages;
	
	static String OS = System.getProperty("os.name").toLowerCase();
		
	public String getDossierImages() {
		return dossierImages;
	}

	public void setDossierImages(String dossierImages) {
		this.dossierImages = dossierImages;
	}

	public boolean isWindows() {		 
		return (OS.indexOf("win") >= 0); 
	}
 
	public boolean isMac() { 
		return (OS.indexOf("mac") >= 0); 
	}
 
	public boolean isUnix() { 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}
 
	public boolean isSolaris() { 
		return (OS.indexOf("sunos") >= 0); 
	}

	public void SystemChoice(){
		
		
		String dossierImages = null;
		
		if(isWindows()){
		dossierImages = System.getProperty("user.home") + "//workspace//data//";
		}
		
		else if(isMac()){
		dossierImages = System.getProperty("user.home") + "/workspace/data/";
		}
		
		else if(isUnix()){
		dossierImages = System.getProperty("user.home") + "/workspace/data/";
		}
		
		setDossierImages(dossierImages);
	}
}
