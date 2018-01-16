package com.zp.dubbo.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisApi {
	private static JedisPool pool;
	
	private static Properties prop = null;
	
	private static JedisPoolConfig config = null;
	static{
		InputStream in = RedisApi.class.getClassLoader().getResourceAsStream("com/zp/dubbo/redis/redis.properties");
		prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config = new JedisPoolConfig();
		config.setMaxTotal(Integer.valueOf(prop.getProperty("MAX_TOTAL")));
		config.setMaxIdle(Integer.valueOf(prop.getProperty("MAX_IDLE")));
		config.setMaxWaitMillis(Long.valueOf(prop.getProperty("MAX_WAIT_MILLIS")));
		config.setTestOnBorrow(Boolean.valueOf(prop.getProperty("TEST_ON_BORROW")));
		config.setTestOnReturn(Boolean.valueOf(prop.getProperty("TEST_ON_RETURN")));
		config.setTestWhileIdle(Boolean.valueOf(prop.getProperty("TEST_WHILE_IDLE")));
	}
	
	public static void createJedisPool(String address){
		pool = new JedisPool(config,address.split(":")[0],Integer.valueOf(address.split(":")[1]),100000);
	}

	public static boolean exists(String key){
		Jedis jedis = null;
		boolean value = false;
		try {
			jedis = pool.getResource();
			value = jedis.exists(key);
		} catch (Exception e) {
			
		}finally{
			if(jedis!=null){
				returnResource(pool,jedis);
			}
			
		}
		return value;
	}
	
	public static void returnResource(JedisPool pool,Jedis jedis){
		if(jedis!=null){
			pool.returnResource(jedis);
		}
	}
}
