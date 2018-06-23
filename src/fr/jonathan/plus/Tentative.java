package fr.jonathan.plus;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tentative {
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
	}

