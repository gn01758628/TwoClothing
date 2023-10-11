package com.twoclothing.web.department;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "department")
public class Department implements Serializable {
	@Id
	@Column(name = "deptid")
	public Integer deptId; // 部門編號
	@Column(name = "deptname")
	public String deptName; // 部門名稱

	public Department() {
		
	}

	public Department(String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deptId, deptName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(deptId, other.deptId) && Objects.equals(deptName, other.deptName);
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + "]";
	}

}
