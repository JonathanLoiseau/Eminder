package fr.jonathan.plus;

public class Config {
	private int nbCase ;
	private int nbEssai ;
	private int nbMaster ;
	private String debug;
	
	public Config (int nbCase, int nbEssai, int nbMaster,String debug ) {
		this.nbCase = nbCase;
		this.nbEssai = nbEssai;
		this.nbMaster=nbMaster;
		this.debug=debug;
	}

	public int getNbCase() {
		return nbCase;
	}

	public void setNbCase(int nbCase) {
		this.nbCase = nbCase;
	}

	public int getNbEssai() {
		return nbEssai;
	}

	public void setNbEssai(int nbEssai) {
		this.nbEssai = nbEssai;
	}

	public int getNbMaster() {
		return nbMaster;
	}

	public void setNbMaster(int nbMaster) {
		this.nbMaster = nbMaster;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}
	
}

