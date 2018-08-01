package com.cg.eev.ui;

import java.util.List;
import java.util.Scanner;

import com.cg.eev.bean.EmployeeBean;
import com.cg.eev.exception.EmployeeExcpetion;
import com.cg.eev.service.EmployeeServiceImpl;
import com.cg.eev.service.IEmployeeService;

public class Client {

	public static void main(String[] args) {
		int choice;
		String name;
		boolean flag;
		Scanner sc = new Scanner(System.in);
		try {
			IEmployeeService service = new EmployeeServiceImpl();
			int test = 10;
			do {
				System.out.println("Choose an operation");
				System.out.println("1. Add Employee");
				System.out.println("2. Search employee by Insurance Scheme");
				System.out.println("3. Delete Employee");
				System.out.println("4. Sort Employee based on Salary");
				System.out.println("5. Exit");
				System.out.println("******************************");
				System.out.print("\nPlease Enter a Choice : ");
				choice = sc.nextInt();
				System.out.println("\n******************************");

				switch (choice) {

				case 1:
					do {

						System.out.println("Enter Employee Name");
						name = sc.next();
						flag = service.validateName(name);
						if (flag == false)
							System.out.println("Name not Valid");
					} while (flag == false);

					System.out.println("Enter Employee Salary : ");
					int salary = sc.nextInt();

					EmployeeBean employeeBean = new EmployeeBean();
					employeeBean.setSalary(salary);
					employeeBean.setName(name);

					try {
						int empId = service.addEmployee(employeeBean);
						System.out.println("Succesfull");
						System.out.println("Employee Information stored succesfully with employee ID: " + empId);
					} catch (EmployeeExcpetion e) {
						System.out.println(e.getMessage());

					}
					break;

				case 2:
					System.out.println("Enter Employee Insurance Scheme");
					String scheme = sc.next();
					List<EmployeeBean> myList = service.getEmployeeDetails(scheme);
					if (myList.size() != 0) {
						for (EmployeeBean employeeBean2 : myList) {
							System.out.println("ID: " + employeeBean2.getEmpId());
							System.out.println("Name: " + employeeBean2.getName());
							System.out.println("Slary: " + employeeBean2.getSalary());
							System.out.println("Designation: " + employeeBean2.getDesignation());
							System.out.println("Insurance Scheme: " + employeeBean2.getInsuranceScheme() + "\n");

						}
					} else {
						System.out.println("No Employee Found With insurance Scheme: " + scheme);
					}

					break;
				case 3:
					System.out.println("Enter Employee Id to be deleted");
					int empId = sc.nextInt();
					EmployeeBean mybean = service.getEmployeeDetailsByID(empId);
					if (mybean != null) {
						service.deleteEmployee(empId);
						System.out.println("Employee with ID: " + empId + " id deleted Successfully");
					} else {
						System.out.println("Employee with ID: " + empId + " id Not Found");
					}
					break;
				case 4:
					List<EmployeeBean> myListt = service.getSortedEmployeeDetails();
					for (EmployeeBean employeeBean2 : myListt) {
						System.out.print("\t " + employeeBean2.getEmpId());
						System.out.print("\t " + employeeBean2.getName());
						System.out.print("\t " + employeeBean2.getSalary());
						System.out.print("\t " + employeeBean2.getDesignation());
						System.out.print("\t " + employeeBean2.getInsuranceScheme() + "\n");
					}
					break;
				}
				System.out.print("Do you want to continue? 1-Yes  0 - No : ");
				choice = sc.nextInt();

			} while (choice == 1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}

	}

}
