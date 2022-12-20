package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.BdoException;
import com.exception.EmployeeException;
import com.exception.GpmException;
import com.model.Employee;
import com.model.Gpm;
import com.model.GpmEmpDTO;
import com.utility.DBUtil;

public class GpmDaoImpl implements GpmDao{

	@Override
	public int gpmLogin(int gid, String password) throws GpmException {
		
		int res = -1;
		try (Connection conn= DBUtil.provideConnection()){
			
			
			PreparedStatement ps= conn.prepareStatement("select * from gpm where gid=? AND password = ?");
			
			ps.setInt(1, gid);
			ps.setString(2, password);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				
				System.out.println("Logged in successfully");
				res = 1;
				
			}else
				throw new GpmException("Invalid Username or password..");
			
		} catch (SQLException e) {
			throw new GpmException(e.getMessage());
		}
		return res;
	}

	@Override
	public void createEmp(Employee employee) throws EmployeeException {
		try (Connection conn= DBUtil.provideConnection()){


			PreparedStatement ps= conn.prepareStatement("insert into Employee values(?,?,?,?,?,?)");
			ps.setInt(1,employee.getEid());
			ps.setString(2,employee.getEname());
			ps.setInt(3,employee.getPid());
			ps.setInt(4,employee.getWork_days());
			ps.setInt(5,employee.getGid());
			ps.setString(6,employee.getContact_no());
			
			int x= ps.executeUpdate();
			if(x>0) {
				System.out.println("Employee Added Successfully");
			}
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public void viewEmpDetails(int eid) throws EmployeeException{
		try (Connection conn= DBUtil.provideConnection()){
			
			
			PreparedStatement ps= conn.prepareStatement("select * from employee where eid=?");
			
			ps.setInt(1, eid);
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				Employee gpm = new Employee();
				
				gpm.setEid(rs.getInt("eid"));
				gpm.setEname(rs.getString("ename"));
				gpm.setPid(rs.getInt("eid"));
				gpm.setWork_days(rs.getInt("work_days"));
				gpm.setGid(rs.getInt("gid"));
				gpm.setContact_no(rs.getString("contact_no"));
				System.out.println(gpm);
				
			}else
				throw new EmployeeException("Invalid Gid...");
			
		} catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public void updateWorkingDays(int eid) throws EmployeeException {
		try (Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement pStatement = conn.prepareStatement("update employee set work_days = work_days+1 where eid = ?");
			pStatement.setInt(1, eid);
			int x = pStatement.executeUpdate();
			if(x>0) System.out.println("Updated Successfully");
			
		}catch (SQLException e) {
			throw new EmployeeException(e.getMessage());
		}
	}

	@Override
	public List<GpmEmpDTO> viewAllEmpDetailsUnderMe(int gid) {
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
			
		}
		
		return list;
	}

}
