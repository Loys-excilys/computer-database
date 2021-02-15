package data;

public class Company {

	public long id;
	public String name;
	
	public Company(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
}
