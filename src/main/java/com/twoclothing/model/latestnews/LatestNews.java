package com.twoclothing.model.latestnews;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "latestnews")
public class LatestNews implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "newsid")
	private Integer newsId;

	@Column(name = "title")
	private String title;

	@Column(name = "startdate")
	private Timestamp startDate;

	@Column(name = "enddate")
	private Timestamp endDate;

	@Column(name = "coverimage", columnDefinition = "mediumblob")
	private byte[] coverImage;

	@Column(name = "content", columnDefinition = "mediumtext")
	private String content;

	@Column(name = "empid")
	private Integer empId;

	public LatestNews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LatestNews(String title, Timestamp startDate, Timestamp endDate, byte[] coverImage, String content,
			Integer empId) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.coverImage = coverImage;
		this.content = content;
		this.empId = empId;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public byte[] getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(byte[] coverImage) {
		this.coverImage = coverImage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(newsId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LatestNews other = (LatestNews) obj;
		return Objects.equals(newsId, other.newsId);
	}

	@Override
	public String toString() {
		
		String coverImageStr ;
		if( coverImage == null ) {
			coverImageStr="null";
		}else {
			coverImageStr = String.valueOf(coverImage.length);
		}
		
		return "LatestNews [newsId=" + newsId + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", coverImage=" + coverImageStr + ", content=" + content + ", empId=" + empId + "]";
	}

	
}
