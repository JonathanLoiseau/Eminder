package fr.jonathan.utility;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * L'objet représentant la tentative de l'attaquant
 * @author Joe
 *
 */
public class Tentative {
	/**
	 * la combinaison représentant la tentative
	 */
	private List<Integer> combinaison = new ArrayList<Integer>();
	/**
	 * Objet chargé de la génération de combinaison
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
	}

