package guojiuhe.controller;

import com.alibaba.fastjson.JSONObject;
import guojiuhe.demo.tools.employee.EmployeeController;
import guojiuhe.demo.utils.SpringBeanUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexControrller {

	@GetMapping("/getEmployee")
	//http://localhost:8080/getEmployee?employeeId=001911
	public JSONObject getEmployee(@RequestParam(value = "employeeId") String employeeId) {
		EmployeeController employeeController = SpringBeanUtil.getBean("employeeController");
		JSONObject result = employeeController.getEmployee(employeeId);
		return result;
	}

	@GetMapping("/addEmployee")
	public String addEmployee() {
		String jsonStr = "";
		EmployeeController employeeController = SpringBeanUtil.getBean("employeeController");
		employeeController.addEmployee(jsonStr);
		return "hello world";
	}
	
	@GetMapping("/vue/demo")
    public String vueDemo() {
    	
        return "hello vue";
    }
}
