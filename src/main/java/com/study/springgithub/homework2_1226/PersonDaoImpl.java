package com.study.springgithub.homework2_1226;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao{
	
	@Autowired
	private JsonDB jsonDB;
	
	@Override
	public boolean create(Person person) {
		Boolean check = null;
		try {
			check = jsonDB.add(person);
		} catch (Exception e) {
			e.printStackTrace();
			check=false;
		}
		return check;
	}

	@Override
	public List<Person> readAll() {
		List<Person> people = null;
		try {
			people = jsonDB.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return people;
	}

	@Override
	public void updateBirth(String name, Date birth) {
		try {
			jsonDB.changeBirth(name, birth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deletePerson(String name) {
		try {
			jsonDB.deletePerson(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
