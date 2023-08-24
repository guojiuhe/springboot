package guojiuhe.demo.tools.employee;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name="tf_employee")
public class Employee{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="employee_id")
	private String employeeId;
	@Column(name="employee_name")
	private String employeeName;
	@Column(name="department_name")
	private String departmentName;
	@Column(name="job_name")
	private String jobName;
	@Column(name="employee_level")
	private String employeeLevel;
	@Column(name="employee_mgr_level")
	private String employeeMgrLevel;
	@Column(name="email")
	private String email;
	@Column(name="phone")
	private String phone;
	@Column(name="status")
	private String status;
	@Column(name="remark")
	private String remark;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="update_date")
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