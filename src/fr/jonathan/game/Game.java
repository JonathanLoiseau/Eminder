package fr.jonathan.game;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jonathan.main.Main;
import fr.jonathan.screen.ScreenDisplayer;
import fr.jonathan.utility.ChekTool;
import fr.jonathan.utility.CombGenerator;
import fr.jonathan.utility.Config;
import fr.jonathan.utility.MasterNumberException;
import fr.jonathan.utility.Mode;
import fr.jonathan.utility.Props;
import fr.jonathan.utility.Solution;
import fr.jonathan.utility.Tentative;

/**
 * SuperClasse des jeux {@link MasterMind} et {@link PlusOuMoins}
 * @author joe
 *
 */
public abstract class Game {
	
	/**
	 * Solution du jeux, la combinaison a trouver
	 */
	protected Solution sol;
	/**
	 * Les combinaison du joueur qui tente de trouver la solution
	 */
	protected Tentative tent ;
	/**
	 * Outil de vérification, il compare la solution à la tentative
	 */
	protected  ChekTool ct ;
	/**
	 * Utile en mode Duel, permet de savoir quel est le gagnant après les deux modes de jeux.
	 */
	
	protected  int score = 0;
	/**
	 * Le générateur de combinaison, une fois celle ci générées elles sont transmises à {@link Solution} et {@link Tentative}
	 */
	protected CombGenerator comb = new CombGenerator();
	/**
	 * En charge de la génération des écrans
	 */
	private ScreenDisplayer screen = new ScreenDisplayer();
	private Scanner scan = new Scanner (System.in);
	private static final Logger logger = LogManager.getLogger(Game.class);
	
	
	
	public Game ( ) {
		this.sol=new Solution();
		this.tent=new Tentative();
		this.ct=new ChekTool();
		this.comb = new CombGenerator();
		this.score=0;
		
		
	}

	/**
	 * 
	 * @return le score du jeux en cours
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * lance une partie en mode challenger
	 * @param duel le jeux est il en mode duel (true/false)
	 * @param j le jeux en cours, MasterMind ou plusOuMoins
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void initChal(boolean duel,Jeux j,Config conf ) {
		comb.addCPUnumber(j, conf);
		sol.setCombinaison(comb.getCombinaison());
		if(conf.getDebug()==true) {
			logger.info(comb.getCombinaison());
			}
		comb.clear();
		while(!ct.getGood()) {
			try {
			comb.addHumanNumber(j, conf);
			}catch(MasterNumberException e) {}
			tent.setCombinaison(comb.getCombinaison());
			comb.clear();
			if(duel == true) {
				if(j==Jeux.MASTER) {
					ct.verifMaster(sol, tent,Mode.MASTER_CHAL, conf);
				} else {
					ct.verifPm(sol, tent, Mode.CHAL, conf);
				}
				
			}else {
				if(j== Jeux.MASTER) {
					ct.verifMaster(sol, tent,Mode.MASTER_DUEL_ATK, conf);
				} else {
					ct.verifPm(sol, tent, Mode.DUEL_ATK, conf);
				}
			}
		}
		logger.info( "Le score est de " + ct.getScore());
	}
	/**
	 * Lance une partie en mode défenseur
	 * @param duel le jeux est il en mode duel (true/false)
	 * @param j le jeux en cours, MasterMind ou plusOuMoins
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void initDef(Boolean duel, Jeux j,Config conf ) {
		try {
			comb.addHumanNumber(j, conf);
		}catch (MasterNumberException e) {}
		sol.setCombinaison(comb.getCombinaison());
		comb.clear();
		ct.setGood(false);
		while(!ct.getGood()) {
			
			comb.addCPUnumber(j, conf);
			tent.setCombinaison(comb.getCombinaison());
			comb.clear();
			if(duel == true) {
				if (j==Jeux.MASTER) {
				ct.verifMaster(sol, tent,Mode.MASTER_DEF, conf);
				}else {
					ct.verifPm(sol, tent, Mode.DEF, conf);
				}
				
			}else {
				if(j==Jeux.MASTER) {
					ct.verifMaster(sol, tent,Mode.MASTER_DUEL_DEF, conf);
				} else {
					ct.verifPm(sol, tent, Mode.DUEL_DEF,conf);
				}
				
			}
		}
	}

	/**
	 * lance une partie en mode duel
	 * @param j le jeux en cours, MasterMind ou plusOuMoins
	 * @param m le mode de jeux en cours
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur
	 */
	public void initDuel(Jeux j, Mode m, Config conf) {
		logger.info(" \n Vous attaquez \n  ");
		
		initChal(false, j, conf );
		logger.info("\n Vous défendez \n ");
		
		initDef(false,j, conf);
		logger.info( "le score est de : " + ct.getScore());
		 
		 if (ct.getScore() >0) {
			 screen.succesScreen(scan, m, conf);
		 }else if (ct.getScore()<0) {
			 screen.gameOver(scan, m, conf);
		 }else {
			 screen.drawScreen(scan,m, conf);
		 }
		 score =0 ;
	}
	
}
