package fr.jonathan.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jonathan.game.Game;
import fr.jonathan.screen.ScreenDisplayer;
/**
 * Compare  les combinaisons {@link Solution} et {@link Tentative} et détermine la marche a suivre selon le résultat obtenu (rejouez, fin de partie, victoire );
 * @author Joe
 *
 */
public class ChekTool {
	
	private Scanner scan =new Scanner (System.in);
	/**
	 * nombre d'essai effectué
	 */
	private int essai = 1;
	/**
	 * permet l'affichage de l'écran correspondant au résultat obtenu après vérification ( nouvelle partie, game over etc...)
	 */
	private ScreenDisplayer screen = new ScreenDisplayer();
	/**
	 * Si Solution et tentative sont identique : true, sinon :false
	 */
	private boolean isGood = false;
	/**
	 * Le score en fin de manche, utilisé en mode duel uniquement
	 */
	private int score = 0;
	/**
	 * affichage de la solution sous forme de chiffre
	 */
	private String soluce = "";
	/**
	 * Information donnée a l'attaquant  sous la forme ("+-=") en mode PlusOuMoins.
	 */
	private String str = "";
	/**
	 * dernière combinaison saisie
	 */
	private String comb = "Combinaison actuel ";
	/**
	 * nombre de chiffre présent mais mal placé en mode MasterMind
	 */
	private int présent = 0;
	/**
	 * nombre de chiffre correctement placé en mode MasterMind
	 */
	private int correct = 0;
	/**
	 * nombre de chiffre correctement placé en mode PlusOuMoins
	 */
	private int correctPlus = 0;
	private static final Logger logger = LogManager.getLogger(ChekTool.class);
	
	
	/**
	 * Dans le jeux  PlusOuMoins vérifie si  la solution s et la tentative t sont égale en comparant le nombre d'élément correct avec la taille de la combianaison et définis une marche a suivre en fonction du résultat. 
	 * @param s La solution a trouver
	 * @param t La tentative de l'attaquant
	 * @param m Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void verifPm(Solution s ,Tentative t, Mode m,Config conf) {
		check(s,t,m);
		if (correctPlus == s.getCombinaisonSize()) {
			found(s,t,m, conf);
			correctPlus=0;
		} else if (essai == conf.getNbEssai()) {
			fail(s,t,m, conf);
			correctPlus=0;
		} else {
			tryAgain(s,t,m, conf);
			correctPlus=0;
		}
		str = "";
		comb= "Combinaison actuel ";
		soluce="";
	}
	/**
	 * Dans le jeux  MasterMind vérifie si  la solution s et la tentative t sont égale en comparant le nombre d'élément correct avec la taille de la combianaison et définis une marche a suivre en fonction du résultat.
	 * @param s La solution a trouver
	 * @param t  La tentative de l'attaquant
	 * @param m  Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 * @param conf  la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void verifMaster(Solution s, Tentative t, Mode m,Config conf) {
		checkMaster(s,t,m);
		if (correct == s.getCombinaisonSize()) {
			found(s,t,m, conf);
			correct=0;
			présent=0;
		} else if (essai == conf.getNbEssai()) {
			fail(s,t,m, conf);
			correct=0;
			présent=0;
		} else {
			tryAgain(s,t,m, conf);
			correct=0;
			présent=0;
			
		}
		str = "";
		comb= "Combinaison actuel ";
		soluce="";
	}

	/**
	 * Dans le jeux  PlusOuMoins compare les deux combinaisons
	 * @param s la solution
	 * @param t la tentative de l'attaquant
	 * @param m Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 */
	public void check (Solution s ,Tentative t, Mode m) {
		
		for (int i = 0 ; i < s.getCombinaisonSize(); i++) {

			if (s.get(i)==(t.get(i))){
				str+=("=");
				correctPlus++;

			}else if ((s.get(i))>(t.get(i))) {
				str+="+";

			}else if ((s.get(i))<(t.get(i))) {   
				str+="-";
			}else {
				str += "";
			}
			soluce+=(s.get(i)+ " , ");
			comb+=(t.get(i));
		}
	}
	/**
	 * Dans le jeux  MasterMind  compare les deux combinaisons, indique quel chiffres sont correctement placé et place les autres dans une nouvelle liste
	 * @param s la solution
	 * @param t	la tentative de l'attaquant
	 * @param m  Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 */
	public void checkMaster(Solution s ,Tentative t, Mode m) {
		List<Integer>sVerif = new ArrayList<Integer>();
		List<Integer>tVerif = new ArrayList<Integer>();
		for (int i = 0 ; i < s.getCombinaisonSize(); i++) {
			 	if (t.get(i)==s.get(i)) {
			 		correct++;
			 	} else {
			 		sVerif.add(s.get(i));
			 		tVerif.add(t.get(i));
			 	}
			 	soluce+=(s.get(i)+ " , ");
				comb+=(t.get(i));
		}
		str += ("bien placé : " + correct ) ;
		checkBis(sVerif, tVerif);
	}
	/**
	 * Dans le jeux  MasterMind compare les chiffres qui ne sont pas correctement placé pour savoir si ils sont présent
	 * @param s Liste des  chiffres de la solution qui n'ont pas était trouvé
	 * @param t Liste des chiffres de la tentative qui n'était pas correct
	 */
	public void checkBis(List<Integer>s ,List<Integer>t) {
		for (int i = 0; i <t.size(); i++) {
			for (int j =0 ; j <s.size();j++) {
				if (t.get(i)==s.get(j)) {
				présent ++;
				s.remove(j);
				break;
				}
			}
		}
		str += (" présent : " + présent);
	}


	/**
	 * Comportement a adopter lorsque la solution est trouvé , varie  en fonction du mode
	 * @param s La solution
	 * @param t La tentative
	 * @param m Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void found(Solution s , Tentative t ,Mode m , Config conf) {
		isGood=true;
		if (m == Mode.CHAL||m == Mode.MASTER_CHAL) {
			screen.succesScreen(scan, m, conf);
		}else if (m == Mode.DEF||m== Mode.MASTER_DEF) {
		screen.gameOver(scan,m, conf);
		}else if (m == Mode.DUEL_ATK|| m==Mode.MASTER_DUEL_ATK) {
			s.clear();
			t.clear();
			logger.info("Vous avez gagné le premier round, préparez vous à défendre");
			score ++;
		}else {
			logger.info("Vous avez perdu ce round "); 
			s.clear();
			t.clear();
			score --;
		}
	}
	/**
	 * Comportement a adopter lorsque après le nombre maximal d'essai (obtenu via Config conf) la solution n'est toujours pas trouvé, varie en fonction du Mode m
	 * @param s la solution
	 * @param t la tentative
	 * @param m	Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void fail (Solution s , Tentative t ,Mode m, Config conf) {
		essai = 1;
		isGood= true;
		if(m == Mode.CHAL || m==Mode.MASTER_CHAL) {
			logger.info("\n la solution était " + soluce);
			screen.gameOver(scan,m, conf);
		}else if (m == Mode.DEF|| m ==Mode.MASTER_DEF){
			screen.succesScreen(scan,m, conf);
		}else if (m == Mode.DUEL_DEF|| m== Mode.MASTER_DUEL_DEF){
			logger.info("Vous avez gagné le second round fin de la partie");
			s.clear();
			t.clear();
			score++;
		}else {
			logger.info("\n Vous avez perdu le premier round mais vous pouvez encore vous rattraper! \n ");
			s.clear();
			t.clear();
			logger.info(" A vous de défendre \n");
			score--;
		}
	}
	/**
	 * Comportement a adopté lorsque la combinaison n'est pas trouvé mais qu'il reste des essais
	 * @param s La solution
	 * @param t La tentative
	 * @param m Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 * @param conf La configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public void tryAgain(Solution s , Tentative t ,Mode m, Config conf) {
		
		if (s.getError()==true) {
			isGood = false;
		}else {
			essai ++;
			logger.info(comb+ " " +str + "\n\nessayez encore\n "); 
			
			t.clear();
			logger.info("nombre d'essai restant = " + ((conf.getNbEssai() +1)- essai)+"\n");
			isGood = false;
		}
	}
	
	public boolean getGood() {
		return isGood;
	}
	public void setGood(boolean b) {
		isGood=b;
	}
	public int getScore() {
		return score;
	}
}
