package fr.jonathan.game;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jonathan.main.Main;
import fr.jonathan.plus.ChekTool;
import fr.jonathan.plus.CombGenerator;
import fr.jonathan.plus.Config;
import fr.jonathan.plus.MasterNumberException;
import fr.jonathan.plus.Mode;
import fr.jonathan.plus.Props;
import fr.jonathan.plus.Solution;
import fr.jonathan.plus.Tentative;
import fr.jonathan.screen.ScreenDisplayer;


public abstract class Game { 
	protected Solution sol;
	protected Tentative tent ;
	protected  ChekTool ct ;
	protected  int score = 0;
	protected CombGenerator comb = new CombGenerator();
	private ScreenDisplayer screen = new ScreenDisplayer();
	private Scanner scan = new Scanner (System.in);
	private static final Logger logger = LogManager.getLogger(Game.class);
	//private Props prop = new Props();
	
	
	public Game ( ) {
		this.sol=new Solution();
		this.tent=new Tentative();
		this.ct=new ChekTool();
		this.comb = new CombGenerator();
		this.score=0;
		
		
	}

	public int getScore() {
		return score;
	}
	
	public void initChal(boolean duel,Jeux j,Config conf ) {
		comb.addCPUnumber(j, conf);
		sol.setCombinaison(comb.getCombinaison());
		if(conf.getDebug()==true) {
			logger.info(comb.getCombinaison());
			}
		comb.clear();
		while(ct.getGood()==false) {
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
	
	public void initDef(Boolean duel, Jeux j,Config conf ) {
		try {
			comb.addHumanNumber(j, conf);
		}catch (MasterNumberException e) {}
		sol.setCombinaison(comb.getCombinaison());
		comb.clear();
		ct.setGood(false);
		while(ct.getGood()==false) {
			
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
