package fr.jonathan.utility;
import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Load les informations contenues dans le fichier de properties, utile a la configuration du jeux.
 * @author Joe
 *
 */
public class Props {

	final Properties prop = new Properties();
	public InputStream is = null;
	/**
	 * Nombre de case 
	 */
	public int nbCase ;
	/**
	 * Nombre d'essai autorisé
	 */
	public int nbEssai ;
	/**
	 * Est on: en mode dev:true (les solutions sont indiqué) ; joueur: false (Les solutions sont caché)
	 */
	public boolean debug=false ;
	/**
	 * Amplitude des chiffres utilisable pour le MasterMind
	 */
	public int nbMaster ;
	private static final Logger logger = LogManager.getLogger(Props.class);
	public Props() {
		

		try {

			is= new FileInputStream("properties.txt");
			
			prop.load(is);

			nbCase = Integer.parseInt(prop.getProperty("nbCase"));
			
			nbEssai = Integer.parseInt(prop.getProperty("nbEssai"));
		
			debug = Boolean.valueOf(prop.getProperty("debug"));
			
			nbMaster= Integer.parseInt(prop.getProperty("nbMaster"));


		} catch (final IOException ex) {
			ex.printStackTrace();
			
		}catch (final NumberFormatException f) {
			logger.warn("Attention properties est vide");
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
}



