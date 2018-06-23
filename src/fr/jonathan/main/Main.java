package fr.jonathan.main;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jonathan.plus.Config;
import fr.jonathan.plus.Props;
import fr.jonathan.screen.ScreenDisplayer;

public class Main {
	
	private static final Logger logger = LogManager.getLogger(Main.class);
	
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
			
		 
		 
	 }else if (args.length == 0 & prop.nbCase!=0 & prop.nbEssai!=0& prop.nbMaster>=4 &prop.nbMaster<10) {
		 logger.debug("Jeux lancé à partir de properties");
		 Config conf = new Config (prop.nbCase,prop.nbEssai,prop.nbMaster,prop.debug);
		 ScreenDisplayer display = new ScreenDisplayer();
		 display.homeScreen(scan, conf);
		 scan.close();
		 
	 }else {
		 Config conf = new Config( 4 , 5 , 9 ,false);
		 logger.debug("Jeux lancé avec les parametres par défaut");
		 ScreenDisplayer display = new ScreenDisplayer();
		 display.homeScreen(scan, conf);
		 scan.close();
	 }
	}
}




