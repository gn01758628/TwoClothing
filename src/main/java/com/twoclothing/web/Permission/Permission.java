package com.twoclothing.web.Permission;

import java.io.Serializable;
import java.util.Objects;

public class Permission implements Serializable {

	private Integer permissionId;
	private String permissionName;
	private String descriptions;

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 帶參數建構子
	public Permission(Integer permissionId, String permissionName, String descriptions) {
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.descriptions = descriptions;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(permissionId, permissionName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		return Objects.equals(permissionId, other.permissionId) && Objects.equals(permissionName, other.permissionName);
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", permissionName=" + permissionName + ", descriptions="
				+ descriptions + "]";
	}
}
