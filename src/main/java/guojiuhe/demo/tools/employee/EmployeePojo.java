package guojiuhe.demo.tools.employee;

import java.io.Serializable;
import java.util.Date;

public class EmployeePojo implements Serializable{
	private long id;
	private String employeeId;
	private String employeeName;
	private String departmentName;
	private String jobName;
	private String employeeLevel;
	private String employeeMgrLevel;
	private String email;
	private String phone;
	private String status;
	private String remark;
	private Date createDate;
	private Date updateDate;

	@Override
	public String toString(){
		return "Employee{" + "employeeId=" + employeeId + ", employeeName='" + employeeName + '\'' + ", departmentName='" + departmentName + '\'' + ", jobName='" + jobName + '\'' + ", employeeLevel='" + employeeLevel + '\'' + ", employeeMgrLevel='" + employeeMgrLevel + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", status='" + status + '\'' + ", remark='" + remark + '\'' + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id = id;
	}

	public String getEmployeeId(){
		return employeeId;
	}

	public void setEmployeeId(String employeeId){
		this.employeeId = employeeId;
	}

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}

	public void setJobName(String jobName){
		this.jobName = jobName;
	}

	public void setEmployeeLevel(String employeeLevel){
		this.employeeLevel = employeeLevel;
	}

	public void setEmployeeMgrLevel(String employeeMgrLevel){
		this.employeeMgrLevel = employeeMgrLevel;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

	public String getEmployeeName(){
		return employeeName;
	}

	public String getDepartmentName(){
		return departmentName;
	}

	public String getJobName(){
		return jobName;
	}

	public String getEmployeeLevel(){
		return employeeLevel;
	}

	public String getEmployeeMgrLevel(){
		return employeeMgrLevel;
	}

	public String getEmail(){
		return email;
	}

	public String getPhone(){
		return phone;
	}

	public String getStatus(){
		return status;
	}

	public String getRemark(){
		return remark;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public Date getUpdateDate(){
		return updateDate;
	}
}