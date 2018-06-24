package fr.jonathan.game;

import fr.jonathan.utility.Config;
import fr.jonathan.utility.Mode;
/**
 * Représente  un jeux de type PlusOuMoins
 * @author Joe
 *
 */
public class PlusOuMoins extends Game {
	/**
	 * 
	 * @param m Le mode de jeux (challenger, defenseur, duel) {@link Mode}
	 * @param conf la configuration du jeux (nombre d'essai,nombre de case,nombre de chiffre utilisable et mode developpeur ou joueur)
	 */
	public PlusOuMoins(Mode m, Config conf) {
		if (m!=null) {
			switch (m) {
			case CHAL:
				initChal(true,Jeux.PLUS,conf );
				break;
			case DEF :
				initDef(true, Jeux.PLUS,conf	);
				break;
			default :
				initDuel(Jeux.PLUS,Mode.DUEL, conf);
			}
		}
	}

}
