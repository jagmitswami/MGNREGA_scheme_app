package com.usecases;

import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

import com.dao.BdoDao;
import com.dao.BdoDaoImpl;
import com.exception.EmployeeException;
import com.exception.GpmException;
import com.exception.ProjectException;
import com.model.Gpm;
import com.model.GpmEmpDTO;
import com.model.Project;

public class BdoUseCases {
	public BdoUseCases() {
		Scanner bdoScanner = new Scanner(System.in);
		System.out.println("1: Create New Project\n"
				+ "2: Create new G.P. Member Profile\n"
				+ "3: View G.P. Member Details\n"
				+ "4: View All G.P. Members\n"
				+ "5: View Employee Details\n"
				+ "6: View All Employees Working Under a G.P. Member\n"
				+ "7: Logout And Exit");
		int input = bdoScanner.nextInt();
		BdoDao bdoDao1 = new BdoDaoImpl();
		if(input==1) {
			
			Project project = new Project();
			
			System.out.println("Enter New Project Id (Integer): ");
			int pid = bdoScanner.nextInt();
			project.setPid(pid);
			
			System.out.println("Enter New Project Name (Length<=12): ");
			String pname = bdoScanner.next();
			project.setPname(pname);
			
			System.out.println("Enter Wage Per Day Of The New Project: ");
			int wage = bdoScanner.nextInt();
			project.setWage(wage);
			
			System.out.println("Enter New Project Location: ");
			String loc = bdoScanner.next();
			project.setLocation(loc);
			
			try {
				bdoDao1.createProject(project);
			} catch (ProjectException e) {
				System.out.println(e.getMessage());
			}
			BdoUseCases bdo = new BdoUseCases();
			
		}else if(input==2) {
			
			Gpm gpm = new Gpm();
			
			System.out.println("Enter New G.P. Member Id (Integer): ");
			int gid = bdoScanner.nextInt();
			gpm.setGid(gid);
			
			System.out.println("Enter New G.P. Member Name (Length<=12): ");
			String gname = bdoScanner.next();
			gpm.setGname(gname);
			
			System.out.println("Enter Project id For the New Member: ");
			int pid = bdoScanner.nextInt();
			gpm.setPid(pid);
			
			System.out.println("Create Password for New G.P. Member: ");
			String passString = bdoScanner.next();
			gpm.setPassword(passString);
			
			try {
				bdoDao1.createGPM(gpm);
			} catch (GpmException e) {
				System.out.println(e.getMessage());
			}
			BdoUseCases bdo = new BdoUseCases();
			
		}else if(input==3) {
			
			System.out.println("Enter G.P.M. Id");
			int gid = bdoScanner.nextInt();
			try {
				bdoDao1.viewGPMDetails(gid);
			} catch (GpmException e) {
				System.out.println(e.getMessage());
			}
			
		}else if(input==4) {
			
			bdoDao1.getAllGPM();
			
		}else if(input==5) {
			
			System.out.println("Enter Employee Id");
			int eid = bdoScanner.nextInt();
			try {
				bdoDao1.viewEmpDetails(eid);
			} catch (EmployeeException e) {
				System.out.println(e.getMessage());
			}
			
		}else if(input==6) {
			
			System.out.println("Enter G.P.M. Id");
			int gid = bdoScanner.nextInt();
			
			try {
				List<GpmEmpDTO> aList = bdoDao1.getAllEmployeeUnderGpm(gid);
				if(aList.size()>0) System.out.println(aList);
				else System.out.println("No Employee assigned to This G.P. Member");
			} catch (GpmException e) {
				System.out.println(e.getMessage());
			}
			
		}else if(input==8) {
			
			System.out.println("Logged Out Successfully And Exited");
			
		}else {
			
			System.out.println("Wrong Input, Please Try Again");
			new BdoUseCases();
			
		}
		
		bdoScanner.close();
		
	}
	
}
