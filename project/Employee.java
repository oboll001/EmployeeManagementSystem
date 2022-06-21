package project;
import java.text.SimpleDateFormat;  
import java.util.Date; 

public class Employee {
	private static int idCounter = 1;

	private int id;
	private String fname;
	private String lname;
	private int salary;
	private String department;
	private Date date;

	public Employee(String fname, String lname, int salary, String department, Date date) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.salary = salary;
		this.department = department;
		this.id = idCounter++;
		this.date = date;
	}
	public Employee() {
		this.fname = null;
		this.id = idCounter++;
	}

	public String getName() {
		return fname + " "+ lname;
	}

	public void setfName(String fname) {
		this.fname = fname;
	}
	
	public String getfName() {
		return fname;
	}
	
	public void setlName (String lname) {
		this.lname = lname;
	}
	
	public String getlName() {
		return lname;
	}
	
	public void setDateOfEmployment(Date date) {
		this.date = date;
	}
	
	public Date getDateOfEmployment() {
		return date;
	}
	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id +", first name = "+ fname + ", last name= " + lname + ", salary= " + salary + ", department= " + department + ", start date = "+ date+"]";
	}


}
