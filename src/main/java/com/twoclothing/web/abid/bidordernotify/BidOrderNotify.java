package com.twoclothing.web.abid.bidordernotify;

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
@Table(name = "bidordernotify")
public class BidOrderNotify implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notifyid", updatable = false)
	private Integer notifyId;
	
	@Column(name = "mbrid", updatable = false)
	private Integer mbrId;
	
	@Column(name = "bidorderid", updatable = false)
	private Integer bidOrderId;
	
	@Column(name = "notifydate", updatable = false)
	private Timestamp notifyDate;
	
	@Column(name = "title", updatable = false)
	private String title;
	
	@Column(name = "content", updatable = false)
	private String content;

	public BidOrderNotify() {
		super();
	}
	

	public BidOrderNotify(Integer notifyId, Integer mbrId, Integer bidOrderId, Timestamp notifyDate, String title,
			String content) {
		super();
		this.notifyId = notifyId;
		this.mbrId = mbrId;
		this.bidOrderId = bidOrderId;
		this.notifyDate = notifyDate;
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString() {
		return "BidOrderNotify [notifyId=" + notifyId + ", mbrId=" + mbrId + ", bidOrderId=" + bidOrderId
				+ ", notifyDate=" + notifyDate + ", title=" + title + ", content=" + content + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(bidOrderId, content, mbrId, notifyDate, notifyId, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		BidOrderNotify other = (BidOrderNotify) obj;
		return Objects.equals(bidOrderId, other.bidOrderId) && Objects.equals(content, other.content)
				&& Objects.equals(mbrId, other.mbrId) && Objects.equals(notifyDate, other.notifyDate)
				&& Objects.equals(notifyId, other.notifyId) && Objects.equals(title, other.title);
	}

	public Integer getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(Integer notifyId) {
		this.notifyId = notifyId;
	}

	public Integer getMbrId() {
		return mbrId;
	}

	public void setMbrId(Integer mbrId) {
		this.mbrId = mbrId;
	}

	public Integer getBidOrderId() {
		return bidOrderId;
	}

	public void setBidOrderId(Integer bidOrderId) {
		this.bidOrderId = bidOrderId;
	}

	public Timestamp getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Timestamp notifyDate) {
		this.notifyDate = notifyDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
