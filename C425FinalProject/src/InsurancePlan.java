
public class InsurancePlan {
	
	private float individualPremium;
	private float familyPremium;
	private String name;
	
	public InsurancePlan() {
		individualPremium = 456;
		familyPremium = 1152;
		name = "Insurance 1";
	}
	public InsurancePlan(float individualPremium, float familyPremium, String name) {
		this.individualPremium = individualPremium;
		this.familyPremium = familyPremium;
		this.name = name;
	}
	
	//getters and setters
	public float getIndividualPremium() {
		return individualPremium;
	}
	public void setIndividualPremium(float individualPremium) {
		this.individualPremium = individualPremium;
	}
	public float getFamilyPremium() {
		return familyPremium;
	}
	public void setFamilyPremium(float familyPremium) {
		this.familyPremium = familyPremium;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//toString
	public String toString() {
		return "Insurance Name: "+name;
	}
	
	//equals
	public boolean equals(InsurancePlan ip) {
		if(ip.getFamilyPremium()==familyPremium) {
			if(ip.getIndividualPremium()==individualPremium) {
				if(ip.getName().equalsIgnoreCase(name)) {
					return true;
				}
			}
		}
		return false;
	}
}
