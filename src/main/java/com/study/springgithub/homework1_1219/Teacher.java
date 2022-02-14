package com.study.springgithub.homework1_1219;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Teacher {
	private Integer id;		//講師代號
	private String name;	//姓名
	private Set<Clazz> clazzs;	//授課項目
	private List<String> experties;	//教師專長
	private Map<String, Integer> salary;//薪資結構
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Clazz> getClazzs() {
		return clazzs;
	}
	public void setClazzs(Set<Clazz> clazzs) {
		this.clazzs = clazzs;
	}
	public List<String> getExperties() {
		return experties;
	}
	public void setExperties(List<String> experties) {
		this.experties = experties;
	}
	public Map<String, Integer> getSalary() {
		return salary;
	}
	public void setSalary(Map<String, Integer> salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", clazzs=" + clazzs + ", experties=" + experties + ", salary="
				+ salary + "]";
	}
	
	
	
	
	
	
}
