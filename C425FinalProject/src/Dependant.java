
public class Dependant {
	
	private String name;
	private int ssn;
	private String relation;
	
	//constructors
	public Dependant() {
		name = "NoName";
		ssn = (int)(Math.random() * 1000000000);
		relation = "NoRelation";
	}
	public Dependant(String name, int ssn, String relation) {
		this.name = name;
		this.ssn = ssn;
		this.relation = relation;
	}
	
	//getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	//toString
	public String toString() {
		return "Dependent Name: "+name;
	}
	
	//equals
	public boolean equals(Dependant d) {
		if(d.getName().equalsIgnoreCase(name)) {
			if(d.getRelation().equalsIgnoreCase(relation)) {
				if(d.getSsn()==ssn) {
					return true;
				}
			}
		}
		return false;
	}
}
