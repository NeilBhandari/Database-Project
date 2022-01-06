
public class FederalGovernment {
	
	private float taxRate;
	private String country;
	
	//constructors
	public FederalGovernment() {
		taxRate = (float) 0.22;
		country = "USA";
	}
	public FederalGovernment(float taxRate, String country) {
		this.taxRate = taxRate;
		this.country = country;
	}
	
	//getters and setters
	public float getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	//toString
	public String toString() {
		return "Country name: "+country;
	}
	
	//equals
	public boolean equals(FederalGovernment fg) {
		if(fg.country.equalsIgnoreCase(country)) {
			if(fg.taxRate == taxRate) {
				return true;
			}
		}
		return false;
	}
}
