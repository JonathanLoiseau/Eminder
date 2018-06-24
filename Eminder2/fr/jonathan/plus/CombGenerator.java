package fr.jonathan.plus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import fr.jonathan.Game.Jeux;

public class CombGenerator {
	
	private List<Integer> combinaison = new ArrayList<Integer>();
	//private Props prop = new Props();
	private Scanner sc = new Scanner (System.in);
	private boolean error = false;
	





	public void addHumanNumber(Jeux j,Config conf) throws MasterNumberException {
		if (j== Jeux.MASTER) {
			System.out.print("Veuillez saisir une combinaison a "+ conf.getNbCase()+ " chiffres , compris entre 1 et " + conf.getNbMaster() );
		}else {
			System.out.println("Veuillez saisir une combinaison a "+ conf.getNbCase()+ " chiffres");
		}
		String str = sc.nextLine();
		if (str.length()==conf.getNbCase()) {
			String [] tab = str.split("");

			for (String t :tab) {
				try {
					if (j==Jeux.MASTER) {
						int num = Integer.parseInt(t);
						if(num>conf.getNbMaster()|| num<1)throw new MasterNumberException();
						combinaison.add(num);
						
						
						
					}else {
					int num = Integer.parseInt(t);
					combinaison.add(num);
					error = false;
					}

				} catch(Exception e) {
					System.out.println("Combinaison non valide veuillez réessayer \n");
					error = true;
					addHumanNumber( j, conf);
					break ;
				}	
			}
		} else {
			System.out.println("Vous n'etes pas prêt, commencer par apprendre a compter jusqu'à " + conf.getNbCase());
			error = true;
			addHumanNumber(j,conf);
		}
	}
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



