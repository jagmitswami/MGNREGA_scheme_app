package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.exception.EmployeeException;
import com.exception.GpmException;
import com.model.Employee;
import com.model.GpmEmpDTO;
import com.utility.DBUtil;

public interface GpmDao {
	public int gpmLogin(int id, String password) throws GpmException;
	public void createEmp(Employee employee) throws EmployeeException;
	public void viewEmpDetails(int eid) throws EmployeeException;
	public List<GpmEmpDTO> viewAllEmpDetailsUnderMe(int gid);
	public void updateWorkingDays(int eid) throws EmployeeException;
}
