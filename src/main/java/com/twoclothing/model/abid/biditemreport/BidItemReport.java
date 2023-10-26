package com.twoclothing.model.abid.biditemreport;

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
@Table(name = "biditemreport")
public class BidItemReport  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reportid", updatable = false)
	private Integer reportId;
	
	@Column(name = "biditemid", updatable = false)
	private Integer bidItemId;
	
	@Column(name = "mbrid", updatable = false)
	private Integer mbrId;
	
	@Column(name = "empid")
	private Integer empId;
	
	@Column(name = "reportdate", updatable = false)
	private Timestamp reportDate;
	
	@Column(name = "biddescription", updatable = false)
	private String bidDescription;
	
	@Column(name = "bidstatus", columnDefinition = "TINYINT")
	private Integer bidStatus;
	
	@Column(name = "auditdate")
	private Timestamp auditDate;
	
	@Column(name = "result", columnDefinition = "TINYINT")
	private Integer result;
	
	@Column(name = "note")
	private String note;

	public BidItemReport() {
		super();
	}
	

	public BidItemReport(Integer reportId, Integer bidItemId, Integer mbrId, Integer empId, Timestamp reportDate,
			String bidDescription, Integer bidStatus, Timestamp auditDate, Integer result, String note) {
		super();
		this.reportId = reportId;
		this.bidItemId = bidItemId;
		this.mbrId = mbrId;
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
		return "BidItemReport [reportId=" + reportId + ", bidItemId=" + bidItemId + ", mbrId=" + mbrId + ", empId="
				+ empId + ", reportDate=" + reportDate + ", bidDescription=" + bidDescription + ", bidStatus="
				+ bidStatus + ", auditDate=" + auditDate + ", result=" + result + ", note=" + note + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(auditDate, bidDescription, bidItemId, bidStatus, empId, mbrId, note, reportDate, reportId,
				result);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		BidItemReport other = (BidItemReport) obj;
		return Objects.equals(auditDate, other.auditDate) && Objects.equals(bidDescription, other.bidDescription)
				&& Objects.equals(bidItemId, other.bidItemId) && Objects.equals(bidStatus, other.bidStatus)
				&& Objects.equals(empId, other.empId) && Objects.equals(mbrId, other.mbrId)
				&& Objects.equals(note, other.note) && Objects.equals(reportDate, other.reportDate)
				&& Objects.equals(reportId, other.reportId) && Objects.equals(result, other.result);
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getBidItemId() {
		return bidItemId;
	}

	public void setBidItemId(Integer bidItemId) {
		this.bidItemId = bidItemId;
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

	
	  public com.twoclothing.model.abid.biditem.BidItem getBidItem() {
		  com.twoclothing.chenghan.service.BidItemFrontServiceImpl bidItemSvc = new com.twoclothing.chenghan.service.BidItemFrontServiceImpl();
		  com.twoclothing.model.abid.biditem.BidItem bidItem = bidItemSvc.getBidItemByBidItemId(bidItemId);
		    return bidItem;
	    }
	
	  public com.twoclothing.model.members.Members getMembers() {
		  com.twoclothing.gordon.service.MembersServiceImpl memberSvc = new com.twoclothing.gordon.service.MembersServiceImpl();
		  com.twoclothing.model.members.Members members = memberSvc.getByPrimaryKey(mbrId);
		    return members;
	    }
	  
	  public com.twoclothing.model.employee.Employee getEmployee() {
		  com.twoclothing.tonyhsieh.service.EmployeeServiceImpl EmpSvcq = new com.twoclothing.tonyhsieh.service.EmployeeServiceImpl();
		  com.twoclothing.model.employee.Employee employee = EmpSvcq.getEmployeeById(empId);
		    return employee;
	    }
	  
	
}