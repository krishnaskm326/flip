package com.genericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	public String readDataFromPropertyFile(String key) throws Throwable {
		FileInputStream fis=new FileInputStream(IpathConstants.FILEPATH);
		Properties pobj=new Properties();
		pobj.load(fis);
		String value = pobj.getProperty(key);
		return value;
	}

}
