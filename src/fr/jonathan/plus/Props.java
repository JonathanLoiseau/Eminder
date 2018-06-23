package fr.jonathan.plus;
import java.io.FileInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Props {

	final Properties prop = new Properties();
	public InputStream is = null;
	public int nbCase = 5;
	public int nbEssai =4;
	public boolean debug=false ;
	public int nbMaster = 5;
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



