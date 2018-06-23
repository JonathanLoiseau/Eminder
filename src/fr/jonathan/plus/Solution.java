package fr.jonathan.plus;

import java.util.ArrayList;
import java.util.List;


public class Solution {
	private List<Integer> combinaison = new ArrayList<Integer>();
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
	




