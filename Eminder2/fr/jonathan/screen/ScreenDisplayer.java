package fr.jonathan.screen;

import java.io.FileOutputStream; 
import java.io.IOException;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import fr.jonathan.Game.MasterMind;
import fr.jonathan.Game.PlusOuMoins;
import fr.jonathan.plus.Config;
import fr.jonathan.plus.MasterNumberException;
import fr.jonathan.plus.Mode;
import fr.jonathan.plus.Props;


public class ScreenDisplayer {
	private boolean  isPlus;
	private Mode m;
	//private Props propert = new Props();

	public void homeScreen(Scanner scan , Config conf  ){
		String str = " <<<<<<E-MINDER>>>>>>\n\n";
		str+= "Bienvenue et amusez vous!!\n\n";
		str += "Veuillez choisir votre jeux\n";
		str+= " \t -1 Plus ou Moins \n";
		str+= " \t -2 MasterMind \n";
		System.out.println(str);

		String choix = scan.next();

		switch(choix) {
		case "1" : 
			System.out.println("Vous avez choisi le  Plus ou Moins! \n\nVeuillez maintenant choisir votre mode de jeux ");
			isPlus=true;
			modeSelector(scan, conf );
			break;
		case "2"  : 
			System.out.println("Vous avez choisi le  MasterMind! \n\nVeuillez maintenant choisir votre mode de jeux ");
			isPlus=false;
			modeSelector(scan, conf );
			break;
		default: System.out.println( choix + " n'est pas une réponse valide veuillez choisir à nouveau s'il vous plait."); 
		homeScreen(scan, conf);
		break;
		}
	}
	public void modeSelector( Scanner scan, Config conf) {
		if (isPlus) {
			String str = "\n\n< Plus Ou Moins >\n\n";
			str += " \t -1 mode Challenger\n";
			str += " \t -2 mode Defenseur\n";
			str += " \t -3 mode Duel\n" ;
			str += " \t -4 retour au menu principal";
			System.out.println(str);

			String choix = scan.next();
			switch (choix) {
			case "1":
				System.out.println("Vous avez choisis le mode Challenger\n");
				m= Mode.CHAL;
				PlusOuMoins plusc = new PlusOuMoins(m, conf);
				break;
			case "2":
				System.out.println("Vous avez choisi le mode Defenseur\n");
				m = Mode.DEF;
				PlusOuMoins plusdef = new PlusOuMoins(m, conf);
				break;
			case "3" : 
				System.out.println("Vous avez choisi le mode Duel\n");
				m =Mode.DUEL;
				PlusOuMoins plusDudu = new PlusOuMoins(m, conf);
				break;
			case "4":
				System.out.println("Retour au menu principal\n");
				isPlus = false;
				homeScreen(scan, conf );
				break;
			default :
				System.out.println(choix +" n'est pas une réponse valide veuillez choisir a nouveau");
				modeSelector(scan, conf);

			}

		}else {
			String str = "\n\n< MasterMind >\n\n";
			str += " \t -1 mode Challenger\n";
			str += " \t -2 mode Defenseur\n";
			str += " \t -3 mode Duel\n" ;
			str += " \t -4 retour au menu principal";
			System.out.println(str);

			String choix = scan.next();
			switch (choix) {
			case "1":
				System.out.println("Vous avez choisis le mode Challenger\n");
				m= Mode.MASTER_CHAL;
				MasterMind master = new MasterMind(m, conf);
				break;
			case "2":
				System.out.println("Vous avez choisi le mode Defenseur\n");
				m = Mode.MASTER_DEF;
				MasterMind masterD = new MasterMind(m, conf);
				break;
			case "3" : 
				System.out.println("Vous avez choisi le mode Duel\n");
				m = Mode.MASTER_DUEL;
				MasterMind masters = new MasterMind(m, conf);
				break;
			case "4":
				System.out.println("Retour au menu principal\n");
				isPlus = false;
				homeScreen(scan, conf);
				break;
			default :
				System.out.println(choix +" n'est pas une réponse valide veuillez choisir a nouveau");
				modeSelector(scan, conf);

			}
		}


	}

	public void gameOver(Scanner scan , Mode m,Config conf) {
		String str = "<<<<< GAME OVER>>>>\n";
		str+= " Voulez vous : \n";
		str+= "\t -1 Revenir au Menu Principal\n";
		str+= "\t -2 Rejouez une Partie\n";
		str += "\t -3 Quittez l'application\n";
		System.out.println(str);

		replay(scan ,m, conf);

	}

	public void succesScreen(Scanner scan , Mode m, Config conf) {
		String str = "<<<<< VICTOIRE>>>>\n";
		str+= " Voulez vous : \n";
		str+= "\t -1 Revenir au Menu Principal\n";
		str+= "\t -2 Rejouez une Partie\n";
		str += "\t -3 Quittez l'application\n";
		System.out.println(str);

		replay(scan , m, conf );
	}

	public void drawScreen (Scanner scan ,Mode m, Config conf) {
		String str = "<<<<< MATCH NUL>>>>\n";
		str+= " Voulez vous : \n";
		str+= "\t -1 Revenir au Menu Principal\n";
		str+= "\t -2 Rejouez une Partie\n";
		str += "\t -3 Quittez l'application\n";
		System.out.println(str);

		replay(scan, m, conf);

	}
	public void replay(Scanner scan ,Mode m, Config conf) {
		String choix = scan.next();

		switch (choix) {
		case "1" : 
			System.out.println("\n Retour au Menu Principal \n ");
			homeScreen(scan, conf);
			break;
		case "2" :
			modeChoice(m, conf);

			break;
		case "3" :
			System.out.println(" \n Au revoir");
			System.exit(0);
			break;
		default :
			String str =(choix + " n'est pas une option valide veuillez choisir de nouveau\n");
			str+=  " Voulez vous : \n";
			str+= "\t -1 Revenir au Menu Principal\n";
			str+= "\t -2 Rejouez une Partie\n";
			str += "\t -3 Quittez l'application\n";
			System.out.println(str);
			replay(scan,m, conf);
		}
	}
	public void modeChoice(Mode m, Config conf) {
		switch (m) {
		case CHAL:
		case DEF:
		case DUEL:
		case DUEL_ATK:
		case DUEL_DEF:
			PlusOuMoins plus = new PlusOuMoins(m, conf);
			break;
		default : 
			MasterMind masta = new MasterMind(m, conf);// a changer!!
		}
	}

	public Mode getMode() {
		return m;
	}

	public void setMode(Mode mode) {
		this.m= mode;
	}


	

		}
	






