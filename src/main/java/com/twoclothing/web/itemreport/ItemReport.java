package com.twoclothing.web.itemreport;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ItemReport implements Serializable {
	private Integer reportId;
	private Integer itemId;
	private Integer mbrID;
	private Integer empId;
	private Timestamp reportDate;
	private String description;
	private Integer rStatus;
	private Timestamp auditDate;
	private Integer result;
	private String note;
	
	public ItemReport() {
	}
	
	public ItemReport(Integer reportId, Integer itemId, Integer mbrID, Integer empId, Timestamp reportDate,
			String description, Integer rStatus, Timestamp auditDate, Integer result, String note) {
		this.reportId = reportId;
		this.itemId = itemId;
		this.mbrID = mbrID;
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
		return "ItemReport [reportId=" + reportId + ", itemId=" + itemId + ", mbrID=" + mbrID + ", empId=" + empId
				+ ", reportDate=" + reportDate + ", description=" + description + ", rStatus=" + rStatus
				+ ", auditDate=" + auditDate + ", result=" + result + ", note=" + note + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(auditDate, description, empId, itemId, mbrID, note, rStatus, reportDate, reportId, result);
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
				&& Objects.equals(mbrID, other.mbrID) && Objects.equals(note, other.note)
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
	
	public Integer getMbrID() {
		return mbrID;
	}
	
	public void setMbrID(Integer mbrID) {
		this.mbrID = mbrID;
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
	
	public Integer getrStatus() {
		return rStatus;
	}
	
	public void setrStatus(Integer rStatus) {
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
