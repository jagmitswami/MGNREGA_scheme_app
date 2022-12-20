package com.usecases;

import java.util.Scanner;

import com.dao.BdoDao;
import com.dao.BdoDaoImpl;
import com.dao.GpmDao;
import com.dao.GpmDaoImpl;
import com.exception.GpmException;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1: BDO Login\n"
				+ "2: G.P. Member Login\n"
				+ "3: Exit\n");
		int opr = sc.nextInt();
		if(opr==1) {
			BdoDao bdoDao = new BdoDaoImpl();
			int attempt = 0;
			int status = 0;
			while(attempt<3) {
				System.out.println("Enter id: ");
				int id = sc.nextInt();
				System.out.println("Enter Password: ");
				String pass = sc.next();
				status = bdoDao.bdoLogin(id,pass);
				if(status == 1) break;
				attempt++;
				if(attempt==3) {
					System.out.println("Login attempt limit exceeded, please try again.");
					break;
				}
				System.out.println(3-attempt+" attempt remaining");
			}
			if(status==1) {
				BdoUseCases bdo = new BdoUseCases();
			}
		}else if(opr==2) {
			int attempt = 0;
			int status = 0;
			int id = 0;
			while(attempt<3) {
				
				GpmDao gpmDao = new GpmDaoImpl();
				
				System.out.println("Enter id: ");
				id = sc.nextInt();
				
				System.out.println("Enter Password: ");
				String pass = sc.next();
				
				try {
					status = gpmDao.gpmLogin(id,pass);
				} catch (GpmException e) {
					System.out.println(e.getMessage());
				}
				
				if(status == 1) break;
				attempt++;
				
				if(attempt==3) {
					System.out.println("Login attempt limit exceeded, please try again.");
					break;
				}
				
				System.out.println(3-attempt+" attempt remaining");
			}
			
			if(status==1) {
				GpmUseCases gpmUseCases = new GpmUseCases(id);
			}
				
		}else if(opr==3){
			System.out.println("Exit Successfull");
		}else {
			System.out.println("Wrong Input, please try again.");
		}
		sc.close();
	}
}
