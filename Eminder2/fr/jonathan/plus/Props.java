package fr.jonathan.plus;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Props {

	final Properties prop = new Properties();
	public InputStream is = null;
	public int nbCase = 5;
	public int nbEssai =4;
	public String debug="dev" ;
	public int nbMaster = 5;
	public Props() {

		try {

			is= new FileInputStream("properties.txt");
			
			prop.load(is);

			nbCase = Integer.parseInt(prop.getProperty("nbCase"));
			
			nbEssai = Integer.parseInt(prop.getProperty("nbEssai"));
		
			debug = (prop.getProperty("debug"));
			
			nbMaster= Integer.parseInt(prop.getProperty("nbMaster"));


		} catch (final IOException ex) {
			ex.printStackTrace();
			
		}catch (final NumberFormatException f) {
			System.out.println("Attention properties est vide");
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



