package com.study.springgithub.homework5_0123.entity;

import java.util.Date;

public class theOrderLog {
	private Integer oid;
	private String bname;
	private String wname;
	private Integer amount;
	private Integer totalprice;
	private Date ct;
	public theOrderLog(Integer oid, String bname, String wanme, Integer amount, Integer totalprice, Date ct) {
		super();
		this.oid = oid;
		this.bname = bname;
		this.wname = wanme;
		this.amount = amount;
		this.totalprice = totalprice;
		this.ct = ct;
	}
	public theOrderLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wanme) {
		this.wname = wanme;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Integer totalprice) {
		this.totalprice = totalprice;
	}
	public Date getCt() {
		return ct;
	}
	public void setCt(Date ct) {
		this.ct = ct;
	}
	@Override
	public String toString() {
		return "theOrderLog [oid=" + oid + ", bname=" + bname + ", wname=" + wname + ", amount=" + amount
				+ ", totalprice=" + totalprice + ", ct=" + ct + "]";
	}
	
	
}
