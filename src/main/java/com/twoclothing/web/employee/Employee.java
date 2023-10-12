package com.twoclothing.web.employee;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
	
	@Column(name = "empstatus")
	private Integer empStatus;
	
	@Column(name = "avatar", columnDefinition = "longblob")	
	private byte[] avatar;

	public Employee() {
		super();
	}

	public Employee(Integer deptId, String empName, String phone, String address, String email, String pswdHash,
			Integer empStatus, byte[] avatar) {
		super();
		this.deptId = deptId;
		this.empName = empName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.pswdHash = pswdHash;
		this.empStatus = empStatus;
		this.avatar = avatar;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
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
				+ ", avatar=" + avatarStr + "]";
	}

}
