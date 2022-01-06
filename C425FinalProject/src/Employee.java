
public class Employee {
    private String name;
    private int SSN;
    private String jobTitle;
    private String salaryType;
    private float salary;
    private int employeeID;
    private State state;
    private String performance;
    private Benefits employeeBenefits;
    private Dependant dependant;
    private InsurancePlan employeeInsurancePlan;
    private String username;
    private String password;
    private String roll;
    Employee(){
        name = "John Doe";
        SSN =  (int)(Math.random() * 1000000000);
        jobTitle = "Intern";
        salaryType = "Hourly";
        employeeID = 12345;
        state = new State();
        performance = "ok";
        salary = 0;
        salaryType = "hourly";
        setUsername("JD12345");
        roll = "employee";
        setPassword("NoPassword");
        setEmployeeBenefits(new Benefits());
        setDependant(new Dependant());
        setEmployeeInsurancePlan(new InsurancePlan());
    }
    Employee(String name, int SSN, String jobTitle, String salaryType, int salary ,int employeeID, String username, String password, String roll, String performance){
        this.name = name;
        this.SSN = SSN;
        this.jobTitle = jobTitle;
        this.salaryType = salaryType;
        this.salary = salary;
        this.employeeID = employeeID;
        state = new State();
        this.setUsername(username);
        this.setPassword(password);
        this.setRoll(roll);
        this.setEmployeeBenefits(new Benefits());
        setDependant(new Dependant());
        this.setEmployeeInsurancePlan(new InsurancePlan());
        this.performance = performance;
    }
    
    //toString
    public String toString() {
    	return "";
    }

    public String getName()
    {
        return name;
    }
    public int getSSN()
    {
        return SSN;
    }
    public String getJobTitle()
    {
        return jobTitle;
    }
    public String getSalaryType()
    {
        return salaryType;
    }
    public int getEmployeeID()
    {
        return employeeID;
    }
    public String getPerformance()
    {
        return performance;
    }
//-------------------------------------
    public void setName(String newName)
    {
        name = newName;
    }
    public void setSSN(int newSSN)
    {
        SSN = newSSN;
    }
    public void setJobTitle(String newJobTitle)
    {
        jobTitle = newJobTitle;
    }
    public void setSalaryType(String newSalaryType)
    {
        salaryType = newSalaryType;
    }
    public void setEmployeeID(int newEmployeeID)
    {
        employeeID = newEmployeeID;
    }
    public void setPerformance(String newPerformance)
    {
        performance = newPerformance;
    }
	public Benefits getEmployeeBenefits() {
		return employeeBenefits;
	}
	public void setEmployeeBenefits(Benefits employeeBenefits) {
		this.employeeBenefits = employeeBenefits;
	}
	public InsurancePlan getEmployeeInsurancePlan() {
		return employeeInsurancePlan;
	}
	public void setEmployeeInsurancePlan(InsurancePlan employeeInsurancePlan) {
		this.employeeInsurancePlan = employeeInsurancePlan;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public Dependant getDependant() {
		return dependant;
	}
	public void setDependant(Dependant dependant) {
		this.dependant = dependant;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
}
