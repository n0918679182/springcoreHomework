package com.study.springgithub.homework3_0109.aop;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.study.springgithub.homework2_1226.Person;
import com.study.springgithub.homework3_0109.entity.Timee;
import com.study.springgithub.homework3_0109.template.EmpDao;


@Component
@Aspect
@Order(1)
public class catchLog {
//	public static List<String> logTime = new ArrayList<String>();
//	public static String logTime;
	@Autowired
	private Gson gson;
	
	private static final Path PATH = Paths.get("src/main/java/com/study/springgithub/homework3_0109/sql/timee.json");

	@Pointcut(value = "execution(* com.study.springgithub.homework3_0109.template.EmpDao.queryAll(..))")
	public void pt() {}
	
	@Pointcut(value = "execution(* com.study.springgithub.homework3_0109.template.EmpDao.printTime(..))")
	public void pt2() {}
	
	@Before(value = "pt()")
	public void before() throws Exception, IOException  
	{ 
		List<Timee> logTime = new ArrayList<Timee>();
		Date theTime= new Date();
		SimpleDateFormat font = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		String st = font.format(theTime);
		
		Timee t1 = new Timee(st);
		addTimeToJson(t1);
		//printLogTime();
		
		
	}
	
	@After(value = "pt2()")
	public void printLogTime() throws Exception
	{	
		System.out.println("+-------------+---------------------+");
		System.out.println("| method_name |    log_timestamp    |");
		System.out.println("+=============+=====================+");
		List<Timee> theLog = timeQueryAll();
		String methodName = "queryAll";
		for(Timee t: theLog) {
			System.out.printf("|   %8s  | %19s |\n",methodName,t.getTheTime() );
			System.out.println("+-------------+---------------------+");
			//System.out.println(t.getTheTime());
		}
	}
	
	
	
	public List<Timee> timeQueryAll() throws Exception
	{
		String jsonStr = Files.readAllLines(PATH).stream().collect(Collectors.joining());
		Type type = new TypeToken<ArrayList<Timee>>() {}.getType();
		List<Timee> timee = gson.fromJson(jsonStr, type);
		
		return timee;
	}
	public void addTimeToJson(Timee timee) throws Exception
	{
		List<Timee> theLog = timeQueryAll();
		theLog.add(timee);
		String newJsonString = gson.toJson(theLog);
		Files.write(PATH, newJsonString.getBytes("UTF-8"));
	}
	
	

}
