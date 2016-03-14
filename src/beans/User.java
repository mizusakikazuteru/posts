package beans;

import java.io.Serializable;

//ログインに関する情報
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String login_id;
	private String password;
	private String name;
	private String branch_id;
	private String department_id;

	private boolean is_active;

	// ①ID
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// ②ログID
	public String getlogin_id() {
		return login_id;
	}

	public void setlogin_id(String login_id) {
		this.login_id = login_id;
	}

	// ③パスワード
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// ④氏名
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// ④支店番号
	public String getbranch_id() {
		return branch_id;
	}

	public void setbranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	// ⑤部署・役職
	public String getdepartment_id() {
		return department_id;
	}

	public void setdepartment_id(String department_id) {
		this.department_id = department_id;
	}
	// ⑥アカウント
	public boolean getis_active() {
		return is_active;
	}

	public void setis_active(boolean is_active) {
		this.is_active = is_active;
	}
}
