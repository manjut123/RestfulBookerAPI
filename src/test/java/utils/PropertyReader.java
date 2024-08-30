package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyReader {
	
	static String filepath="C:\\Users\\Manjushree\\eclipse-workspace\\TestBrokerAPI\\src\\test\\java\\data\\dataread.properties";
	
	public static String readproperty(String key) throws IOException
	{
	Properties prop=new Properties();
	
	FileInputStream file=new FileInputStream(filepath);
	
	prop.load(file);
	
	String propertyVal=prop.getProperty(key);
	
	return propertyVal;	
	
   }
	
	
}