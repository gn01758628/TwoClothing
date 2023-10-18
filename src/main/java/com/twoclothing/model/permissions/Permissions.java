package com.twoclothing.model.permissions;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "permissions")
public class Permissions implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permissionid", updatable = false)
	private Integer permissionId;
	
	@Column(name = "permissionname")
	private String permissionName;
	
	@Column(name = "descriptions")
	private String descriptions;

	public Permissions() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 帶參數建構子
	public Permissions(String permissionName, String descriptions) {
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
		Permissions other = (Permissions) obj;
		return Objects.equals(permissionId, other.permissionId) && Objects.equals(permissionName, other.permissionName);
	}

	@Override
	public String toString() {
		return "Permissions [permissionId=" + permissionId + ", permissionName=" + permissionName + ", descriptions="
				+ descriptions + "]";
	}
}
