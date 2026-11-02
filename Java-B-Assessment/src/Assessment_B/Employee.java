package Assessment_B;

public class Employee implements IEmployee{
    public static double TAX = 15;
    public static double House_Allowance = 8;
    private static double MAX_INCREASE = 5000;
    private static double MIN_SALARY = 4000;

    private String employeeID;
    private String name;
    private String jobTitle;
    private Positions position;
    private String department;
    private double basicSalary;

    public Employee(){
    }

    public Employee(String employeeID, String name, String jobTitle,
                    Positions position, String department, double basicSalary){
        setEmplyeeID(employeeID);
        this.name = name;
        this.jobTitle = jobTitle;
        this.position = position;
        this.department = department;
        setBasicSalary(basicSalary);
    }

    public void setEmplyeeID(String employeeID){
        if(employeeID == null || !employeeID.startsWith("EM")){
            throw new IllegalArgumentException("Employee ID must start with EM: " + employeeID);
        }
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        if(basicSalary < MIN_SALARY){
            throw new IllegalArgumentException("Basic salary cannot be less than R" + MIN_SALARY);
        }
        this.basicSalary = basicSalary;
    }

    @Override
    public double raiseSalary(double percentage) throws HighSalaryIncrease{
        double increase = basicSalary * percentage / 100;

        if (increase > MAX_INCREASE){
            throw new HighSalaryIncrease("Salary increase of " + increase + " for " + name
                    + " is more than the threshold R" + MAX_INCREASE);
        }
        basicSalary = basicSalary + increase;
        return basicSalary;
    }

    public Positions promote(int years){
        if(years >= 3 && years <=5){
            if(position == Positions.Junior){
                position = Positions.Intermediate;
            }else if (position == Positions.Intermediate){
                position = Positions.Senior;
            }
        }else if(years >=6 && years <=10){
            if (position == Positions.Intermediate){
                position = Positions.Senior;
            }else if (position == Positions.Senior){
                position = Positions.Director;
            }
        }else if (years > 10){
            if(position == Positions.Senior){
                position = Positions.Director;
            }else if (position == Positions.Director){
                position = Positions.Executive;
            }
        }
        return position;
    }

    @Override
    public double calcHouseAllowance(){
        return basicSalary * House_Allowance / 100;
    }

    @Override
    public double calcGrossSalary(){
        return calcGrossSalary() + TAX/100;
    }

    @Override
    public double calcTax(){
        return calcGrossSalary() + TAX / 100;
    }

    @Override
    public double calcNetSalary(){
        return calcGrossSalary() - calcTax();
    }

    @Override
    public String toString(){
        return "Employee: [" + employeeID + "] " + name + "\n"
                + "Title: " + jobTitle + "\n"
                + "Position: " + position + "\n"
                + "Department: " + department + "\n"
                + "======= Salary Breakdown =======\n"
                + "Basic: R" + basicSalary + "\n"
                + "House Allowance: R" + calcHouseAllowance() + "\n"
                + "Gross: R" + calcGrossSalary() + "\n"
                + "Tax Deduction: R" + calcTax() + "\n"
                + "Net: R" + calcNetSalary() + "\n"
                + "++++++++++++++++++++++++++++++++++++";
    }
}
