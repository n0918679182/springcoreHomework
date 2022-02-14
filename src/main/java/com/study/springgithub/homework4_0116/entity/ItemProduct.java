package com.study.springgithub.homework4_0116.entity;

import java.util.List;

// 商品
public class ItemProduct {
	private Integer id;
	private String text;
	private Integer price;
	private Integer inventory;
	
	private List<Item> items;

	public ItemProduct(String text, Integer price, Integer inventory, List<Item> items) {
		super();
		this.text = text;
		this.price = price;
		this.inventory = inventory;
		this.items = items;
	}

	public ItemProduct() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ItemProduct [id=" + id + ", text=" + text + ", price=" + price + ", inventory=" + inventory + ", "
				+ "items=" + items + "]";
	}
	
	
	
	
}
