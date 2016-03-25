package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String subject;
	private String text;
	private String category;
	private Timestamp createdAt;
	private String userId;
	private String name;

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
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	// ⑤登録者ID
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	// ⑥登録者（名前）
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}
}