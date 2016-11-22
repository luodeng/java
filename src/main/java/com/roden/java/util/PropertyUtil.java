package com.roden.java.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 获取资源文件键值对.
 * 
 */
public final class PropertyUtil {

	private static Properties properties = new Properties();

	/**
	 * 
	 * @param key
	 *            -
	 * @param classPathFileName
	 *            CLASSPATH 根路径下的资源文件.
	 * @return -
	 */
	public static String getProperty(String key, String classPathFileName) {
		if (!classPathFileName.startsWith("/")) {
			classPathFileName = "/" + classPathFileName;
		}
		if (properties.containsKey(key)) {
			return properties.getProperty(key);
		}
		try {
			System.out.println(PropertyUtil.class
					.getResourceAsStream(classPathFileName));
			properties.load(PropertyUtil.class
					.getResourceAsStream(classPathFileName));
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}