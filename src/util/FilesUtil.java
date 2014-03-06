
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FilesUtil {
	
	
		/*
	 * (non-Javadoc)
	 * @see co.com.quipux.entapp.qxvehiculos.utils.QxFiles#read(java.io.File)
	 */
	public byte[] read(String urlFile) throws IOException {
		File file=new File(urlFile);
		InputStream is = new FileInputStream(file); // Get the size of the file 
		long length = file.length(); // You cannot create an array using a long type. 
		// It needs to be an int type. 
		// Before converting to an int type, check 
		// to ensure that file is not larger than Integer.MAX_VALUE. 
		if (length > Integer.MAX_VALUE) { 
			// File is too large 
			} // Create the byte array to hold the data 
		byte[] bytes = new byte[(int)length]; // Read in the bytes 
		int offset = 0; 
		int numRead = 0; 
		while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) { 
			offset += numRead; 
			} 
		// Ensure all the bytes have been read in 
		if (offset < bytes.length) { 
			is.close();
			throw new IOException("Could not completely read file "+file.getName()); 
			} // Close the input stream and return bytes 
		is.close(); 
		return bytes; 
	}
	
	public List<String> getListDirectory(String rutaBase){
		List<String> listArchivos=new ArrayList<String>();
		File file = new File(rutaBase);
		String[] ficheros = file.list();
		new File(rutaBase).isDirectory();
		for (String fichero : ficheros) {
			if((ficheros[0].indexOf(".txt")>0 ||ficheros[0].indexOf(".jpg")>0 ||ficheros[0].indexOf(".vid")>0)){
				continue;
			}else{
				listArchivos.add(rutaBase+"/"+fichero);
				listArchivos.addAll(getListDirectory(rutaBase+"/"+fichero));
			}
		}
		return listArchivos;
	}
	

	/**
	 * Closes InputStream and/or OutputStream. It makes sure that both streams tried to be closed, even first throws an exception.
	 * 
	 * @throw IOException if stream (is not null and) cannot be closed.
	 * 
	 */
	protected void close(InputStream iStream, OutputStream oStream) throws IOException {
		try {
			if (iStream != null) {
				iStream.close();
			}
		} finally {
			if (oStream != null) {
				oStream.close();
			}
		}
	}

}