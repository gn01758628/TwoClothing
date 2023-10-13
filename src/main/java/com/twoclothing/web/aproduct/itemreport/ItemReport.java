package com.twoclothing.web.aproduct.itemreport;

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
@Table(name = "itemreport")
public class ItemReport implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reportid", updatable = false)
	private Integer reportId;

	@Column(name = "itemid", updatable = false)
	private Integer itemId;

	@Column(name = "mbrid", updatable = false)
	private Integer mbrId;

	@Column(name = "empid")
	private Integer empId;

	@Column(name = "reportdate", updatable = false)
	private Timestamp reportDate;

	@Column(name = "description", updatable = false)
	private String description;

	@Column(name = "rstatus", columnDefinition = "TINYINT")
	private Integer rStatus;

	@Column(name = "auditdate")
	private Timestamp auditDate;

	@Column(name = "result", columnDefinition = "TINYINT")
	private Integer result;

	@Column(name = "note")
	private String note;

	public ItemReport() {
	}

	public ItemReport(Integer reportId, Integer itemId, Integer mbrId, Integer empId, Timestamp reportDate,
			String description, Integer rStatus, Timestamp auditDate, Integer result, String note) {
		this.reportId = reportId;
		this.itemId = itemId;
		this.mbrId = mbrId;
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
		return "ItemReport [reportId=" + reportId + ", itemId=" + itemId + ", mbrID=" + mbrId + ", empId=" + empId
				+ ", reportDate=" + reportDate + ", description=" + description + ", rStatus=" + rStatus
				+ ", auditDate=" + auditDate + ", result=" + result + ", note=" + note + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(auditDate, description, empId, itemId, mbrId, note, rStatus, reportDate, reportId, result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemReport other = (ItemReport) obj;
		return Objects.equals(auditDate, other.auditDate) && Objects.equals(description, other.description)
				&& Objects.equals(empId, other.empId) && Objects.equals(itemId, other.itemId)
				&& Objects.equals(mbrId, other.mbrId) && Objects.equals(note, other.note)
				&& Objects.equals(rStatus, other.rStatus) && Objects.equals(reportDate, other.reportDate)
				&& Objects.equals(reportId, other.reportId) && Objects.equals(result, other.result);
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getMbrId() {
		return mbrId;
	}

	public void setMbrId(Integer mbrId) {
		this.mbrId = mbrId;
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