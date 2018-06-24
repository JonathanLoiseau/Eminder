package fr.jonathan.game;

import java.util.logging.Logger;

import fr.jonathan.main.Main;
import fr.jonathan.utility.Config;
import fr.jonathan.utility.Mode;
/**
 * Cette classe représente un jeux de type Mastermind
 * @author Joe
 *
 */
public class MasterMind extends Game {
	/**
	 * 
	 * @param m Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public MasterMind (Mode m, Config conf){
		if (m==Mode.MASTER_CHAL) {
			initChal(true, Jeux.MASTER,conf );
		} else if (m==Mode.MASTER_DEF) {
			initDef(true, Jeux.MASTER,conf );
		} else  {
			initDuel(Jeux.MASTER,Mode.MASTER_DUEL, conf);
			
			}
		}
}
