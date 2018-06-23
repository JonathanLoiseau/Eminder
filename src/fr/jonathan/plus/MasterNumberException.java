package fr.jonathan.plus;

public class MasterNumberException extends Exception {

	public MasterNumberException(int maxValue) {
		super("Votre combinaison ne doit contenir que des chiffres compris entre 1 et " + maxValue);
	}

}
