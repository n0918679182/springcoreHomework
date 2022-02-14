package com.study.springgithub.homework2_1226;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonSystem {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext8.xml");
	PersonController personController = ctx.getBean("personController",PersonController.class);
	
	private boolean stop;
	
	private void menu()
	{
		System.out.println("-----------------------------------------");
		System.out.println("1.建立Person資料");
		System.out.println("2.取得Person全部資料");
		//作業2: 完成3, 4, 5, 6, 7
		System.out.println("3.根據姓名取得 Person");
		System.out.println("4.取得今天生日的Person");
		System.out.println("5.取得某一歲數以上的Person");
		System.out.println("6.根據姓名來修改Person的生日");
		System.out.println("7.根據姓名來刪除Person");
		System.out.println("0.離開");
		System.out.println("-----------------------------------------");
		System.out.println("請選擇: ");
		
		try {
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if(choice <=7)
			{
				switch (choice) {
				case 1:
					createPerson();
					break;
					
				case 2:
					printPerson();
					break;
					
				case 3:
					getPersonByName();
					break;
					
				case 4:
					getPersonByBirthDate();
					break;
					
				case 5:
					getPersonLargeOfTheAge();
					break;
					
				case 6:
					changeAgeByName();
					break;
					
				case 7:
					deletePersonByName();
					break;
				
				case 0:
					System.out.println("感謝使用本系統 歡迎再度光臨!");
					stop = true;
					break;
				}
				System.out.println();
			}else {
				System.out.println("請輸入數字0~7");
			}
		} catch (InputMismatchException e) {
			System.out.println(e);
			System.out.println("請輸入數字0~7");
		}
			
		
	}
	
	//建立Person資料
	private void createPerson() {
		System.out.println("請輸入姓名 出生 年 月 日: ");
		//Ex: Jack 1999 4 5
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		int yyyy = sc.nextInt();
		int mm = sc.nextInt();
		int dd = sc.nextInt();
		personController.addPerson(name, yyyy, mm, dd);
	}
	//取得Person全部資料
	private void printPerson() {
		personController.printAllPersons();
	}
	//--------------------------------------------------------------------------------
	
	//根據姓名取得 Person
	private void getPersonByName()
	{
		System.out.println("請輸入姓名: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		personController.getPersonByName(name);
		
	}
	//取得今天生日的Person
	private void getPersonByBirthDate()
	{
		personController.PrintBirthdayGuy();
		
	}
	
	//取得某一歲數以上的Person
	private void getPersonLargeOfTheAge()
	{
		System.out.println("請輸入年齡: ");
		Scanner sc = new Scanner(System.in);
		int age  = sc.nextInt();
		personController.getTheAge(age);
		
	}
	
	//根據姓名來修改Person的生日
	private void changeAgeByName()
	{
		System.out.println("請輸入姓名與生日: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		int yyyy = sc.nextInt();
		int mm = sc.nextInt();
		int dd = sc.nextInt();
		try {
			personController.changeAgeByName(name, yyyy, mm, dd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根據姓名來刪除Person
	private void deletePersonByName()
	{
		System.out.println("請輸入姓名: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		
		personController.deletePersonByName(name);
		
		
	}
	
	
	
	//------------------------------------------------------------------
	public void start() {
		while (!stop)
		{
			menu();
		}
		
	}
	public static void main(String[] args)
	{
		new PersonSystem().start();
	}
	
	
}
