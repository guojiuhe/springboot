package guojiuhe.demo.tools.employee;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import guojiuhe.demo.tools.employee.mapper.EmployeeMapper;
import guojiuhe.demo.utils.Dictionary;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeController{
	
    @Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeMapper employeeMapper;

	org.slf4j.Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Transactional
	public void addEmployeeJpa() {
		// jpa 练习
    	Employee employee = new Employee();
    	employee.setEmployeeId("1222");
    	employee.setEmployeeName("222");
    	employee.setDepartmentName("2as");
    	employee.setJobName("3sa");
    	employee.setEmployeeLevel("4");
    	employee.setEmployeeMgrLevel("");
    	employee.setEmail("5");
    	employee.setPhone("2121");
    	employee.setStatus("7");
    	employee.setRemark("sadfb");
    	employee.setCreateDate(DateUtil.date()); // hutool
    	employeeRepository.saveAndFlush(employee);
    }

	public void addEmployee(String jsonStr) {

		JSONObject jsonObject = new JSONObject(JSON.parseObject(jsonStr));
		JSONObject jsonBody = jsonObject.getJSONObject("body");
		JSONArray jsonInfo = jsonBody.getJSONArray("info");
		for (int i = 0; i < jsonInfo.size(); i ++){
			JSONObject data = jsonInfo.getJSONObject(i);
			String employeeId = data.getString("employeeId");
			EmployeePojo pojo = getEmployeePojo(employeeId);
			if (pojo != null) {
				log.info("已存在" + employeeId);
				continue;
			}

			Employee employee = new Employee();
			employee.setEmployeeId(employeeId);
			employee.setEmployeeName(data.getString("employeeName"));
			employee.setDepartmentName(data.getString("branchName"));
			employee.setJobName(data.getString("postName"));
			employee.setEmployeeLevel(data.getString("employeeLevel"));
			employee.setEmployeeMgrLevel(data.getString("employeeMgrLevel"));
			employee.setEmail(data.getString("email"));
			employee.setPhone(data.getString("phone"));
			employee.setStatus(Dictionary.EmployeeStatus.Active.getStr());
			employee.setRemark("");
			employee.setCreateDate(DateUtil.date()); // hutool
			//employee.setCreateDate(DateUtils.getCurrentDate());

			employeeMapper.addEmployee(employee);

		}
	}

	public EmployeePojo getEmployeePojo(String employeeId) {
		EmployeePojo employee = employeeMapper.getEmployeeByEmployeeId(employeeId);


		return employee;
	}
	public JSONObject getEmployee(String employeeId) {
		EmployeePojo employee = employeeMapper.getEmployeeByEmployeeId(employeeId);
		if (employee != null) {
			log.info("exist " + employeeId);
		}
		else {
			log.info("not exist" + employeeId);
		}
		JSONObject jsonObject = (JSONObject) JSON.toJSON(employee);
		return jsonObject;
	}
}
