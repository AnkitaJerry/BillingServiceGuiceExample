package util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.InputStream;
import java.lang.ClassLoader;
import java.lang.Thread;
import java.util.ArrayList;

public class ReadConfig {
	
	private String walletPath;
	private ArrayList<String> availableCreditCardProcessors;
	
	public ReadConfig(){
		this.availableCreditCardProcessors = new ArrayList<String>();
		try {
			 	
			ClassLoader t = Thread.currentThread().getContextClassLoader();
			InputStream file = t.getResourceAsStream("config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			this.walletPath = doc.getDocumentElement().getAttribute("location");
			
			NodeList processors = doc.getElementsByTagName("creditcardprocessor");
			for(int i=0;i< processors.getLength();i++){
				Node processor = processors.item(i);
				if (processor.getNodeType() == Node.ELEMENT_NODE) {
					Element p = (Element) processor;
					
					this.availableCreditCardProcessors.add(
							p.getElementsByTagName("name").item(0).getTextContent());
				}	
			}	
		 }catch (Exception e) {
			e.printStackTrace();
		 }
	}
	
	public ArrayList<String> getAvailableCreditCardProcessors(){
		return this.availableCreditCardProcessors;
	}
	
	
	public String getWalletPath(){
		return this.walletPath;
	}
	
	public boolean checkIfSupportedProcessor(String processorName){
		return this.availableCreditCardProcessors.contains(processorName);
	}
	
	public static void main(String[] args){
		ReadConfig c = new ReadConfig();
		System.out.println(c.getAvailableCreditCardProcessors());
		System.out.println(c.getWalletPath());
		boolean s = c.checkIfSupportedProcessor("Default");
		System.out.println(s);
	}
}
