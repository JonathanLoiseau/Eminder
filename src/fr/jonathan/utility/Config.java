package fr.jonathan.utility;
/**
 * La configuration du jeux : nombre de case, nombre d'essai , chiffre utilisable en mastermind et mode developpeur ou joueur
 * @author Joe
 *
 */
public class Config {
	/**
	 * nombre de Case
	 */
	private int nbCase ;
	/**
	 * nombre d'Essai
	 */
	private int nbEssai ;
	/**
	 * Nombre de chiffre en mode MasterMind
	 */
	private int nbMaster ;
	/**
	 * Chiffre Maximum utilisable en mode MasterMind, si properties et args ne sont pas renseigné il fera office de valeur par défault.
	 */
	public static final int nbMaxMaster = 9;
	/**
	 * Chiffre minimum utilisable en mode MasterMind
	 */
	public static final int nbMinMaster = 4;
	/**
	 * nombre de case par défault si args et properties ne sont pas renseigné
	 */
	public static final int defaultNbCase=4;
	/**
	 * nombre d'essai par défault si args et properties ne sont pas renseigné
	 */
	public static final int defaultNbEssai=5;
	/**
	 * mode developpeur(true) ou joueur(false), si properties et args ne sont pas renseigné le jeux est par défault en mode joueur
	 */
	private boolean debug;
	
	public Config (int nbCase, int nbEssai, int nbMaster,Boolean debug ) {
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

	public boolean getDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	
}

