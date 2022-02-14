package com.study.springgithub.homework2_1226;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class JsonDB {		//Json資料庫
	
	@Autowired
	private Gson gson;
	
	//Json file資料庫存放地
	private static final Path PATH = Paths.get("src/main/java/com/study/springcore/case08/person.json");
	
	//查詢全部
	public List<Person> queryAll() throws Exception
	{
		String jsonStr = Files.readAllLines(PATH).stream().collect(Collectors.joining());
		Type type = new TypeToken<ArrayList<Person>>() {}.getType();
		List<Person> people = gson.fromJson(jsonStr, type);
		// 請將age變為最新年齡
//		Date today = new Date();
//		LocalDate todayLocalDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		
//		for(Person person : people) {
//			LocalDate birthLocalDate = person.getBirth().toInstant()
//									.atZone(ZoneId.systemDefault())
//									.toLocalDate();
//			int age = todayLocalDate.getYear() - birthLocalDate.getYear();
//			person.setAge(age);
//		}
		Date today = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(today);
		int todayYear = calender.get(Calendar.YEAR);
		for(Person person:people)
		{
			calender.setTime(person.getBirth());
			int biythYear =calender.get(Calendar.YEAR);
			int age = todayYear - biythYear;
			person.setAge(age);
		}
		return people;
	}
	
	public boolean add(Person person) throws Exception
	{
		List<Person> people = queryAll();
		//作業 1: 如果person已存在則return false
		//name, age, birth 皆與目前資料庫的某一person資料相同
		//最好先驗證person的資料有沒有存在在這裏面, 這裡不驗證直接做
		LocalDate newPersonBirthLocalDate = person.getBirth().toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
		boolean havePerson = false;
		for(Person p: people)
		{
			LocalDate birthLocalDate = p.getBirth().toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
			if(person.getName().equals(p.getName()) && newPersonBirthLocalDate.equals(birthLocalDate))
			{
				havePerson = true;
			}
		}
		
		//確定沒有Person之後作新增
		if(havePerson)
		{
			System.out.println("Already have person!");
			return false;
		}else {
			people.add(person);
			String newJsonString = gson.toJson(people);
			Files.write(PATH, newJsonString.getBytes("UTF-8"));
			
			return true;
		}
	}
	
	public void changeBirth(String name, Date birth) throws Exception{
		List<Person> people = queryAll();
		for(Person p:people)
		{
			if(name.equals(p.getName()))
				p.setBirth(birth);
		}
		String newJsonString = gson.toJson(people);
		Files.write(PATH, newJsonString.getBytes("UTF-8"));
	}
	
	public void deletePerson(String name) throws Exception
	{
		List<Person> people = queryAll();
		List<Person> othersPeople = people.stream().filter((s) -> !(s).getName().equals(name)).collect(Collectors.toList());
		
		String newJsonString = gson.toJson(othersPeople);
		Files.write(PATH, newJsonString.getBytes("UTF-8"));
		
	}
	
}
