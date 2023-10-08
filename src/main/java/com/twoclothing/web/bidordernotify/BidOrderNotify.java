package com.twoclothing.web.bidordernotify;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class BidOrderNotify implements Serializable{

	private Integer notifyId;
	private Integer mbrId;
	private Integer bidOrderId;
	private Timestamp notifyDate;
	private String title;
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
