package fr.jonathan.Game;

import fr.jonathan.plus.Config;
import fr.jonathan.plus.Mode;

public class PlusOuMoins extends Game {
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
