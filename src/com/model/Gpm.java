package com.model;

public class Gpm {
	private int gid;
	private String gname;
	private int pid;
	private String password;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Gpm [gid=" + gid + ", gname=" + gname + ", pid=" + pid + ", password=" + password + "]";
	}
	
	
}
