package com.study.springgithub.homework2_1226;

import java.util.Date;
import java.util.List;

public interface PersonService {
	boolean append(String name, Date birth);
	boolean append(Person person);
	List<Person> findAllPersons();
	Person getPerson(String name);
	void updateBirth(String name, Date birth);
	void deletePerson(String name);
}
