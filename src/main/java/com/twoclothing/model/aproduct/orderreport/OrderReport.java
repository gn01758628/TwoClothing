package com.twoclothing.model.aproduct.orderreport;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderreport")
public class OrderReport implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reportid", updatable = false)
	private Integer reportId;

	@Column(name = "orderid", updatable = false, nullable = false)
	private Integer orderId;

	@Column(name = "empid")
	private Integer empId;

	@Column(name = "reportdate", updatable = false, nullable = false)
	private Timestamp reportDate;

	@Column(name = "description", updatable = false, nullable = false)
	private String description;

	@Column(name = "rstatus", columnDefinition = "TINYINT", nullable = false)
	private Integer rStatus;

	@Column(name = "auditdate")
	private Timestamp auditDate;

	@Column(name = "result", columnDefinition = "TINYINT")
	private Integer result;

	@Column(name = "note")
	private String note;

	public OrderReport() {
	}

	public OrderReport(Integer reportId, Integer orderId, Integer empId, Timestamp reportDate, String description,
			Integer rStatus, Timestamp auditDate, Integer result, String note) {
		this.reportId = reportId;
		this.orderId = orderId;
		this.empId = empId;
		this.reportDate = reportDate;
		this.description = description;
		this.rStatus = rStatus;
		this.auditDate = auditDate;
		this.result = result;
		this.note = note;
	}

	@Override
	public String toString() {
		return "OrderReport [reportId=" + reportId + ", orderId=" + orderId + ", empId=" + empId + ", reportDate="
				+ reportDate + ", description=" + description + ", rStatus=" + rStatus + ", auditDate=" + auditDate
				+ ", result=" + result + ", note=" + note + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(auditDate, description, empId, note, orderId, rStatus, reportDate, reportId, result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderReport other = (OrderReport) obj;
		return Objects.equals(auditDate, other.auditDate) && Objects.equals(description, other.description)
				&& Objects.equals(empId, other.empId) && Objects.equals(note, other.note)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(rStatus, other.rStatus)
				&& Objects.equals(reportDate, other.reportDate) && Objects.equals(reportId, other.reportId)
				&& Objects.equals(result, other.result);
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Timestamp getReportDate() {
		return reportDate;
	}

	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRStatus() {
		return rStatus;
	}

	public void setRStatus(Integer rStatus) {
		this.rStatus = rStatus;
	}

	public Timestamp getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}