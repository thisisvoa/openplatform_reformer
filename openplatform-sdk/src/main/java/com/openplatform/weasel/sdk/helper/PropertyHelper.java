package com.openplatform.weasel.sdk.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * @author dylan
 * @email pickear@gmail.com
 * @time 2015年6月15日
 */
public final class PropertyHelper {

	private static Properties properties = new Properties();

	private PropertyHelper(String fileName) {
		InputStream is = PropertyHelper.class.getResourceAsStream(fileName);
		if (null != is) {
			try {
				properties.load(is);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	/**get Properties Object
	 * @return
	 */
	public Properties getProperties(){
		return properties;
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static PropertyHelper read(String fileName) {
		try {
			return new PropertyHelper(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
