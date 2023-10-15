package com.twoclothing.model.latestnews;

import java.io.Serializable;
import java.sql.Timestamp;
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

	@Column(name = "content")
	private String content;

	@Column(name = "published")
	private Timestamp published; // 将 published 字段的数据类型改为 Timestamp

	@Column(name = "empid")
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
