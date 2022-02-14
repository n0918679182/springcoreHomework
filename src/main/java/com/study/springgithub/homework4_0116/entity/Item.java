package com.study.springgithub.homework4_0116.entity;

import java.util.List;
import static java.util.stream.Collectors.toList;;

// 發票項目
public class Item {
	private Integer id;
	private Integer amount;
	private Integer ipid;
	private Integer invid;
	
	private List<ItemProduct> itemProducts;
	private List<Invoice> invoices;
	
	public Item() {
	}

	public Item(Integer amount, Integer ipid, Integer invid, List<ItemProduct> itemProducts, List<Invoice> invoices) {
		super();
		this.amount = amount;
		this.ipid = ipid;
		this.invid = invid;
		this.itemProducts = itemProducts;
		this.invoices = invoices;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getIpid() {
		return ipid;
	}

	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}

	public Integer getInvid() {
		return invid;
	}

	public void setInvid(Integer invid) {
		this.invid = invid;
	}

	public List<ItemProduct> getItemProducts() {
		return itemProducts;
	}

	public void setItemProducts(List<ItemProduct> itemProducts) {
		this.itemProducts = itemProducts;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	public String toString() {
		return "Item [編號=" + id + ", 商品數量=" + amount + ", 商品編號=" + ipid + ", 發票編號=" + invid +
				", 商品名稱=" + itemProducts.stream().map(ItemProduct::getText).collect(toList()) +"]";
//				", invoices=" + invoices.stream().map(Invoice::getInvdate).collect(toList()) + "]";
	}
	
	
	
}
