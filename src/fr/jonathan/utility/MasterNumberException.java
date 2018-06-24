package fr.jonathan.utility;
/**
 * Exeption lev�e si l'utilisateur tente de saisir un chiffre non autoris� ou pr�vu lors de la configuration du jeux.
 * @author Joe
 *
 */
public class MasterNumberException extends Exception {

	public MasterNumberException(int maxValue) {
		super("Votre combinaison ne doit contenir que des chiffres compris entre 1 et " + maxValue);
	}

}
