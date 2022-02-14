package com.study.springgithub.homework2_1226;

import java.util.Date;
import java.util.List;

public interface PersonDao {
	//建立 person
	public boolean create(Person person);
	//查詢所有 Person
	public List<Person> readAll();
	
	public void updateBirth(String name, Date birth);
	
	public void deletePerson(String name);
}
