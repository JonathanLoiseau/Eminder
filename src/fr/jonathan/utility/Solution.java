package fr.jonathan.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * La solution que l'attaquant devra trouver
 * @author Joe
 *
 */
public class Solution {
	/**
	 * La combinaison représentant la solution
	 */
	private List<Integer> combinaison = new ArrayList<Integer>();
	/**
	 * C'est l'objet qui est chargé de générer la combinaison.
	 */
	private CombGenerator combG = new CombGenerator();
	
	

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
		return combG.getError();
		}
	public void setCombinaison(List<Integer> list){
		combinaison = new ArrayList<>(list);
		}
	public List<Integer> getCombinaison(){
		return combinaison;
	}
	
	public boolean contains(int i) {
		return combinaison.contains(i);
		}
	}
	




