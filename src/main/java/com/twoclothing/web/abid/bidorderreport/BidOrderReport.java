package com.twoclothing.web.abid.bidorderreport;

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
@Table(name = "bidorderreport")
public class BidOrderReport  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reportid", updatable = false)
	private Integer reportId;
	
	@Column(name = "bidorderid", updatable = false)
	private Integer bidOrderId;
	
	@Column(name = "empid")
	private Integer empId;
	
	@Column(name = "reportdate", updatable = false)
	private Timestamp reportDate;
	
	@Column(name = "biddescription", updatable = false)
	private String bidDescription;
	
	@Column(name = "bidstatus")
	private Integer bidStatus;
	
	@Column(name = "auditdate")
	private Timestamp auditDate;
	
	@Column(name = "result")
	private Integer result;
	
	@Column(name = "note")
	private String note;

	public BidOrderReport() {
		super();
	}
	

	public BidOrderReport(Integer reportId, Integer bidOrderId, Integer empId, Timestamp reportDate,
			String bidDescription, Integer bidStatus, Timestamp auditDate, Integer result, String note) {
		super();
		this.reportId = reportId;
		this.bidOrderId = bidOrderId;
		this.empId = empId;
		this.reportDate = reportDate;
		this.bidDescription = bidDescription;
		this.bidStatus = bidStatus;
		this.auditDate = auditDate;
		this.result = result;
		this.note = note;
	}

	@Override
	public String toString() {
		return "BidOrderReport [reportId=" + reportId + ", bidOrderId=" + bidOrderId + ", empId=" + empId
				+ ", reportDate=" + reportDate + ", bidDescription=" + bidDescription + ", bidStatus=" + bidStatus
				+ ", auditDate=" + auditDate + ", result=" + result + ", note=" + note + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(auditDate, bidDescription, bidOrderId, bidStatus, empId, note, reportDate, reportId,
				result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		BidOrderReport other = (BidOrderReport) obj;
		return Objects.equals(auditDate, other.auditDate) && Objects.equals(bidDescription, other.bidDescription)
				&& Objects.equals(bidOrderId, other.bidOrderId) && Objects.equals(bidStatus, other.bidStatus)
				&& Objects.equals(empId, other.empId) && Objects.equals(note, other.note)
				&& Objects.equals(reportDate, other.reportDate) && Objects.equals(reportId, other.reportId)
				&& Objects.equals(result, other.result);
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getBidOrderId() {
		return bidOrderId;
	}

	public void setBidOrderId(Integer bidOrderId) {
		this.bidOrderId = bidOrderId;
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

	public String getBidDescription() {
		return bidDescription;
	}

	public void setBidDescription(String bidDescription) {
		this.bidDescription = bidDescription;
	}

	public Integer getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(Integer bidStatus) {
		this.bidStatus = bidStatus;
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
