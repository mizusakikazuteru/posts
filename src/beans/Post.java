package beans;

import java.io.Serializable;
import java.sql.Date;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String subject;
	private String text;
	private String category;
	private Date createdAt;
	private int userId;

	// ①ID
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// ②件名
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	// ③本文
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// ④カテゴリー
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// ④登録日時
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	// ⑤登録者
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
