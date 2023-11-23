package com.twoclothing.model.coupon;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "coupon")
public class Coupon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cpnid")
	private Integer cpnId;
	
	@Column(name = "cpnname")
	private String cpnName;
	
	@Column(name = "tagid")
	private Integer tagId;
	
	@Column(name = "empid")
	private Integer empId;
	
	@Column(name = "createdate")
	private Timestamp createDate;
	
	@Column(name = "expiredate")
	private Timestamp expireDate;
	
	@Column(name = "distype" , columnDefinition = "TINYINT")
	private Integer disType;
	
	@Column(name = "disvalue")
	private Integer disValue;
	
	@Column(name = "minamount")
	private Integer minAmount;

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupon(String cpnName, Integer tagId, Integer empId, Timestamp createDate, Timestamp expireDate, Integer disType,
			Integer disValue, Integer minAmount) {
		super();
		this.cpnName = cpnName;
		this.tagId = tagId;
		this.empId = empId;
		this.createDate = createDate;
		this.expireDate = expireDate;
		this.disType = disType;
		this.disValue = disValue;
		this.minAmount = minAmount;
	}

	public Integer getCpnId() {
		return cpnId;
	}

	public void setCpnId(Integer cpnId) {
		this.cpnId = cpnId;
	}

	public String getCpnName() {
		return cpnName;
	}

	public void setCpnName(String cpnName) {
		this.cpnName = cpnName;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Timestamp expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getDisType() {
		return disType;
	}

	public void setDisType(int disType) {
		this.disType = disType;
	}

	public Integer getDisValue() {
		return disValue;
	}

	public void setDisValue(int disValue) {
		this.disValue = disValue;
	}

	public Integer getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpnId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		return Objects.equals(cpnId, other.cpnId);
	}

	@Override
	public String toString() {
		return "Coupon [cpnId=" + cpnId + ", cpnName=" + cpnName + ", tagId=" + tagId + ", empId=" + empId
				+ ", createDate=" + createDate + ", expireDate=" + expireDate + ", disType=" + disType + ", disValue="
				+ disValue + ", minAmount=" + minAmount + "]";
	}

}
