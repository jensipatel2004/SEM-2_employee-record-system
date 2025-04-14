import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ERS2 {
    // JDBC URL, username, and password of the database
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    public static void main(String[] args) throws Exception{
        
            Scanner scanner = new Scanner(System.in);
            LinkedList<Employee> employees=new LinkedList<>();

            while (true) {
                System.out.println("-----------------------------------------");
                System.out.println("Welcome To Employee Record System Program");
                System.out.println("-----------------------------------------");
                System.out.println("1. Add Employee");
                System.out.println("2. Display Employee");
                System.out.println("3. Search Employee");
                System.out.println("4. Update Employee");
                System.out.println("5. Delete Employee");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        addEmployee(employees);
                        break;
                    case 2:
                        displayEmployee(employees);
                        break;
                    case 3:
                       searchEmployee();
                        break;
                    case 4:
                        System.out.println(" update employee detail");
                        System.out.println(" 1. update employee salary by id ");
                        System.out.println(" 2. update employee position by id");
                        System.out.println(" 3. update employee type as resign and add resign date");
                        System.out.println(" 4. exit");
                        System.out.println("Enter your choice");
                        int ch = scanner.nextInt();
                        scanner.nextLine();
                        switch(ch){

                        case 1:
                        System.out.println("Enter employee id to update salary");
                        int IdToUpdate = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character 
                        System.out.println("Enter new employee salary :");
                        double newsalary=scanner.nextDouble();
                        scanner.nextLine();
                
						updateEmpSalary(IdToUpdate, newsalary);
                        
                        System.out.println("Employee updated successfully");
                        break;

                        case 2:
                        System.out.println("Enter employee id to update position");
                        int IdToUpdate1 = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character 
                        System.out.println("Enter new employee position :");
                        String newposition = scanner.nextLine();
                        updateEmpPosition(IdToUpdate1, newposition);
                        
                        System.out.println("Employee updated successfully");
                        break;

                        case 3:
                        System.out.println("Enter employee id to resign employee");
                        int IdToUpdate2 = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character 
                        System.out.println("Enter new employee type as resign :");
                        String newtype = scanner.nextLine();
                        System.out.println("Enter employee resign date ( format: DD-MM-YYYY) ");
                        String newresigningDate= scanner.nextLine();
                        updateEmpResign(IdToUpdate2, newtype, newresigningDate);
                        
                        System.out.println("Employee updated successfully");
                        break;

                        case 4:
                        System.out.println("Exit from the update function.");
                        return;
                        default:
                        System.out.println("Invalid choice. Please try again.");
                        }
                        break;
                    case 5:
                        System.out.println("Enter employee id to delete");
                        int employeeIdToDelete = scanner.nextInt();
                        deleteEmployee(employees, employeeIdToDelete);
                    
                        break;
                    case 6:
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        
    }

    
    private static void addEmployee(LinkedList<Employee> employees) throws Exception{
        Scanner scanner=new Scanner(System.in);
        // Get input of Employee ID
		boolean isValidInt = false;
		int empID = 0;
		do {
			System.out.println("Enter Employee ID:");
			try {
				empID = scanner.nextInt();
					scanner.nextLine();
					isValidInt = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid Integer number format!");
				scanner.nextLine(); // clear the input buffer
			}
		} while (!isValidInt);

        // Get input of employee name
        System.out.println("Enter  employee name :");
        String name = scanner.nextLine();

        // Get input of Employee age
        System.out.println("Enter  employee age :");
        int age=scanner.nextInt();
        scanner.nextLine();
					
		// Get input of Employee Contact Details (Address)
        System.out.println("Enter employee address:");
        String address = scanner.nextLine();
					
		// Get input of Employee Contact Details (Mobile number)
		boolean isValidNumber = false;
		String mobileNumber = "";
		do {
			System.out.println("Enter employee mobile number(number between 0 to 9):");
			mobileNumber = scanner.nextLine();
			try {
				if (mobileNumber.matches("[0-9]+")) {
					if (mobileNumber.length() == 10) {
						isValidNumber = true;
					} else {
						System.out.println("Mobile number should be 10 digits long!");
					}
				} else {
					System.out.println("Invalid Mobile number format!");
				}
				} catch (NumberFormatException e) {
							System.out.println("Invalid mobile number format!");
				}
			} while (!isValidNumber);

			// Get input of Employee Contact Details (Email ID)
			boolean isValidEmail = false;
			String email = "";
			do {
			    System.out.println("Enter employee email(format: a@a.com): ");
			try {
				email = scanner.nextLine();
				if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
					isValidEmail = true;
				} else {
					System.out.println("Invalid email address format!");
				}
			    } catch (Exception e) {
					System.out.println("Invalid email address format!");
					scanner.nextLine(); // clear the input buffer
					}
				} while (!isValidEmail);

				// Get input of Employee Designation/Position
                System.out.println("Enter employee position:");
                String position = scanner.nextLine();
						
				// Get input of Employee Payroll details
                System.out.println("Enter employee salary:");
                double salary = scanner.nextInt();
                scanner.nextLine();

				// Get input of Employee Type
                System.out.println("Enter employee type (Trainee/Probation/Permanent/Contract)");
                String type = scanner.nextLine();

				// Get input of Employee Joining Date
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				boolean isValidDateFormat = false;
                String joiningDate;
				do {
					System.out.println("Enter employee joining date (Format should be 'dd-MM-yyyy')");
					joiningDate = scanner.nextLine();
					try {
						Date formatDate = dateFormat.parse(joiningDate);
						if (joiningDate.equals(dateFormat.format(formatDate))) {
							isValidDateFormat = true;
						} else {
							System.out.println("Date is not in the correct format. Correct format is dd-MM-yyyy");
						}
					} catch (Exception e) {
						System.out.println("Invalid date format!");
					}
				} while (!isValidDateFormat);
					
				// Set Resign Date as "Not Applicable"
				String resigningDate = "NA";
                Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASSWORD );
        try {
            conn.setAutoCommit(false); // Start a transaction

            String sql = "INSERT INTO employee1 (e_id, e_name, e_age, e_address, e_contactNumber, e_emailAddress, e_position, e_salary, e_type, e_joiningDate, e_resigningDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, empID);
                pstmt.setString(2, name);
                pstmt.setInt(3, age);
                pstmt.setString(4, address);
                pstmt.setString(5, mobileNumber);
                pstmt.setString(6, email);
                pstmt.setString(7, position);
                pstmt.setDouble(8, salary);
                pstmt.setString(9, type);
                pstmt.setString(10, joiningDate);
                pstmt.setString(11, resigningDate);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    
                    System.out.println("Employee added successfully." );
                } else {
                    System.out.println("Failed to add employee.");
                }
            }

            conn.commit(); // Commit the transaction
        } catch (SQLException e) {
            conn.rollback(); // Rollback the transaction in case of an error
            e.printStackTrace();
        } finally {
            conn.setAutoCommit(true); // Restore auto-commit mode
        }
    }


    private static void searchEmployee() throws Exception{

    Scanner scanner=new Scanner(System.in);    
    System.out.println("Enter id to search employee details");
    int idToSearch=scanner.nextInt();
    Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASSWORD );    
    try {
        // Start a transaction.
        conn.setAutoCommit(false);

        // Create a prepared statement to search for an employee by ID.
        String searchQuery = "SELECT * FROM employee1 WHERE e_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(searchQuery);
        preparedStatement.setInt(1, idToSearch);

        // Execute the query.
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Employee found, retrieve and display the information.
            int employeeId = resultSet.getInt("e_id");
            String employeeName = resultSet.getString("e_name");
            int employeeAge = resultSet.getInt("e_age");
            String employeeAddress = resultSet.getString("e_address");
            String employeePhoneNumber = resultSet.getString("e_contactNumber");
            String employeeEmail = resultSet.getString("e_emailAddress");
            String employeePosition = resultSet.getString("e_position");
            double employeeSalary = resultSet.getDouble("e_salary");
            String employeeType = resultSet.getString("e_type");
            String employeejoiningDate = resultSet.getString("e_joiningDate");
            String employeeresigningDate = resultSet.getString("e_resigningDate");
            
            System.out.println("Employee ID: " + employeeId);
            System.out.println("Employee Name: " + employeeName);
            System.out.println("Employee age: " + employeeAge );
            System.out.println("Employee address: " + employeeAddress);
            System.out.println("Employee phone no.: " + employeePhoneNumber);
            System.out.println("Employee email: " + employeeEmail);
            System.out.println("Employee position: " + employeePosition);
            System.out.println("Employee Salary: " + employeeSalary);
            System.out.println("Employee type: " + employeeType);
            System.out.println("Employee joining date: " + employeejoiningDate);
            System.out.println("Employee resigning date: " + employeeresigningDate);
            
        } else {
            System.out.println("Employee not found.");
        }

        // Commit the transaction.
        conn.commit();

        // Close the resources.
        resultSet.close();
        preparedStatement.close();
        conn.close();
        } catch (SQLException e) {
            // Handle any SQL exceptions.
            e.printStackTrace();
            try {
                // Rollback the transaction in case of an error.
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        }
    }

    private static void updateEmpSalary(int IdToUpdate,  double newsalary) throws Exception{
       
         try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASSWORD ))
        {
            // Start a transaction.
            conn.setAutoCommit(false);
            
            String updateQuery = "UPDATE employee1 SET  e_salary = ? WHERE e_id = ?";
            
            try(PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)){
              
            preparedStatement.setDouble(1, newsalary);
            preparedStatement.setInt(2, IdToUpdate);

            // Execute the update.
            preparedStatement.executeUpdate();

            // Commit the transaction.
            conn.commit(); 
        }     
        catch (SQLException e) { 
            e.printStackTrace();
            //conn.rollback();
        } 
    } 
catch (SQLException e) {     
            e.printStackTrace();
        }
    }

    private static void updateEmpPosition(int IdToUpdate1,  String newPosition) throws Exception{
       
         try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASSWORD ))
        {
            // Start a transaction.
            conn.setAutoCommit(false);
            
            String updateQuery = "UPDATE employee1 SET  e_position = ? WHERE e_id = ?";
            
            try(PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)){
              
            preparedStatement.setString(1, newPosition);
            preparedStatement.setInt(2, IdToUpdate1);

            // Execute the update.
            preparedStatement.executeUpdate();

            // Commit the transaction.
            conn.commit(); 
        }     
        catch (SQLException e) { 
            e.printStackTrace();
            //conn.rollback();
        } 
    } 
catch (SQLException e) {     
            e.printStackTrace();
        }
    }

    private static void updateEmpResign(int IdToUpdate2,  String newtype, String newresigningDate) throws Exception{
       
         try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASSWORD ))
        {
            // Start a transaction.
            conn.setAutoCommit(false);
            
            String updateQuery = "UPDATE employee1 SET  e_type = ?, e_resigningDate = ? WHERE e_id = ?";
            
            try(PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)){
              
            preparedStatement.setString(1, newtype);
            preparedStatement.setString(2, newresigningDate);
            preparedStatement.setInt(3, IdToUpdate2);

            // Execute the update.
            preparedStatement.executeUpdate();

            // Commit the transaction.
            conn.commit(); 
        }     
        catch (SQLException e) { 
            e.printStackTrace();
            //conn.rollback();
        } 
    } 
catch (SQLException e) {     
            e.printStackTrace();
        }
    }

        private static void displayEmployee(LinkedList<Employee> employees) throws Exception{
            Scanner scanner=new Scanner(System.in);
            Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASSWORD );
            try{
               // conn.setAutoCommit(false);
                String displayQuery= "select * from employee1";
                PreparedStatement preparedStatement = conn.prepareStatement(displayQuery);
                ResultSet rs= preparedStatement.executeQuery();

                while(rs.next()){
                    System.out.println();
                    System.out.println("employee id is : " + rs.getInt(1));
                    System.out.println("employee name is : " + rs.getString(2));
                    System.out.println("employee age is : " + rs.getInt(3));
                    System.out.println("employee address is : " + rs.getString(4));
                    System.out.println("employee contact no. is : " + rs.getString(5));
                    System.out.println("employee email is : " + rs.getString(6));
                    System.out.println("employee position is : " + rs.getString(7));
                    System.out.println("employee salary is : " + rs.getDouble(8));
                    System.out.println("employee type is : " + rs.getString(9));
                    System.out.println("employee joining date is : " + rs.getString(10));
                    System.out.println("employee resigning date is : " + rs.getString(11));
                    System.out.println();
                    
                }
            }catch (Exception e) {
            // Handle any SQL exceptions.
            e.printStackTrace();
            
            }
    }

    
    private static void deleteEmployee(LinkedList<Employee> employees, int employeeIdToDelete){
    try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
        String deleteQuery = "DELETE FROM employee1 WHERE e_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, employeeIdToDelete);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("No employee found with the provided ID.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Handle errors here
    }
}
}


class Employee {
    private int employeeID;
    private String name;
    private int age;
	private String address;
	private String mobileNumber;
	private String email;
	private String position;
	private double salary;
	private String type;
	private String joiningDate;
	private String resigningDate;

    // Constructor method
    public Employee(int employeeID, String name,int age, String address, String mobileNumber, String email, String position, double salary, String type, String joiningDate, String resigningDate) {
		this.employeeID = employeeID;
        this.name = name;
        this.age=age;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.position = position;
        this.salary = salary;
        this.type = type;
        this.joiningDate = joiningDate;
		this.resigningDate = resigningDate;
    }

    // Getters and setters
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getResigningDate() {
        return resigningDate;
    }

    public void setResigningDate(String resigningDate) {
        this.resigningDate = resigningDate;
    }
}


    


    