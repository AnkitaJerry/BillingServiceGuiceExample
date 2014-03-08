package creditCard;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import util.ReadConfig;

/**
 * Class to handle the loading class process, first it reads the  
 * config file to check where are the classes to be loaded and load
 * the requested class.
 * @param  processorName  String with the name of the class to be loaded
 * @return      Requested class instance
 */

public class CreditCardProcessorLoader {

	public CreditCardProcessor getDefaultProcessor(String processorName) {
		
		// Create a File object on the root of the directory containing the class file
		File file = new File( new ReadConfig().getWalletPath());

		try {
		    // Convert File to a URL
		    URL url = file.toURI().toURL();
		    URL[] urls = new URL[]{url};

		    // Create a new class loader with the directory
		    ClassLoader cl = new URLClassLoader(urls);
	
		    Class<?> cls = cl.loadClass(processorName);
		    
		    return (CreditCardProcessor)cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
