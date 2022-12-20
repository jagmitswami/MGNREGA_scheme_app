package com.model;

import java.util.Date;

public class GpmEmpDTO {
	private String gname;
	
	private int eid;
	private String ename;
	private int pid;
	private int work_days;
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getWork_days() {
		return work_days;
	}
	public void setWork_days(int work_days) {
		this.work_days = work_days;
	}
	@Override
	public String toString() {
		return "GpmEmpDTO [gname=" + gname + ", eid=" + eid + ", ename=" + ename + ", pid=" + pid + ", work_days="
				+ work_days + "]";
	}
	
	
	
}
