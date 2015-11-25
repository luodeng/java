package com.roden.java.algorithm.encrypt;

/*
 * 密码一般需要使用某种算法加密后保存，以免数据库管理员能够从数据表中直接窥视到用户的密码。本例使用MD5算法加密。MD5算法是个标准的算法，它加密的特点是不可逆性，即使知道了加密后的密码，也不可能解密到原使密码。
   JDK中自带MD5加密的核心算法，但是没有提供可直接利用的加密方法，因此需要自己封装一下。MD5加密的代码如下：
          注意使用了MD5加密的系统不具有找回密码的功能，因为它的加密是不可逆的，只能使用重置密码。如果需要找回密码。可以使用其它加密算法，例如DES,SHA等
*/
import java.security.MessageDigest;

public class MD5Util {

	public final static String calc(String ss) {

		String s = ss == null ? "" : ss;

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String key="idfs32idnsah;gfp";
		String account="luodeng";
		String ssid=calc(account+key);
		System.out.println(ssid);

		System.out.print(MD5Util.calc(""));
	}
}