package guojiuhe.controller;

import guojiuhe.demo.redis.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RedisController{

	@GetMapping("/redis/setRedisList/{listName}")
	public String testSetRedisList(@PathVariable("listName") String listName) {

		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setRedisList(listName);
		return "setRedisList ok";
	}
	@GetMapping("/redis/getRedisList/{listName}")
	public String testGetRedisList(@PathVariable("listName") String listName) {
		RedisTemplate redisTemplate = new RedisTemplate();
		List<String> list = redisTemplate.getRedisList(listName);
		return list.stream().collect(Collectors.joining(", "));
	}
}
