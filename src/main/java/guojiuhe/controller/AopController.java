package guojiuhe.controller;

import guojiuhe.demo.aop.AopTestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

	@Autowired
	AopTestModel aopTestModel;
	
	@GetMapping("/testAop")
	public String testAop() {
		
		//AopTestModel aopTestModel = SpringBeanUtil.getBean("aopTestModel");
		aopTestModel.start("sdf",2);
		return "hello world";
	}
}
