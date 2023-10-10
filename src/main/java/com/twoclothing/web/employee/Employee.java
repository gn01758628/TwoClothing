package com.twoclothing.web.employee;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Employee implements Serializable {
	private Integer empId;
	private Integer deptId;
	private String empName;
	private String phone;
	private String address;
	private String email;
	private String pswdHash;
	private Integer empStatus;
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
		return "Employee [empId=" + empId + ", deptId=" + deptId + ", empName=" + empName + ", phone=" + phone
				+ ", address=" + address + ", email=" + email + ", pswdHash=" + pswdHash + ", empStatus=" + empStatus
				+ ", avatar=" + Arrays.toString(avatar) + "]";
	}

}
