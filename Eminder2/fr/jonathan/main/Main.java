package fr.jonathan.main;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jonathan.plus.Config;
import fr.jonathan.plus.Props;
import fr.jonathan.screen.ScreenDisplayer;

public class Main {
	
	private static final Logger logger = LogManager.getLogger(Main.class.getName());
	
	public static void main(String[] args) {
	Props prop = new Props();
	Scanner scan = new Scanner(System.in);
	
	 if (args.length != 0) {
		 Config conf = new Config(Integer.valueOf(args[00]),Integer.valueOf(args[01]),Integer.valueOf(args[02]),args[03]);
		 ScreenDisplayer display = new ScreenDisplayer();
		 display.homeScreen(scan, conf);
		 scan.close();
		 
		 
	 }else if (args.length == 0 & prop.nbCase!=0 & prop.nbEssai!=0& prop.nbMaster>=4 &prop.nbMaster<10 & prop.debug!= "" ) {
		 Config conf = new Config (prop.nbCase,prop.nbEssai,prop.nbMaster,prop.debug);
		 ScreenDisplayer display = new ScreenDisplayer();
		 display.homeScreen(scan, conf);
		 scan.close();
		 
	 }else {
		 Config conf = new Config( 4 , 5 , 9 , "");
		 ScreenDisplayer display = new ScreenDisplayer();
		 display.homeScreen(scan, conf);
		 scan.close();
		 
	 }
	}
}




