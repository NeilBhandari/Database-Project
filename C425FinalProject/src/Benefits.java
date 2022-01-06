public class Benefits {
    private String healthPlan;
    private String attorenyPlan;
    private float contribution401k;
    private String lifeInsurance;
    private int benefitsID;
    
    //Constructors
    public Benefits() {
    	healthPlan = "BestHealth";
    	attorenyPlan = "EvenBetter";
    	contribution401k = (float) 0.07;
    	lifeInsurance = "BestOne";
    	benefitsID = 0;
    }
    public Benefits(String healthPlan, String attorenyPlan, float contribution401k, String lifeInsurance, int benefitsID) {
    	this.healthPlan = healthPlan;
    	this.attorenyPlan = attorenyPlan;
    	this.contribution401k = contribution401k;
    	this.lifeInsurance = lifeInsurance;
    	this.benefitsID = benefitsID;
    }
    //getters and setters
	public String getHealthPlan() {
		return healthPlan;
	}
	public void setHealthPlan(String healthPlan) {
		this.healthPlan = healthPlan;
	}
	public String getAttorenyPlan() {
		return attorenyPlan;
	}
	public void setAttorenyPlan(String attorenyPlan) {
		this.attorenyPlan = attorenyPlan;
	}
	public float getContribution401k() {
		return contribution401k;
	}
	public void setContribution401k(float contribution401k) {
		this.contribution401k = contribution401k;
	}
	public String getLifeInsurance() {
		return lifeInsurance;
	}
	public void setLifeInsurance(String lifeInsurance) {
		this.lifeInsurance = lifeInsurance;
	}
	public int getBenefitsID() {
		return benefitsID;
	}
	public void setBenefitsID(int benefitsID) {
		this.benefitsID = benefitsID;
	}
	
	//toString
	public String toString() {
		return "Benefits ID: "+benefitsID;
	}
}
