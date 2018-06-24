package fr.jonathan.screen;

import java.io.FileOutputStream; 
import java.io.IOException;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jonathan.game.MasterMind;
import fr.jonathan.game.PlusOuMoins;
import fr.jonathan.utility.Config;
import fr.jonathan.utility.MasterNumberException;
import fr.jonathan.utility.Mode;
import fr.jonathan.utility.Props;

/**
 * Classe servant a naviguer entre les différents écrans du jeux
 * @author Joe
 *
 */
public class ScreenDisplayer {
	/**
	 * Permet de savoir si le jeux actuel est MasterMind(false) ou PlusOuMoins(true)
	 */
	private boolean  isPlus;
	/**
	 *  Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 */
	private Mode m;
	private static final Logger logger = LogManager.getLogger(ScreenDisplayer.class);
	//private Props propert = new Props();

	/**
	 * Affiche l'écran de démarage
	 * @param scan scanner pour choisir le jeux
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void homeScreen(Scanner scan , Config conf  ){

		String str = " <<<<<<E-MINDER>>>>>>\n\n";
		str+= "Bienvenue et amusez vous!!\n\n";
		str += "Veuillez choisir votre jeux\n";
		str+= " \t -1 Plus ou Moins \n";
		str+= " \t -2 MasterMind \n";
		logger.info(str);

		String choix = scan.next();

		switch(choix) {
		case "1" : 
			logger.info("Vous avez choisi le  Plus ou Moins! \n\nVeuillez maintenant choisir votre mode de jeux ");
			isPlus=true;
			modeSelector(scan, conf );
			break;
		case "2"  : 
			logger.info("Vous avez choisi le  MasterMind! \n\nVeuillez maintenant choisir votre mode de jeux ");
			isPlus=false;
			modeSelector(scan, conf );
			break;
		default: logger.info( choix + " n'est pas une réponse valide veuillez choisir à nouveau s'il vous plait."); 
		homeScreen(scan, conf);
		break;
		}
	}
	/**
	 * Choix du mode par l'utilisateur
	 * @param scan scanner pour choisir le mode
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void modeSelector( Scanner scan, Config conf) {
		if (isPlus) {
			String str = "\n\n< Plus Ou Moins >\n\n";
			str += " \t -1 mode Challenger\n";
			str += " \t -2 mode Defenseur\n";
			str += " \t -3 mode Duel\n" ;
			str += " \t -4 retour au menu principal";
			logger.info(str);

			String choix = scan.next();
			switch (choix) {
			case "1":
				logger.info("Vous avez choisis le mode Challenger\n");
				m= Mode.CHAL;
				PlusOuMoins plusc = new PlusOuMoins(m, conf);
				break;
			case "2":
				logger.info("Vous avez choisi le mode Defenseur\n");
				m = Mode.DEF;
				PlusOuMoins plusdef = new PlusOuMoins(m, conf);
				break;
			case "3" : 
				logger.info("Vous avez choisi le mode Duel\n");
				m =Mode.DUEL;
				PlusOuMoins plusDudu = new PlusOuMoins(m, conf);
				break;
			case "4":
				logger.info("Retour au menu principal\n");
				isPlus = false;
				homeScreen(scan, conf );
				break;
			default :
				logger.info(choix +" n'est pas une réponse valide veuillez choisir a nouveau");
				modeSelector(scan, conf);

			}

		}else {
			String str = "\n\n< MasterMind >\n\n";
			str += " \t -1 mode Challenger\n";
			str += " \t -2 mode Defenseur\n";
			str += " \t -3 mode Duel\n" ;
			str += " \t -4 retour au menu principal";
			logger.info(str);

			String choix = scan.next();
			switch (choix) {
			case "1":
				logger.info("Vous avez choisis le mode Challenger\n");
				m= Mode.MASTER_CHAL;
				MasterMind master = new MasterMind(m, conf);
				break;
			case "2":
				logger.info("Vous avez choisi le mode Defenseur\n");
				m = Mode.MASTER_DEF;
				MasterMind masterD = new MasterMind(m, conf);
				break;
			case "3" : 
				logger.info("Vous avez choisi le mode Duel\n");
				m = Mode.MASTER_DUEL;
				MasterMind masters = new MasterMind(m, conf);
				break;
			case "4":
				logger.info("Retour au menu principal\n");
				isPlus = false;
				homeScreen(scan, conf);
				break;
			default :
				logger.info(choix +" n'est pas une réponse valide veuillez choisir a nouveau");
				modeSelector(scan, conf);

			}
		}


	}
	/**
	 * Affiche l'écran Game Over
	 * @param scan scanner choix de l'action a effectué après une défaite
	 * @param m Le mode de jeux (challenger, defenseur, duel) 
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur) 
	 */
	public void gameOver(Scanner scan , Mode m,Config conf) {
		String str = "<<<<< GAME OVER>>>>\n";
		str+= " Voulez vous : \n";
		str+= "\t -1 Revenir au Menu Principal\n";
		str+= "\t -2 Rejouez une Partie\n";
		str += "\t -3 Quittez l'application\n";
		logger.info(str);

		replay(scan ,m, conf);

	}
	/**
	 * Affiche l'écran de victoire
	 * @param scan scanner choix de l'action a effectué après une victoire.
	 * @param m Le mode de jeux (challenger, defenseur, duel)
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void succesScreen(Scanner scan , Mode m, Config conf) {
		String str = "<<<<< VICTOIRE>>>>\n";
		str+= " Voulez vous : \n";
		str+= "\t -1 Revenir au Menu Principal\n";
		str+= "\t -2 Rejouez une Partie\n";
		str += "\t -3 Quittez l'application\n";
		logger.info(str);

		replay(scan , m, conf );
	}
	/**
	 * Affiche l'écran d'égalité
	 * @param scan scanner choix de l'action a effectué après un draw
	 * @param m Le mode de jeux (challenger, defenseur, duel)
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void drawScreen (Scanner scan ,Mode m, Config conf) {
		String str = "<<<<< MATCH NUL>>>>\n";
		str+= " Voulez vous : \n";
		str+= "\t -1 Revenir au Menu Principal\n";
		str+= "\t -2 Rejouez une Partie\n";
		str += "\t -3 Quittez l'application\n";
		logger.info(str);

		replay(scan, m, conf);

	}
	/**
	 * Ecran affiché à la fin d'un partie
	 * @param scan scanner choix de l'action a effectué en fin de partie
	 * @param m Le mode de jeux (challenger, defenseur, duel)
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void replay(Scanner scan ,Mode m, Config conf) {
		String choix = scan.next();

		switch (choix) {
		case "1" : 
			logger.info("\n Retour au Menu Principal \n ");
			homeScreen(scan, conf);
			break;
		case "2" :
			modeChoice(m, conf);

			break;
		case "3" :
			logger.info(" \n Au revoir");
			System.exit(0);
			break;
		default :
			String str =(choix + " n'est pas une option valide veuillez choisir de nouveau\n");
			str+=  " Voulez vous : \n";
			str+= "\t -1 Revenir au Menu Principal\n";
			str+= "\t -2 Rejouez une Partie\n";
			str += "\t -3 Quittez l'application\n";
			logger.info(str);
			replay(scan,m, conf);
		}
	}
	/**
	 * permet de relancer une partie dans le bon mode sans nouvelle saisie utilisateur en utilisant le mode en cour.
	 * @param m Le mode de jeux (challenger, defenseur, duel)
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
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
}







