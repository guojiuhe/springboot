package guojiuhe.demo.tools.employee.mapper;


import guojiuhe.demo.tools.employee.Employee;
import guojiuhe.demo.tools.employee.EmployeePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper{

	int addEmployee(Employee employee);

	EmployeePojo getEmployeeByEmployeeId(String employeeId);
}
