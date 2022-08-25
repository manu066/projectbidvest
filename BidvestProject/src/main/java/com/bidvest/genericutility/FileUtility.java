package com.bidvest.genericutility;


import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.Icon;


/**
 * it;s contains External File specific libraries
 * @author Manu
 *
 */
public class FileUtility {
	/**
	 *   its used return the value from the property file based on key
	 * @param key
	 * @return value
	 * @throws Throwable
	 */
		public String getPropertyKeyValue(String key) throws Throwable {
			
			FileInputStream fis = new FileInputStream(IConstatnts.PropertyFilePath);
			Properties pObj = new Properties();
			pObj.load(fis);
			String value = pObj.getProperty(key);
			return value;
			
		}


}
