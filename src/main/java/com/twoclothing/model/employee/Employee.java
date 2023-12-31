package com.twoclothing.model.employee;

import javax.persistence.*;

import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name= "employee")
public class Employee implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empid", updatable = false)
	private Integer empId;
	
	@Column(name = "deptid")
	private Integer deptId;
	
	@Column(name = "empname")
	private String empName;
		
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
		
	@Column(name = "pswdhash")
	private String pswdHash;
	
	@Column(name = "empstatus", columnDefinition = "TINYINT")
	private Integer empStatus;
	
	@Column(name = "avatar", columnDefinition = "MEDIUMBLOB")
	private byte[] avatar;

	@Column(name = "empmissions")
	private String empMissions;
	
	@Formula("LPAD(empId, 5, '0')")
	private String formatEmpId;

	

	public Employee() {
		super();
		this.empMissions="00000000";
	}

	public Employee(Integer deptId, String empName, String phone, String address, String email, String pswdHash,
			Integer empStatus, byte[] avatar,String empMissions) {
		super();
		this.deptId = deptId;
		this.empName = empName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.pswdHash = pswdHash;
		this.empStatus = empStatus;
		this.avatar = avatar;
		this.empMissions= empMissions;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
		this.formatEmpId = String.format("%05d", empId);
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPswdHash() {
		return pswdHash;
	}

	public void setPswdHash(String pswdHash) {
		this.pswdHash = pswdHash;
	}

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
	public String getEmpMissions() {
		return empMissions;
	}

	public void setEmpMissions(String empMissions) {
		this.empMissions = empMissions;
	}

	public void setFormatEmpId() {
		this.formatEmpId = String.format("%05d", empId);;
	}

	public String getFormatEmpId() {
		return formatEmpId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(empId, other.empId);
	}

	@Override
	public String toString() {
		String avatarStr ;
		if( avatar == null ) {
			avatarStr="null";
		}else {
			avatarStr = String.valueOf(avatar.length);
		}
		
		return "Employee [empId=" + empId + ", deptId=" + deptId + ", empName=" + empName + ", phone=" + phone
				+ ", address=" + address + ", email=" + email + ", pswdHash=" + pswdHash + ", empStatus=" + empStatus
				+ ", avatar=" + avatarStr + ", empmissions="+empMissions+", formatEmpId=" +formatEmpId+ "]";
	}

	  public com.twoclothing.model.department.Department getDepartment() {
		  com.twoclothing.tonyhsieh.service.DepartmentServiceImpl deptSvc = new com.twoclothing.tonyhsieh.service.DepartmentServiceImpl();
		  com.twoclothing.model.department.Department department = deptSvc.getByPrimaryKey(deptId);
		    return department;
	    }
	
	
}
