package fr.jonathan.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jonathan.game.Game;
import fr.jonathan.game.Jeux;
/**
 * Objet chargé de la génération de combinaison
 * @author Joe
 *
 */
public class CombGenerator {
	/**
	 * La combinaison générée
	 */
	private List<Integer> combinaison = new ArrayList<Integer>();
	private Scanner sc = new Scanner (System.in);
	/**
	 * La combinaison est elle valide? Si celà n'est pas le cas, le nombre d'essai restant ne devra pas être incrémenté
	 */
	private boolean error = false;
	private static final Logger logger = LogManager.getLogger(CombGenerator.class);
/**
 * L'utilisateur entre une combinaison
 * @param j le jeux en cour
 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
 * @throws MasterNumberException si l'utilisateur tente de saisir un chiffre qui n'est pas autorisé en mode de jeux MasterMind
 */
	public void addHumanNumber(Jeux j,Config conf) throws MasterNumberException {
		if (j== Jeux.MASTER) {
			logger.info("Veuillez saisir une combinaison a "+ conf.getNbCase()+ " chiffres , compris entre 1 et " + conf.getNbMaster() );
		}else {
			logger.info("Veuillez saisir une combinaison a "+ conf.getNbCase()+ " chiffres");
		}
		String str = sc.nextLine();
		if (str.length()==conf.getNbCase()) {
			String [] tab = str.split("");
			for (String t :tab) {
				try {
					if (j==Jeux.MASTER) {
						int num = Integer.parseInt(t);
						if(num>conf.getNbMaster()|| num<1)throw new MasterNumberException(conf.getNbMaster());;
						combinaison.add(num);
						
						
						
					}else {
					int num = Integer.parseInt(t);
					combinaison.add(num);
					error = false;
					}

				} catch(Exception e) {
					logger.info(e.getMessage());
					logger.info("Veuillez réessayer \n");
					error = true;
					addHumanNumber( j, conf);
					break ;
				}	
			}
		} else {
			logger.info("Vous n'etes pas prêt, commencer par apprendre a compter jusqu'à " + conf.getNbCase());
			error = true;
			addHumanNumber(j,conf);
		}
	}
	/**
	 * L'ordinateur génère une combinaison aléatoire
	 * @param j le jeux en cour
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void addCPUnumber(Jeux j ,Config conf) {
		Random rand = new Random();
		int value;
		for (int i = 0; i <conf.getNbCase() ;i++) {
			if (j==Jeux.MASTER) {
			value = rand.nextInt(conf.getNbMaster()-1)+1;//évite le 0
			combinaison.add(value);
		}else {
				value = rand.nextInt(9)+1;
				combinaison.add(value);
			}
		}
		}
	
	public int getCombinaisonSize() {
		return combinaison.size();
	}

	public void clear() {
		combinaison.clear();
	}

	public int get(int i) {
		int value = combinaison.get(i);
		return value;
	}
	public boolean getError() {
		return error;
	}
	public List<Integer> getCombinaison() {
		return combinaison;
	}
}



