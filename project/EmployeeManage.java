package project;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.List;


public class EmployeeManage {
	//The main will call a creation method and a deletion method
	//I will implement a method that auto generates an employee list
	//There will be a print out of records.
	//There will be a method to update
	//
	public static int id = 1;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Scanner input = new Scanner(System.in);
		List<Employee> EmployeeList= new ArrayList<Employee>();
		populate(EmployeeList);
		
		MainMenu(input, EmployeeList);
		input.close();
		System.out.println("The program is ended");
	}

	//It will ensure that the user is in the correct method
	//It will be called to help navigate the different functions
	//It will return an int that specifies which system if it is wrong.
	public static int checkSystem(String system, Scanner input) throws Exception{
		System.out.println("You are in the Employee "+system+" system");
		int number = 2;
		System.out.println("If it is correct, type 1\n"
				+ "If it is not correct, type 0");
		boolean isValid = true;
		String newSystem = "Not made";
		do {
			try {
				int answer = input.nextInt();
				
				if(answer==1) {
					number = answer;
					isValid = false;
				}else if(answer == 0) {
					newSystem = findSystem(input);
					isValid = false;
				}
				else {
					System.out.println("Not a valid number");
				}
			}
			catch(Exception e) {
				
				System.out.println("Invalid input. It was an integer");
				number = checkSystem(system, input);
				isValid = false;
			}//end try catch
		}while(isValid);//end do while
		if(newSystem == "Not made") {
		}else if(newSystem == "Main menu"){
			number = 5;
		}else if(newSystem == "Creation") {
			number = 6;
		}else if(newSystem == "Deletion") {
			number = 7;
		}//end if else
		return number;
	}//end checkSystem
	//This method will take the name of a system that the user wants to be in.
	public static String findSystem(Scanner input) {
		String system = "Exit";
		boolean isValid = true;
		String check = input.next();
		do {		
			if(check == "Main Menu") {
				system = check;
				isValid = false;
			}else if(check == "Creation" ){
				system = check;
				isValid = false;
			}else if(check == "Deletion"){
				system = check;
				isValid = false;
			}else {
				System.out.println("Invalid argument. Please try that again");
				check = input.next();
			}//end if else
		}while(isValid);//end do while
		System.out.println("This is the end of the findSystem method. Returning:  "+system);
		return system;
	}//ends findSystem
	//This method is the main menu
	//It will allow the user to move around to other functions
	//and even close the app
	public static List<Employee> MainMenu (Scanner input, List<Employee> EmployeeList)throws Exception {
		System.out.println("Welcome to the Employee Management System.\n"
				+ "Where would you like to go? (C)reation | (D)elete | (L)ist | (U)pdate | E(x)it");
		String desiredSection = input.next();
		boolean isValid = true;
		boolean ac = false;
		do {
			
			if(desiredSection.equalsIgnoreCase("Creation") ||desiredSection.equalsIgnoreCase("C")) {
				EmployeeList= createEmployee(input, EmployeeList);
				System.out.println("Creation works");
				isValid = false;
			}else if(desiredSection.equalsIgnoreCase("Delete") ||desiredSection.equalsIgnoreCase("D")) {
				EmployeeList = deleteEmployee(input, EmployeeList);
				System.out.println("Delete works");
				isValid = false;
			}else if(desiredSection.equalsIgnoreCase("List") ||desiredSection.equalsIgnoreCase("L")) {
				listEmployees(EmployeeList);
				isValid = false;
			}else if(desiredSection.equalsIgnoreCase("Update") ||desiredSection.equalsIgnoreCase("U")) {
				System.out.println("Update works");
				EmployeeList = updateEmployee(input, EmployeeList);
				isValid = false;
			}else if(desiredSection.equalsIgnoreCase("Exit") ||desiredSection.equalsIgnoreCase("X")){
				System.out.println("Thank you!\n"
						+ "Have a good day.");
				ac = true;
				isValid = false;
			}else {
				System.out.println("Invalid input. Try again.");
				desiredSection = input.next();
			}//end if else
		}while(isValid);//end do while
		//This will check if there is another action to do
		while(!ac) {
			try {
				System.out.println("Would you like to do another action?\n"
						+ "(Y)es | (N)o");
				String newAct = input.next();
				if(newAct.equalsIgnoreCase("Yes")||newAct.equalsIgnoreCase("Y")) {
					EmployeeList = MainMenu(input, EmployeeList);
					ac =!ac;
				}else if(newAct.equalsIgnoreCase("No")||newAct.equalsIgnoreCase("N")){
					System.out.println("Have a nice day");
					ac = !ac;
				}else {
					String message = "No valid input";
					throw new MainMenuException(message);
				}
			}catch(Exception e) {
				System.out.println("(Y)es | (N)o");
			}
		}
		
		return EmployeeList;
	}//ends main menu
	//This method will create the Employee objects.
	//It will have a try catch for the 
	//It will return an Employee
	public static List<Employee> createEmployee(Scanner input, List<Employee> EmployeeList) throws Exception {
			Employee newEmployee = new Employee();
			boolean isValid =true;
			while(isValid){
				System.out.println("Enter a first name:");
				String RemoveLine = input.nextLine();
				String fname = findName(input);
				newEmployee.setfName(fname);
				System.out.println("Enter a last name:");
				String lname = findName(input);
				newEmployee.setlName(lname);	
				System.out.println("What is there salary?");
				int salary = getSalary(input);
				newEmployee.setSalary(salary);
				System.out.println("What department do they work for? ");
				String department = whichDepartment(input);
				newEmployee.setDepartment(department);
				System.out.println("What is the start date? ");
				Date firstDay = startDate(input);
				newEmployee.setDateOfEmployment(firstDay);
				isValid = false;
			System.out.println("End of creation method");
			}
			EmployeeList.add(newEmployee);
		return EmployeeList; 
	}
	//Deletes the Employee on the record.
	//It will find the name of the employee and confirm before deletion
	public static List<Employee> deleteEmployee(Scanner input, List<Employee> EmployeeList) throws Exception{
		//checkSystem("Deletion",input);
		System.out.println("Which employee would you like to delete? Enter an ID");
		boolean isValid = true;
		do {
			try {
				boolean found = false;
				int FutureFormerEmployeeID = input.nextInt();
				//List<Employee> FutureFormerEmployeeList= new ArrayList<Employee>();
				for(int id = 0; id < EmployeeList.size(); id++) {
					if(FutureFormerEmployeeID == EmployeeList.get(id).getId()) {
						EmployeeList.remove(id);
						found = true;
					}//ends the if
				}//ends the for
				if(found==false)
					System.out.println("Employee not found. Nothing was deleted");
				listEmployees(EmployeeList);
				isValid = false;
			}catch(Exception e) {
				System.out.println("Not an ID");
			}//ends try catch
		
		}while(isValid);//ends do while
		return EmployeeList;
	}//ends the deletion
	//This method will catch
	public static List<Employee> updateEmployee(Scanner input, List<Employee> EmployeeList) throws Exception{

		System.out.println("Please enter the ID of the Employee to be updated");
		boolean isValid = true;
		boolean found = false;
		int EID = -1;
		do {
			try {
				
				int UpdateID = input.nextInt();
				//List<Employee> FutureFormerEmployeeList= new ArrayList<Employee>();
				for(int id = 0; id < EmployeeList.size(); id++) {
					if(UpdateID == EmployeeList.get(id).getId()) {
						EID = EmployeeList.get(id).getId();
						found = true;
					}//ends the if
				}//ends the for
				if(found==false)
					System.out.println("Employee not found. Nothing was deleted");
				listEmployees(EmployeeList);
				isValid = false;
			}catch(Exception e) {
				System.out.println("Not an ID");
			}//ends try catch
		}while(isValid);//ends do while
		if (found == true && EID != -1) {
			String field = getField(input);
			if(field == "First Name") {
				String name = findName(input);
				EmployeeList.get(EID).setfName(name);
			}else if(field == "Last Name") {
				String name = findName(input);
				EmployeeList.get(EID).setlName(name);
				
			}else if(field == "Salary") {
				int salary = getSalary(input);
				EmployeeList.get(EID).setSalary(salary);
			}else if(field == "Department") {
				String department = whichDepartment(input);
				EmployeeList.get(EID).setDepartment(department);
			}else {
				Date firstDay = startDate(input);
				EmployeeList.get(EID).setDateOfEmployment(firstDay);
			}
		}

		return EmployeeList;
	}
	public static void listEmployees(List<Employee> EmployeeList){
		EmployeeList.stream()
		.forEach(System.out::println);
	}//ends listEmployee
	
	//This method will auto populate the list of employees for testing purposes
	public static List<Employee> populate(List<Employee> EmployeeList) throws ParseException{
		
		SimpleDateFormat geek = new SimpleDateFormat("dd / MM / yyyy");
		Employee e1 = new Employee("Clark", "Kent", 50000, "Journalism",geek.parse("01 / 02 / 2020"));
		EmployeeList.add(e1);
		Employee e2 = new Employee("Bruce", "Wayne", 5000000, "Management",geek.parse("01 / 03 / 2020"));
		EmployeeList.add(e2);
		return EmployeeList;
	}//ends populate
	public static String findName(Scanner input) {
		boolean done = false;
		//String RemoveLine = input.nextLine();
		String NewName = "";
		while(!done) {
			try {
			boolean validName = true;
			String name = input.nextLine();
			for(int i = 0; i < name.length();i++) {
				if(!Character.isLetter(name.charAt(i))) {
					System.out.println("Invalid name. Try Again");
					validName = false;
					//i = i+ 100;	
				}//if done
			}//for done
			if(validName == true) {
				NewName = name;
				done = true;
			}//if done
			}catch (Exception e) {
				System.out.println("An error occured");
			}
		}//while done
		return NewName;
	}
	public static int getSalary(Scanner input) {
		int salary = 1;
		//String removeLine = input.nextLine();
		boolean done = false;
		while(!done) {
			try {
				int possible = input.nextInt();
				if(possible < 30000) {
					System.out.println("Not a valid number. Needs to be at least $30,000");
				}else {
					salary = possible;
					done = true;
				}
				
			}catch(Exception e) {
				System.out.println("Invalid input for this. Try inputing a number");
				salary = getSalary(input);
			}
		}
		return salary;
	}
	public static String whichDepartment(Scanner input) {
		String  randomLine = input.nextLine();
		String Department = "";
		System.out.println("There are only 5 departments\n"
				+ "(M)anagement | (H)uman Resources | (J)ournalism | (A)ccounting | (E)diting");
		boolean done = false;
		while(!done) {
			String newLine = input.nextLine();
			if(newLine.equalsIgnoreCase("Management") ||newLine.equalsIgnoreCase("M")){
				Department = "Management";
				done = true;
			}else if(newLine.equalsIgnoreCase("Human Resources")||newLine.equalsIgnoreCase("H")) {
				Department = "Human Resources";
				done = true;
			}else if(newLine.equalsIgnoreCase("Journalism")||newLine.equalsIgnoreCase("J")) {
				Department = "Journalism";
				done = true;
			}else if(newLine.equalsIgnoreCase("Accounting")||newLine.equalsIgnoreCase("A")) {
				Department = "Accounting";
				done = true;
			}else if(newLine.equalsIgnoreCase("Editing")||newLine.equalsIgnoreCase("E")) {
				Department = "Editing";
				done = true;
			}else {
				System.out.println("No Department found. Try Again");
			}
			
		}
		return Department;
	}
	public static Date startDate(Scanner input) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
		Date date = null;
		boolean done = false;
			System.out.println("What day of the month was it?");
			String d1 = input.nextLine();
			System.out.println("What month was it? input month ex . 01, 04");
			d1 = d1 + " / "+input.nextLine();
			System.out.println("Which year?");
			d1 = d1 +" / "+input.nextLine();
			date = format.parse(d1);
		return date;
	}
	public static String getField(Scanner input) {
		String field = "";
		boolean done = false;
		while(!done) {
			System.out.println("Which field are you updating?\n"
					+ "(F)irst Name | (L)ast Name | (S)alary | (D)epartment | Da(t)e");
			field = input.nextLine();
			if(field.equalsIgnoreCase("First Name")|| field.equalsIgnoreCase("F")) {
				field = "First Name";
				done = true;
			}else if(field.equalsIgnoreCase("Last Name")|| field.equalsIgnoreCase("L")) {
				field ="Last Name";
				done = true;
			}else if(field.equalsIgnoreCase("Salary")|| field.equalsIgnoreCase("S")) {
				field ="Salary";
				done = true;
			}else if(field.equalsIgnoreCase("Department")|| field.equalsIgnoreCase("D")) {
				field ="Department";
				done = true;
			}else if(field.equalsIgnoreCase("Date")|| field.equalsIgnoreCase("T")) {
				field ="Date";
				done = true;
			}else {
				System.out.println("Found no field. Try again");
			}
		}
		return field;
	}
}//ends class
