
public class State {
	
	private float taxRate;
	private String name;
	private FederalGovernment fg;
	//constructors
	public State() {
		taxRate = (float) 0.2;
		name = "IL";
		fg = new FederalGovernment();
	}
	public State(String name, float taxRate, FederalGovernment fg) {
		this.taxRate = taxRate;
		this.name = name;
		this.fg = fg;
	}
	
	//getters and setters
	public float getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//toString
	public String toString() {
		return "State: "+name;
	}
	
	//equals
	public boolean equals(State s) {
		return false;
	}
	public FederalGovernment getFg() {
		return fg;
	}
	public void setFg(FederalGovernment fg) {
		this.fg = fg;
	}
}
