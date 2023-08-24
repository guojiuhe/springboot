package guojiuhe.demo.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisTemplate{

	Jedis jedis = null;

	public RedisTemplate(){
		//连接本地的 Redis 服务
		jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("123456");
		//查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
	}

	public void setRedisList(String listName){
		jedis.lpush(listName, "123456");
		jedis.lpush(listName, "hahaha");
		jedis.lpush(listName, "qqqqqq");
	}

	public List<String> getRedisList(String listName){
		List<String> list = jedis.lrange(listName, 0 ,100);
		for(int i=0; i<list.size(); i++) {
			System.out.println("列表项为: "+list.get(i));
		}
		return list;
	}

}
