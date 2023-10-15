package com.twoclothing.model.aproduct.ordernotify;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "ordernotify")
public class OrderNotify implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notifyid", updatable = false)
	private Integer notifyId;
	@Column(name = "mbrid", updatable = false)
	private Integer mbrId;
	@Column(name = "orderid", updatable = false)
	private Integer orderId;
	@Column(name = "notifydate", updatable = false)
	private Timestamp notifyDate;
	@Column(name = "title")
	private String title;
	@Column(name = "content")
	private String content;

	public OrderNotify() {
	}

	public OrderNotify(Integer notifyId, Integer mbrId, Integer orderId, Timestamp notifyDate, String title,
			String content) {
		this.notifyId = notifyId;
		this.mbrId = mbrId;
		this.orderId = orderId;
		this.notifyDate = notifyDate;
		this.title = title;
		this.content = content;
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

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	@Override
	public int hashCode() {
		return Objects.hash(content, mbrId, notifyDate, notifyId, orderId, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderNotify other = (OrderNotify) obj;
		return Objects.equals(content, other.content) && Objects.equals(mbrId, other.mbrId)
				&& Objects.equals(notifyDate, other.notifyDate) && Objects.equals(notifyId, other.notifyId)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "OrderNotify [notifyId=" + notifyId + ", mbrId=" + mbrId + ", orderId=" + orderId + ", notifyDate="
				+ notifyDate + ", title=" + title + ", content=" + content + "]";
	}

}
