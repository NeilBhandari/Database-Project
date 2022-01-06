import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class ERP 
{
	private static Connection c;
	private static Statement stat;
	private static ConnectDB c_db1;
	
    private static boolean isAdmin = false;
    private static boolean isManager = false;
    private static boolean isEmployee = false;
    
    public static void main(String args[]){
    	//connects to the database///////////////
    	c = null;
		stat = null;
		ConnectDB c_db1 = new ConnectDB();
		
		c = c_db1.get_connection();
		//////////////////////////////////////////
        Scanner scnr = new Scanner(System.in);
        boolean exitCase = false;

        while(!exitCase)
        {
            System.out.println("What would you like to do? \nYour options will be enumerated below. \nAdd an Employee (add) \nRemove an Employee(remove)\nUpdate Employee Information (update) \nLog in \nView Reports (reports) \nExit");
            String command = scnr.nextLine();
            String lwrCaseCmmnd = command.toLowerCase();
            /*-------------------------------*/
            if(lwrCaseCmmnd.equals("exit"))
            {
                exitCase = true;
            }
            /*--------------------------------------------------------------------------------------*/
            else if(lwrCaseCmmnd.equals("log in"))
            {
                System.out.println("Please give your login credentials.\nUser Name:");
                String username = scnr.nextLine();
                System.out.println("Password:");
                String password = scnr.nextLine();
                login(username, password);
            }
            /*--------------------------------------------------------------------------------------------------*/
            else if(lwrCaseCmmnd.equals("add"))
            {
                if(isAdmin)
                {
                    addEmployee();
                }
                else
                {
                    System.out.println("You do not have the required permissions.");
                }
            }
            else if(lwrCaseCmmnd.equals("remove"))
            {
                if(isAdmin)
                {
                	System.out.println("What is the ID of the employee you are trying to remove?");
                	String removeThis = scnr.nextLine();
                	remove("Employee", "employee_ID", removeThis);
                }
                else
                {
                    System.out.println("You do not have the required permissions.");
                }
            }
            else if(lwrCaseCmmnd.equals("update"))
            {
                if(isEmployee || isManager || isAdmin)
                {
                    updateEmployee();
                }
                else
                {
                    System.out.println("Please log in before attempting to change an employee's records");
                }
            }
            /*--------------------------------------------------------------------*/
            else if(lwrCaseCmmnd.equals("reports"))
            {
                if(isAdmin)
                {
                    viewReports();
                }
                else
                {
                    System.out.println("You do not have the required permissions.");
                }
            }
            else if(lwrCaseCmmnd.equals("add employee"))
            {
                if(isAdmin)
                {
                    addEmployee();
                }
                else
                {
                    System.out.println("You lack the proper credentials to perform this function.");
                }
            }
        }
        scnr.close();
    }

    private static void addEmployee(){
        Scanner empScnr = new Scanner(System.in);
        System.out.println("Please provide the person's Name.");
        String newEmpName = empScnr.nextLine();
        System.out.println("Please provide their Social Security Number.");
        int newEmpSSN = Integer.parseInt(empScnr.nextLine());
        System.out.println("What is their job title?");
        String newEmpJobTitle = empScnr.nextLine();
        System.out.println("Please state their type of salary.");
        String newEmpSalaryType = empScnr.nextLine();
        System.out.println("Please state their salary.");
        int newEmpSalary = Integer.parseInt(empScnr.nextLine());
        System.out.println("What is their employee ID");
        int newEmployeeID = Integer.parseInt(empScnr.nextLine());
        System.out.println("What will their username be?");
        String newUsername = empScnr.nextLine();
        System.out.println("What will their password be?");
        String newPassword = empScnr.nextLine();
        System.out.println("What is their roll (employee, manager, admin)?");
        String newRoll = empScnr.nextLine();
        Employee createdEmployee = new Employee(newEmpName, newEmpSSN, newEmpJobTitle, newEmpSalaryType, newEmpSalary,newEmployeeID, newUsername, newPassword, newRoll, "ok");
        //Insert SQL stuff
        addEmployeeSQL(createdEmployee);
    }

    private static void updateEmployee(){
        Scanner updScnr = new Scanner(System.in);
        
        //
        //checks to see if the employee exists and if the user is authorized to update it
        //
        System.out.println("Would you like to update a person's (records), add (benefits), add (dependants), add (state),or add (insurance)?");
        String rOrB = updScnr.nextLine();
        
        if(rOrB.equalsIgnoreCase("benefits")){
            if(isAdmin){
            	Benefits b;
            	System.out.println("Would you like to ADD, REMOVE, or EDIT a benefit?");
                String whatToUpdate = updScnr.nextLine();
                String lwrCaseUpdt = whatToUpdate.toLowerCase();
                if(lwrCaseUpdt.equals("add")) {
                	System.out.println("What is the new Benefit's Health Plan?");
                    String newHealthPlan = updScnr.nextLine();
                    
                    System.out.println("What is the new Benefit's Attorney Plan?");
                    String newAttorneyPlan = updScnr.nextLine();
                    
                    System.out.println("What is the new Benefit's Contribution 401k?");
                    float newContribution401k = Float.parseFloat(updScnr.nextLine());
                    
                    System.out.println("What is the new Benefit's life insurance policy?");
                    String newInsurancePolicy = updScnr.nextLine();
                    
                    System.out.println("What is the new Benefit's ID?");
                    int newBenefitsID = Integer.parseInt(updScnr.nextLine());
                    b = new Benefits(newHealthPlan, newAttorneyPlan, newContribution401k, newInsurancePolicy, newBenefitsID);
                    addBenefitsSQL(b);
                }
                else if(lwrCaseUpdt.equals("remove")) {
                	System.out.println("What is the ID of the benefit you are trying to remove?");
                    String removeThis = updScnr.nextLine();
                	remove("Benefits", "benefits_ID", removeThis);
                }
                else if(lwrCaseUpdt.equals("edit")) {
                	String newVal = "";
                	System.out.println("What is the ID of the benefit you are trying to edit?");
                    String id = updScnr.nextLine();
                    
                    System.out.println("What are you trying to edit: ID, lifeInsurance, 401kContribution, attorneyPlan, or healthPlan");
                    String editThis = updScnr.nextLine();
                    if(editThis.equalsIgnoreCase("id")) {
                    	System.out.println("What is the new id?");
                        newVal = updScnr.nextLine();
                        editThis = "benefits_ID";
                    }
                    else if(editThis.equalsIgnoreCase("lifeInsurance")) {
                    	System.out.println("What is the new lifeInsurance?");
                        newVal = updScnr.nextLine();
                        editThis = "life_insurance";
                    }
                    else if(editThis.equalsIgnoreCase("401kContribution")) {
                    	System.out.println("What is the new 401kContribution?");
                        newVal = updScnr.nextLine();
                        editThis = "contribution_401k";
                    }
                    else if(editThis.equalsIgnoreCase("attorneyPlan")) {
                    	System.out.println("What is the new attorneyPlan?");
                        newVal = updScnr.nextLine();
                        editThis = "attoreny_plan";
                    }
                    else if(editThis.equalsIgnoreCase("healthPlan")) {
                    	System.out.println("What is the new healthPlan?");
                        newVal = updScnr.nextLine();
                        editThis = "health_plan";
                    }
                    else {
                    	System.out.println("not a valid option!");
                    	return;
                    }
                	updateBenefitsSQL(id, editThis, newVal);
                }
                else {
                	System.out.println("Not a valid option.");
                }
                
            }
            else{
                System.out.println("You do not have permissions for this.");
            }
        }
        else if(rOrB.toLowerCase().equals("records")){
        	System.out.println("What is the ID of the employee who's record you would like to update?");
            int updEmpID = Integer.parseInt(updScnr.nextLine());
        	
            System.out.println("What would you like to update about this employee? Your options will be enumerated below.\n");
            System.out.print("------------------------------------------\n");
            System.out.print("name | jobTitle | salaryType | salary | employeeID | stateName | DependentSSN | ssn | insuranceName | username | password | roll | benefits_ID | performance\n");
            String whatToUpdate = updScnr.nextLine();
            String lwrCaseUpdt = whatToUpdate.toLowerCase();
            if(lwrCaseUpdt.equals("name")){
                if(isEmployee || isAdmin || isManager){
                    System.out.println("What is the new name?");
                    String newName = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "name", newName);
                }
                else{
                    System.out.println("Please log in first.");
                }
            }
            else if(lwrCaseUpdt.equals("ssn")){
                if(isEmployee || isAdmin || isManager){
                    System.out.println("What is the new ssn?");
                    String newSSN = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "e_ssn", newSSN);
                }
                else{
                    System.out.println("Please log in first.");
                }
            }
            else if(lwrCaseUpdt.equals("statename")){
                if(isEmployee || isAdmin || isManager){
                    System.out.println("What is the new State they are living in (make sure the State is already in the database first!)?");
                    String newSname = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "s_name", newSname);
                }
                else{
                    System.out.println("Please log in first.");
                }
            }
            
            else if(lwrCaseUpdt.equals("dependentssn")){
                if(isEmployee || isAdmin || isManager){
                    System.out.println("What is their new dependant SSN (make sure that dependant already exists!)?");
                    String newdependentssn = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "ssn", newdependentssn);
                }
                else{
                    System.out.println("Please log in first.");
                }
            }
            else if(lwrCaseUpdt.equals("insurancename")){
                if(isEmployee || isAdmin || isManager){
                    System.out.println("What is their new Insurance Name (make sure it exists first!!)?");
                    String newinsuranceName = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "i_name", newinsuranceName);
                }
                else{
                    System.out.println("Please log in first.");
                }
            }
            else if(lwrCaseUpdt.equals("benefitsid")){
                if(isEmployee || isAdmin || isManager){
                    System.out.println("What is their new benefits ID (make sure that benefit exists first!!)?");
                    String benefits_ID = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "benefits_ID", benefits_ID);
                }
                else{
                    System.out.println("Please log in first.");
                }
            }
            else if(lwrCaseUpdt.equals("jobtitle")) {
                if(isManager || isAdmin){
                    System.out.println("What is the new jobTitle?");
                    String newJobTitle = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "job_title", newJobTitle);
                }
                else{
                    System.out.println("You lack the prorper credentials.");
                }
            }
            else if(lwrCaseUpdt.equals("salarytype")) {
                if(isManager || isAdmin){
                    System.out.println("What is the new salaryType?");
                    String newSalaryType = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "salary_type", newSalaryType);
                }
                else{
                    System.out.println("You lack the prorper credentials.");
                }
            }
            else if(lwrCaseUpdt.equals("salary")) {
                if(isManager || isAdmin){
                    System.out.println("What is the new salary?");
                    String newSalary = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "salary", newSalary);
                }
                else{
                    System.out.println("You lack the prorper credentials.");
                }
            }
            else if(lwrCaseUpdt.equals("employeeid")) {
                if(isAdmin){
                    System.out.println("What is the new employeeID?");
                    String newEmployeeID = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "employee_ID", newEmployeeID);
                }
                else{
                    System.out.println("You lack the prorper credentials.");
                }
            }
            else if(lwrCaseUpdt.equals("performance")) {
                if(isManager || isAdmin){
                    System.out.println("What is the new performance?");
                    String newPerformance = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "performance", newPerformance);
                }
                else{
                    System.out.println("You lack the prorper credentials.");
                }
            }
            else if(lwrCaseUpdt.equals("username")) {
                if(isAdmin){
                    System.out.println("What is the new username?");
                    String newUsername = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "e_username", newUsername);
                }
                else{
                    System.out.println("You lack the prorper credentials.");
                }
            }
            else if(lwrCaseUpdt.equals("password")) {
                if(isAdmin){
                    System.out.println("What is the new password?");
                    String newPassword = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "e_Password", newPassword);
                }
                else{
                    System.out.println("You lack the prorper credentials.");
                }
            }
            else if(lwrCaseUpdt.equals("roll")) {
                if(isAdmin){
                    System.out.println("What is the new roll?");
                    String newRoll = updScnr.nextLine();
                    updateEmployeeSQL(updEmpID, "roll", newRoll);
                }
                else{
                    System.out.println("You lack the prorper credentials.");
                }
            }
            else 
            {
            	System.out.println("That is not a valid option.");
            	return;
            }
        }
        else if(rOrB.equalsIgnoreCase("dependants")) 
        {
            if(isEmployee || isAdmin || isManager)
            {
                System.out.println("Would you like to ADD, REMOVE, or EDIT a dependant?");
                String whatToUpdate = updScnr.nextLine();
                String lwrCaseUpdt = whatToUpdate.toLowerCase();
                if(lwrCaseUpdt.equals("add")) 
                {
                    System.out.println("What is the name of the dependant?");
                    String nameODependant = updScnr.nextLine();
                    System.out.println("What is the dependant's SSN?");
                    int dependantSSN = updScnr.nextInt();
                    System.out.println("What is their relationship to the employee");
                    String depepndantRelation = updScnr.nextLine();
                    Dependant newDependant = new Dependant(nameODependant, dependantSSN, depepndantRelation);
                }
                else if(lwrCaseUpdt.equals("remove")) 
                {
                    System.out.println("What is the name of the dependant you want to remove?");
                    String remDependant = updScnr.nextLine();
                }
                else if(lwrCaseUpdt.equals("edit"))
                {
                    System.out.println("What is the name of the dependant you want to edit?");
                    String editDependant = updScnr.nextLine();
                    String lwrEditDependant = editDependant.toLowerCase();
                    System.out.println("What do you want to update about this dependant? Name, SSN, Relation");
                    if(lwrEditDependant.equals("name"))
                    {
                        System.out.println("What is the new name?");
                        String newDepName = updScnr.nextLine();
                    }
                    else if(lwrEditDependant.equals("ssn"))
                    {
                        System.out.println("What is their new SSN?");
                        String newDepSSN = updScnr.nextLine();
                    }
                    else if(lwrEditDependant.equals("relation"))
                    {
                        System.out.println("What is their new Relation?");
                        String newDepRelation = updScnr.nextLine();
                    }
                    else
                    {
                        System.out.println("Please choose a valid option.");
                    }
                }
                else 
                {
                    System.out.println("Please remeber to choose a valid option.");
                }
            }
            else
            {
                System.out.println("Please log in first.");
            }
        	
        }
        else if(rOrB.equalsIgnoreCase("insurance")) 
        {
        	System.out.println("What do you want to change about their insurance? Individual Premium, Family Premium, Name");
            String whatToUpdateInsurance = updScnr.nextLine();
            String toLwrUpdInsurance = whatToUpdateInsurance.toLowerCase();
            if(toLwrUpdInsurance.equals("individual premium"))
            {
                System.out.println("What is their new premium value?");
                int newIndiPrem = updScnr.nextInt();
            }
            else if(toLwrUpdInsurance.equals("family premium"))
            {
                System.out.println("What is the new premium value");
                int newFamPrem = updScnr.nextInt();
            }
            else if(toLwrUpdInsurance.equals("name"))
            {
                System.out.println("What is their new name");
                String newInsName = updScnr.nextLine();
            }
            else
            {
                System.out.println("Please select a valid options");
            }
        }
        else
        {
            System.out.println("Not a valid option.");
            return;
        }
    }

    private static void viewReports()
    {
        Scanner rprtScnr = new Scanner(System.in);
        System.out.println("What report would you like see: paycheck | w2 | expense");
        String typeOfReport = rprtScnr.nextLine();
        String lwrCaseRprt = typeOfReport.toLowerCase();
        System.out.println("Please provide the employee ID for the paycheck you would like to see. (just press 1 if you want to see expenses)");
        int pChkEmpID = Integer.parseInt(rprtScnr.nextLine());
        if(lwrCaseRprt.equals("paycheck"))
        {
            if(isAdmin || isEmployee || isManager)
            {
                paycheckSQL(pChkEmpID);
            }
            else
            {
                System.out.println("Please log in first.");
            }
        }
        else if(lwrCaseRprt.equals("w2"))
        {
            if(isAdmin || isManager)
            {
            	W2SQL(pChkEmpID);
            }
            else
            {
                System.out.println("You lack the proper credentials");
            }
        }
        else if(lwrCaseRprt.equals("expense"))
        {
            if(isAdmin)
            {
            	expRprtSQL();
            }
            else
            {
                System.out.println("You lack the proper credentials.");
            }
        }
        else
        {
            System.out.println("Please provide a valid report.");
        }
    }
    public static void login(String username, String password) {
    	ResultSet rs = null;
    	isAdmin= false;
    	isManager = false;
    	isEmployee = false;
    	try {
			
			String query = "SELECT roll FROM employee WHERE e_username = '"+username+"' and e_password='"+password+"';";
			stat = c.createStatement();
			rs = stat.executeQuery(query);
			rs.next();
			if(rs.getString(1).equalsIgnoreCase("admin")) {
				isAdmin = true;
			}
			else if(rs.getString(1).equalsIgnoreCase("manager")) {
				isManager = true;
			}
			else if(rs.getString(1).equalsIgnoreCase("employee")) {
				isEmployee = true;
			}
			else {
				System.out.println("Invalid credentials or invalid roll!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    public static void addEmployeeSQL(Employee emp) {
    	//String ssn = Integer.toString(e.getSSN());
    	try {
			String dep = "INSERT INTO dependent (relation,ssn,d_name) VALUES (\r\n"
					+ "  '"+emp.getDependant().getRelation()+"',\r\n"
					+ "  '"+emp.getDependant().getSsn()+"',\r\n"
					+ "  '"+emp.getDependant().getName()+"'\r\n"
					+ ");\r\n";
			String query = dep+"INSERT INTO employee (name, job_title, salary_type, salary, employee_ID, s_name, ssn, e_ssn ,i_name, e_username, e_Password, roll, benefits_ID, performance)\r\n"
					+"VALUES ('"+emp.getName()+"', '"+emp.getJobTitle()+"', '"+emp.getSalaryType()+"', "+emp.getSalary()+", "+emp.getEmployeeID()+", '"+emp.getState().getName()+"', "+emp.getDependant().getSsn()+", "+emp.getSSN()+", '"+emp.getEmployeeInsurancePlan().getName()+"', '"+emp.getUsername()+"', '"+emp.getPassword()+"', '"+emp.getRoll()+"', "+emp.getEmployeeBenefits().getBenefitsID()+", '"+emp.getPerformance()+"');";                       
			stat = c.createStatement();
			stat.executeUpdate(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    public static void updateEmployeeSQL(int empID, String var, String newVal){
        try{
            String query = "Update Employee\r\n"
                            +"SET "+var + "=" + "'" + newVal + "'\n"
                            +"where employee_ID = " + empID + ";";
            stat = c.createStatement();
            stat.executeUpdate(query);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void addBenefitsSQL(Benefits b){
    	try {
			String query = "INSERT INTO benefits (benefits_id, life_insurance, contribution_401k, attoreny_plan, health_plan)\r\n"
					+ "VALUES ("+b.getBenefitsID()+", '"+b.getLifeInsurance()+"', "+b.getContribution401k()+", '"+b.getAttorenyPlan()+"', '"+b.getHealthPlan()+"');";                       
			stat = c.createStatement();
			stat.executeUpdate(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void updateBenefitsSQL(String benefitsID, String var, String newVal){
        try{
            String query = "Update Benefits\r\n"
                            +"SET "+var + "=" + "'" + newVal + "'\r\n"
                            +"where benefits_ID = " + benefitsID + ";";
            stat = c.createStatement();
			stat.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void remove(String table, String clmn, String var) {
    	try {
			String query =  "delete from "+table+" where "+clmn+"= "+var+";";                   
			stat = c.createStatement();
			stat.executeUpdate(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void paycheckSQL(int empID){

        try{
            String query = "SELECT e_ssn, salary from employee where employee_id = "+empID+";\r\n";
            stat = c.createStatement();
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                System.out.print("SSN: "+rs.getString("e_ssn"));
                System.out.print("\tSalary: "+rs.getString("salary"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String query = "Select tax_rate from states where s_name = (Select  s_name FROM employee WHERE employee_id = "+empID+");\r\n";
            stat = c.createStatement();
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                System.out.print("\tState Tax Rate: "+rs.getString("tax_rate"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String query = "SELECT tax_rate from federal_government where country = (SELECT country from employee where employee_id = "+empID+");\r\n";
            stat = c.createStatement();
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                System.out.println("\tFederal Tax Rate: "+rs.getString("tax_rate"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String query = "SELECT family_premium, individual_premium FROM insurance_plan where i_name = (SELECT i_name from employee where employee_id = "+empID+");\r\n";
            stat = c.createStatement();
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                System.out.print("Family Premimum: "+rs.getString("family_premium"));
                System.out.print("\tIndividual Premium: "+rs.getString("individual_premium"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            String query = "SELECT contribution_401k from benefits where benefits_id = (SELECT benefits_id FROM employee where employee_id = "+empID+");";
            stat = c.createStatement();
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                System.out.println("\t401K Contribution: "+rs.getString("contribution_401k"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void W2SQL(int empID){
        try{
            String query = "SELECT e_ssn, salary, performance from employee where employee_id = "+empID+";";
            stat = c.createStatement();
            
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                System.out.print("SSN: "+rs.getString("e_ssn"));
                System.out.print("\tSalary: "+rs.getString("salary"));
                double allocatedP = 0;
                if(rs.getString("performance").equalsIgnoreCase("well")) {
                	allocatedP = 1.5;
                }
                else if(rs.getString("performance").equalsIgnoreCase("ok")) {
                	allocatedP = 0.75;
                }
                else if(rs.getString("performance").equalsIgnoreCase("super")) {
                	allocatedP = 2.25;
                }
                else {
                	allocatedP = 0;
                }
                System.out.println("\tBonus: "+allocatedP+"%");           
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
        
        try{
            String query = "Select tax_rate tx from states where s_name = (Select  s_name FROM employee WHERE employee_id = "+empID+");";
            stat = c.createStatement();
            
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                System.out.print("State Tax Rate: "+rs.getString("tx"));        
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
        
        try{
            String query = "SELECT tax_rate from federal_government where country = (SELECT country from employee where employee_id = "+empID+");";
            stat = c.createStatement();
            
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
                System.out.println("\tFederal Tax Rate: "+rs.getString("tax_rate"));        
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
        
    }
    public static void expRprtSQL(){
    	double con401k = 0;
    	double wages = 0;
    	double fam = 0;
    	double ind = 0;
    	try{
            String query = "SELECT sum(salary) as wages from employee;";
            stat = c.createStatement();
            
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
            	wages = Integer.parseInt(rs.getString("wages"));
                System.out.print("Total Wages: "+wages);        
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
    	
    	try{
            String query = "SELECT sum(contribution_401k) as total401k from benefits;";
            stat = c.createStatement();
            
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
            	con401k = Double.parseDouble(rs.getString("total401k"))*wages;
            	double scale = Math.pow(10, 3);
            	con401k = Math.round(con401k * scale) / scale;
                System.out.print("\tTotal 401K Contributions: "+con401k);        
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
    	
    	try{
            String query = "SELECT SUM(family_premium) as totalFam from insurance_plan;";
            stat = c.createStatement();
            
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
            	fam = Double.parseDouble(rs.getString("totalFam"));
                System.out.print("\tTotal family premiums: "+fam);        
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
    	
    	try{
            String query = "SELECT sum(individual_premium) as totalInd from insurance_plan;";
            stat = c.createStatement();
            
            ResultSet rs =null;
            rs = stat.executeQuery(query);
            
            while(rs.next()){
            	ind = Double.parseDouble(rs.getString("totalInd"));
                System.out.println("\tTotal individual premiums: "+ind);        
            }
            double total = wages+con401k+fam+ind;
            System.out.println("TOTAL COMPANY EXPENSES: "+total);
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
}