package com.twoclothing.web.employeemission;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeMission implements Serializable{
    private Integer empId;
    private Integer permissionId;

    public EmployeeMission() {
		super();
	}

	public EmployeeMission(Integer empId, Integer permissionId) {
        this.empId = empId;
        this.permissionId = permissionId;
    }

    // Getter 和 Setter 方法
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(empId, permissionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeMission other = (EmployeeMission) obj;
		return Objects.equals(empId, other.empId) && Objects.equals(permissionId, other.permissionId);
	}

	@Override
	public String toString() {
		return "EmployeeMission [empId=" + empId + ", permissionId=" + permissionId + "]";
	}
}
