package creditCard;
 
 import java.io.File;
 import java.net.URL;
 import java.net.URLClassLoader;
 import javax.tools.JavaCompiler;
 import javax.tools.ToolProvider;
 import util.ReadConfig;
 
 
 
 public class CreditCardProcessorLoader {
 
 	public CreditCardProcessor getDefaultProcessor(String processorName) {
 		ReadConfig config =  new ReadConfig(); 
 		String sourcePath = config.getWalletPath().concat(processorName).concat(".java");
 		System.out.println(sourcePath);
 		JavaCompiler compilador = ToolProvider.getSystemJavaCompiler();
 		compilador.run(null, null, null, sourcePath);
 		
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