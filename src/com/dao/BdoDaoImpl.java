package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.BdoException;
import com.exception.EmployeeException;
import com.exception.GpmException;
import com.exception.ProjectException;
import com.model.Employee;
import com.model.Gpm;
import com.model.GpmEmpDTO;
import com.model.Project;
import com.utility.DBUtil;

public class BdoDaoImpl implements BdoDao{

	@Override
	public int bdoLogin(int bid, String password) {
		int fixedid = 1;
		String pass = "1234";
		System.out.println(bid+" "+password);
		if(fixedid == bid && pass.equals(password)) {
			System.out.println("Logged in successfully");
			return 1;
		}
		else System.out.println("Invalid id or password");
		return -1;
	}

	@Override
	public void createProject(Project project) throws ProjectException{
		try (Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("insert into Project values(?,?,?,?)");
			ps.setInt(1,project.getPid());
			ps.setString(2,project.getPname());
			ps.setInt(3,project.getWage());
			ps.setString(4,project.getLocation());
			
			int x= ps.executeUpdate();
			if(x>0) {
				
				System.out.println("Project Created Successfully");
				
				
			}
		} catch (SQLException e) {
			throw new ProjectException(e.getMessage());
		}
	}

	@Override
	public void createGPM(Gpm gpm) throws GpmException {
		try (Connection conn= DBUtil.provideConnection()){

			PreparedStatement ps= conn.prepareStatement("insert into Gpm values(?,?,?,?)");
			ps.setInt(1,gpm.getGid());
			ps.setString(2,gpm.getGname());
			ps.setString(3,gpm.getPassword());
			ps.setInt(4,gpm.getPid());
			
			
			int x= ps.executeUpdate();
			if(x>0) {
				System.out.println("GPM Profile Created Successfully");
			}
		} catch (SQLException e) {
			throw new GpmException(e.getMessage());
		}
	}

	@Override
	public void viewGPMDetails(int gid) throws GpmException {
		try (Connection conn= DBUtil.provideConnection()){
			
			
			PreparedStatement ps= conn.prepareStatement("select * from gpm where gid=?");
			
			ps.setInt(1, gid);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				Gpm gpm = new Gpm();
				
				gpm.setGid(rs.getInt("gid"));
				gpm.setGname(rs.getString("gname"));
				gpm.setPid(rs.getInt("pid"));
				gpm.setPassword(rs.getString("password"));
				System.out.println(gpm);
				
			}else
				throw new GpmException("Invalid G.P. Member Id...");
			
		} catch (SQLException e) {
			throw new GpmException(e.getMessage());
		}
	}

	@Override
	public void viewEmpDetails(int eid) throws EmployeeException{
		try (Connection conn= DBUtil.provideConnection()){
			
			
			PreparedStatement ps= conn.prepareStatement("select * from employee where eid=?");
			
			ps.setInt(1, eid);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				Employee emp = new Employee();
				
				emp.setEid(rs.getInt("eid"));
				emp.setEname(rs.getString("ename"));
				emp.setPid(rs.getInt("pid"));
				emp.setWork_days(rs.getInt("work_days"));
				emp.setGid(rs.getInt("gid"));
				emp.setContact_no(rs.getString("contact_no"));
				System.out.println(emp);
				
			}else
				throw new EmployeeException("Invalid Employee Id...");
			
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public void getAllGPM() {
		try (Connection conn= DBUtil.provideConnection()){
			
			
			PreparedStatement ps= conn.prepareStatement("select * from gpm");
			
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				Gpm gpm = new Gpm();
				
				gpm.setGid(rs.getInt("gid"));
				gpm.setGname(rs.getString("gname"));
				gpm.setPid(rs.getInt("pid"));
				gpm.setPassword(rs.getString("password"));
				System.out.println(gpm);
				
			}
			
		} catch (SQLException e) {
			
		}
	}

	@Override
	public List<GpmEmpDTO> getAllEmployeeUnderGpm(int gid) throws GpmException {
		
		List<GpmEmpDTO> list = new ArrayList<>();
		
		try (Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps= conn.prepareStatement("select g.gname, e.eid, e.ename, e.pid, e.work_days "
					+ "from employee e INNER JOIN gpm g "
					+ "ON g.gid=e.gid and g.gid=?");
			ps.setInt(1, gid);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				GpmEmpDTO dto = new GpmEmpDTO();
				
				dto.setGname(rs.getString("g.gname"));
				dto.setEid(rs.getInt("e.eid"));
				dto.setEname(rs.getString("e.ename"));
				dto.setPid(rs.getInt("e.pid"));
				dto.setWork_days(rs.getInt("e.work_days"));
				list.add(dto);
				
			}
			
		} catch (SQLException e) {
			throw new GpmException(e.getMessage());
		}
		
		return list;
	}

}
