package com.dao;
import com.model.*;
import com.exception.EmployeeException;
import com.exception.GpmException;
import com.exception.ProjectException;

import java.util.List;

import com.*;
public interface BdoDao {
	public int bdoLogin(int id, String password);
	public void createProject(Project project) throws ProjectException;
	public void createGPM(Gpm gpm) throws GpmException;
	public void viewGPMDetails(int gid) throws GpmException;
	public void getAllGPM();
	public void viewEmpDetails(int eid) throws EmployeeException;
	public List<GpmEmpDTO> getAllEmployeeUnderGpm(int gid) throws GpmException;
}
