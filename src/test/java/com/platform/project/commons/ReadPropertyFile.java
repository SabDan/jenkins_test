//package com.platform.project.commons;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//import org.apache.log4j.Logger;
//
//// static has been added - no longer need objs - just the class itself
//public class ReadPropertyFile { // reading from file that has browser info
//	private static String configFileLocation = "./src/test/resources/config.properties";
//	private static Logger logger = Logger.getLogger(ReadPropertyFile.class); // replaces console
//
//	
//	private static String readFile(String file, String key) { // returning the value
//		String value = null;
//		
//		try { // Properties class handles the parsing
//			Properties prop = new Properties(); // how we're interacting with with the file
//			FileInputStream in = new FileInputStream(file);
//			prop.load(in);
//			
//			value = prop.getProperty(key); // returning value
//		} catch(IOException ioe) {
//			ioe.printStackTrace();
//			logger.info("Couldn't locate the property of the file");
//		}
//		logger.info("Value in property file for " + key + "is" + value);
//		return value;
//	}
//	
//	
//	// One reason to have it like this is if you have more than one property files to work with
//	public static String getConfigPropertyVal(final String key) { // final- key cannot be changed
//		String configPropertyVal = readFile(configFileLocation, key);
//		
////		if(configPropertyVal != null) {
////			return configPropertyVal.trim(); // gets rid of spaces in the file
////			
////		} else {
////			return configPropertyVal;
////		}
//		// return configProperty.trim(); will return a null Pointer and trim() messes things up
//		return configPropertyVal != null ? configPropertyVal.trim() : configPropertyVal.trim(); //instead of if else
//	}
//}
