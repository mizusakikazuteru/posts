package beans;

import java.io.Serializable;

//ログインに関する情報
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String password;
	private String name;
	private int branchId;
	private int departmentId;
	private int isActive;

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
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	// ⑤部署・役職
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	// ⑥アカウント
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


}
