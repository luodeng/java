package com.roden.java.database.memcache;

import java.util.Arrays;
import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcacheHelper {
	private MemCachedClient mcc = new MemCachedClient();
	
	private static MemcacheHelper uniqueInstance1 = null;

	public static MemcacheHelper getInstance() {
		if (uniqueInstance1 == null) {
			uniqueInstance1 = new MemcacheHelper();
			// init();
		}
		return uniqueInstance1;
	}

	private MemcacheHelper() {
		try {
			// "localhost:11211"
			// 设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器。这里应该设置为多个不同的服务，我这里将两个服务设置为一样的，大家不要向我学习，呵呵。
			// String[] servers = { "10.15.0.215:46697", "10.15.0.215:46697","server3.mydomain.com:1624"};
			String[] servers = "127.0.0.1:11211,192.168.1.111:11211".split(",");// { "localhost:11211" };
			// 设置服务器权重
			// Integer[] weights = {3,2,2};
			String[] sweights = "3,5".split(",");
			Integer[] weights = new Integer[sweights.length]; 
			
			for (int i = 0; i < sweights.length; i++) {
				weights[i] = Integer.parseInt(sweights[i]);
			}

			// 创建一个Socked连接池实例
			SockIOPool pool = SockIOPool.getInstance();

			// 向连接池设置服务器和权重
			pool.setServers(servers);
			pool.setWeights(weights);
			//pool.setInitConn(5);  
		    //pool.setMinConn(5);  
		    //pool.setMaxConn(200);  
		    //pool.setMaxIdle(1000*30*30);  
		    // pool.setMaintSleep(30);  
			// set some TCP settings
			// disable nagle
			// set the read timeout to 3 secs
			// and don't set a connect timeout
			pool.setNagle(false);
			pool.setSocketTO(3000);
			pool.setSocketConnectTO(0);

			// initialize the connection pool
			pool.initialize();
			// mcc = new MemCachedClient();
			String [] s  =pool.getServers();  
			System.out.println(Arrays.toString(s));
			//mcc.setCompressEnable(true);  
			//mcc.setCompressThreshold(1000*1024); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean add(String key, Object value) {
		if (exist(key))return mcc.replace(key, value);
		return mcc.add(key, value);
	}

	public boolean add(String key, Object value, Date expiry) {
		if (exist(key))return mcc.replace(key, value, expiry);
		return mcc.add(key, value, expiry);
	}

	public boolean replace(String key, Object value) {
		if (exist(key))
			return mcc.replace(key, value);
		else
			return add(key, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		if (exist(key))
			return mcc.replace(key, value, expiry);
		else
			return add(key, value, expiry);
	}

	public boolean delete(String key) {
		if (!exist(key))
			return true;
		return mcc.delete(key);
	}

	public boolean exist(String key) {
		return mcc.keyExists(key);
	}

	public Object get(String key) {
		return mcc.get(key);
	}
	public static void main(String[] args) {
		//MemcacheHelper.getInstance().add("sb", "250");
		System.out.println(MemcacheHelper.getInstance().get("sb"));
}
}
