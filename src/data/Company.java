package data;

public class Company {

	public int id;
	public String name;
	
	public Company(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
}
