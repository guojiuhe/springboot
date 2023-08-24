package guojiuhe.demo.aop;

import org.springframework.stereotype.Component;

@Component
public class AopTestModel {
	
	
	public AopTestModel(){
		
	}
	
	@ViewRecords(id = 89l)
	public void start(String status,int index) {
		System.out.println("AopTestModel start");
	}

}
