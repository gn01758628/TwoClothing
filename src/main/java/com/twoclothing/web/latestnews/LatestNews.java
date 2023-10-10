package com.twoclothing.web.latestnews;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class LatestNews implements Serializable {

    private Integer newsId;
    private String title;
    private String content;
    private Timestamp published; // 将 published 字段的数据类型改为 Timestamp
    private Integer empId;
	public LatestNews() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LatestNews(String title, String content, Timestamp published, Integer empId) {
		super();
		this.title = title;
		this.content = content;
		this.published = published;
		this.empId = empId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(empId, title);
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
		return Objects.equals(empId, other.empId) && Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "LatestNews [newsId=" + newsId + ", title=" + title + ", content=" + content + ", published=" + published
				+ ", empId=" + empId + "]";
	}
	
}
