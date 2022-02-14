package com.study.springgithub.homework2_1226;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/* 做一個前端的系統
 * 功能:
 * 
 * 1. 建立Person資料
 * 		-> 輸入name, birth
 * 2. 取得Person全部資料
 * 		-> 不用輸入參數
 * 3. 根據姓名取得Person
 * 		-> 輸入name
 * 4. 取得今天生日的Person
 * 		-> 輸入今天日期
 * 5. 取得某一歲數以上的Person
 * 		-> 輸入age
 * 6. 根據姓名來修改Person的生日
 * 		-> 輸入name, birth
 * 7. 根據姓名來刪除Person
 * 		-> 輸入name
 * 
 * */	
@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	//建立Person資料
	public void addPerson(String name, int yyyy, int mm, int dd)
	{
		// 1. 判斷name, yyyy, mm, dd 是否有資料?
//		if(AlreadyHavePerson(name))
//		{
//			System.out.println("已有此人");
//		}
//		else {
			// 2. 將yyyy/mm/dd轉日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date birth = sdf.parse(yyyy+"/"+mm+"/"+dd);
				addPerson(name, birth);
				printAllPersons();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
//		}
	}
	
	//建立Person資料
	public void addPerson(String name, Date birth)
	{
		// 1. 判斷name與birth是否有資料?
		// 2. 建立Person資料
		boolean check = personService.append(name,birth);
		System.out.println("add person " + check);
	}
	
	//取得Person全部資料
	public void printAllPersons() {
		//System.out.println(personService.findAllPersons());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 資料呈現
		List<Person> people = personService.findAllPersons();
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
		System.out.println("+--------------+---------+--------------+");
		for(Person p : people) {
			String birthday = sdf.format(p.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
			System.out.println("+--------------+---------+--------------+");
		}
	}
	
	//根據姓名取得Person
	public void getPersonByName(String name)
	{
//		Person person = personService.getPerson(name);
//		return person;
		if(AlreadyHavePerson(name))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			System.out.println("+--------------+---------+--------------+");
			System.out.println("|     name     |   age   |   birthday   |");
			System.out.println("+--------------+---------+--------------+");
			
			Person p2 = personService.getPerson(name);
			String birthday = sdf.format(p2.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n", p2.getName(), p2.getAge(), birthday);
			System.out.println("+--------------+---------+--------------+");
			
		}else {
			System.out.println("查無此人");
		}
		
	}
	
	
	//取得今天生日的Person
	public void PrintBirthdayGuy()
	{
		List<Person> people = personService.findAllPersons();
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date dateObj = calendar.getTime();
		String formattedDate = sdf.format(dateObj);
		Optional<Person> optPerson = people.stream().filter(p -> sdf.format(p.getBirth()).equals(formattedDate)).findFirst();
		
		if(optPerson.isPresent())
		{
			Person p2 = optPerson.get();
			String birthday = sdf.format(p2.getBirth());
			System.out.println("+--------------+---------+--------------+");
			System.out.println("|     name     |   age   |   birthday   |");
			System.out.println("+--------------+---------+--------------+");
			System.out.printf("| %-12s | %7d | %12s |\n", p2.getName(), p2.getAge(), birthday);
			System.out.println("+--------------+---------+--------------+");
		}else {
			System.out.println("今天沒人生日呦!");
		}
		
		
	}
	
	//取得某一歲數以上的Person
	public void getTheAge(int age)
	{
		List<Person> people = personService.findAllPersons();
		List<Person> ThePerson = people.stream().filter(p ->p.getAge()>=age).collect(Collectors.toList());
//		System.out.println(ThePerson);
		if(ThePerson.isEmpty())
		{
			System.out.println("查無資料喲!");
		}else {
			System.out.println("+--------------+---------+--------------+");
			System.out.println("|     name     |   age   |   birthday   |");
			System.out.println("+--------------+---------+--------------+");
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String birthday="";
			for(Person p:ThePerson) {
				birthday = sdf.format(p.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
				System.out.println("+--------------+---------+--------------+");
			}
		}
		
	}
	
	//根據姓名來修改Person的生日
	public void changeAgeByName(String name, int yyyy, int mm, int dd) throws Exception
	{
		if(AlreadyHavePerson(name))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date changedBirth = sdf.parse(yyyy+"/"+mm+"/"+dd);
			personService.updateBirth(name, changedBirth);
			System.out.println("Done!");
			printAllPersons();
			
		}else {
			System.out.println("查無此人");
		}
		
	}
	
	//根據姓名來刪除Person
	public void deletePersonByName(String name)
	{
		if(AlreadyHavePerson(name))
		{
			personService.deletePerson(name);
			System.out.println("Done!");
			printAllPersons();
			
		}else {
			System.out.println("查無此人");
		}
		
	}
	
	//查詢是否已經有Person
	public boolean AlreadyHavePerson(String name)
	{
		List<Person> people = personService.findAllPersons();
		boolean b1= people.stream().anyMatch(p->p.getName().equals(name));
		return b1;
	}
	
	
	
}











