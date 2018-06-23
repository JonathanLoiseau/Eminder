package fr.jonathan.game;

import java.util.logging.Logger;

import fr.jonathan.main.Main;
import fr.jonathan.plus.Config;
import fr.jonathan.plus.Mode;

public class MasterMind extends Game {
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
