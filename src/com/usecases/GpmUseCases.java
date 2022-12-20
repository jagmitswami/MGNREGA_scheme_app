package com.usecases;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.dao.BdoDao;
import com.dao.BdoDaoImpl;
import com.dao.GpmDao;
import com.dao.GpmDaoImpl;
import com.exception.EmployeeException;
import com.exception.GpmException;
import com.model.Employee;
import com.model.GpmEmpDTO;

public class GpmUseCases {

	GpmUseCases(int gid) {
		
		Scanner gpmScanner = new Scanner(System.in);
		
		System.out.println("1: Create new Employee Profile\n"
				+ "2: View Employee Details\n"
				+ "3: View All Employees Working Under G.P. Member\n"
				+ "4: Logout And Exit");
		
		int input = gpmScanner.nextInt();
		GpmDao gpmDao1 = new GpmDaoImpl();
		
		if (input==1) {

			Employee employee = new Employee();
			
			System.out.println("Enter New Employee Id: ");
			int eid = gpmScanner.nextInt();
			employee.setEid(eid);
			
			System.out.println("Enter New Employee Name: ");
			String ename = gpmScanner.next();
			employee.setEname(ename);
			
			System.out.println("Enter Project Id: ");
			int pid = gpmScanner.nextInt();
			employee.setPid(pid);
			
			System.out.println("Enter working days: ");
			int work_days = gpmScanner.nextInt();
			employee.setWork_days(work_days);
			
			System.out.println("Enter New Employee Contact No.: ");
			String contact_no = gpmScanner.next();
			employee.setContact_no(contact_no);
			
			employee.setGid(gid);
			System.out.println(employee);
			
			try {
				gpmDao1.createEmp(employee);
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			}
			
		}else if (input==2) {
			
			System.out.println("Enter Employee Id");
			int eid = gpmScanner.nextInt();
			try {
				gpmDao1.viewEmpDetails(eid);
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			}
			
		}else if (input==3) {
			
			List<GpmEmpDTO> aList = gpmDao1.viewAllEmpDetailsUnderMe(gid);
			if(aList.size()>0) System.out.println(aList);
			else System.out.println("No Employee assigned to This G.P. Member");
			
		}else if (input==4) {
			
			System.out.println("Logged out And Exited Successfully");
			
		}else {
			
			System.out.println("Wrong input please try again...");
			new GpmUseCases(gid);
			
		}
		gpmScanner.close();
	}
	
}
