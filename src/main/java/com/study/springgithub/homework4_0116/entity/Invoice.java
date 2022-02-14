package com.study.springgithub.homework4_0116.entity;

import java.util.Date;
import java.util.List;

// 發票
public class Invoice {
	private Integer id;
	private Date invdate;
	
	private List<Item> items;

	public Invoice() {
	}

	public Invoice(Date invdate, List<Item> items) {
		super();
		this.invdate = invdate;
		this.items = items;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInvdate() {
		return invdate;
	}

	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invdate=" + invdate + ", items=" + 
				items + "]";
	}
	
	
}
