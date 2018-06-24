package fr.jonathan.main;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jonathan.screen.ScreenDisplayer;
import fr.jonathan.utility.Config;
import fr.jonathan.utility.Props;

/**
 * 
 * Classe de lancement principal
 * @author joe
 *
 */
public class Main {
	
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	/**
	 * Fonction de lancement.
	 * Instantie un {@link ScreenDisplayer} Par défault le jeux tente de démarrer sur via les args, si ceux ci ne sont pas disponible, via le fichier properties et si celui ci n'est pas correctemet renseigné, le jeux dispose de parametre par défault.
	 * @param args les args doivent correspondre à : args[0] le nombre de case du jeux,args[1] le nombre d'esssai,args le nombre de chifffre utilisable en mode masterMind[2],args[3] true pour mode devvelopppeur false mode classique.
	 */
	public static void main(String[] args) {
	
	Props prop = new Props();
	Scanner scan = new Scanner(System.in);
	 if (args.length != 0) {
		 logger.debug("jeux lancé a partir des arguments");
		 try {
			 int nbCase = Integer.valueOf(args[0]);
			 int nbEssai = Integer.valueOf(args[1]);
			 int nbMaster = Integer.valueOf(args[2]);
			 boolean debug=Boolean.valueOf(args[3]);
			 Config conf = new Config(nbCase,nbEssai,nbMaster,debug);
			 ScreenDisplayer display = new ScreenDisplayer();
			 display.homeScreen(scan, conf);
			 scan.close();
		 }catch(NumberFormatException e) {
			 logger.fatal("Vous êtes un sac",e);
			 System.exit(0);
		 }
			
		 
		 
	 }else if (args.length == 0 && prop.nbCase!=0 && prop.nbEssai!=0 && prop.nbMaster>=Config.nbMinMaster && prop.nbMaster<Config.nbMaxMaster) {
		 logger.debug("Jeux lancé à partir de properties");
		 Config conf = new Config (prop.nbCase,prop.nbEssai,prop.nbMaster,prop.debug);
		 ScreenDisplayer display = new ScreenDisplayer();
		 display.homeScreen(scan, conf);
		 scan.close();
		 
	 }else {
		 Config conf = new Config( Config.defaultNbCase , Config.defaultNbEssai , Config.nbMaxMaster ,false);
		 logger.debug("Jeux lancé avec les parametres par défaut");
		 ScreenDisplayer display = new ScreenDisplayer();
		 display.homeScreen(scan, conf);
		 scan.close();
	 }
	}
}




