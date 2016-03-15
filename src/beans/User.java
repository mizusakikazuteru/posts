package beans;

import java.io.Serializable;

//ログインに関する情報
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String password;
	private String name;
	private String branchId;
	private String departmentId;

	private boolean is_active;

	// ①ID
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// ②ログID
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	// ⑤部署・役職
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	// ⑥アカウント
	public boolean getisActive() {
		return is_active;
	}

	public void setisActive(boolean is_active) {
		this.is_active = is_active;
	}
}
