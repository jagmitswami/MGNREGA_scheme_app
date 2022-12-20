package com.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
	
	private int eid;
	private String ename;
	private int pid;
	private int work_days;
	private int gid;
	private String contact_no;
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
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", pid=" + pid + ", work_days=" + work_days + ", gid="
				+ gid + ", contact_no=" + contact_no + "]";
	}
}
