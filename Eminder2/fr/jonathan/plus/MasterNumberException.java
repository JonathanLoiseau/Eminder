package fr.jonathan.plus;

public class MasterNumberException extends Exception {
	Props prop = new Props();
	public MasterNumberException() {
		System.out.println("\nVotre combinaison ne doit contenir que des chiffres compris entre 1 et " + prop.nbMaster+"\n");
	}

}
