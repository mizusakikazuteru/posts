package beans;

public class Department {

	private int id;
	private String name;

	// ①ID
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// ②部署・役職
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}