package com.study.springgithub.homework1_1219;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext5.xml");
		
		Student john = ctx.getBean("s1", Student.class);
		Student mary = ctx.getBean("s2", Student.class);
		
		Teacher[] teachers = {ctx.getBean("t1", Teacher.class),ctx.getBean("t2", Teacher.class)};
		Set<Teacher> teachers2=new LinkedHashSet<>();
		
		//--------------------------------------------------------------------------------------------------------------------
		
		//老師1的課程id
		List<Integer> t1ClazzId = new ArrayList<>();
		t1ClazzId = ctx.getBean("t1", Teacher.class).getClazzs().stream().map(Clazz::getId).collect(Collectors.toList());
		
		//老師2的課程id
		List<Integer> t2ClazzId = new ArrayList<>();
		t2ClazzId = ctx.getBean("t2", Teacher.class).getClazzs().stream().map(Clazz::getId).collect(Collectors.toList());
		
		//老師1和mary共同課程的id
		List<Integer> t1s = new ArrayList<>();
		t1s =mary.getClazzs().stream().map(Clazz::getId)
					     .filter(t1ClazzId::contains)
					     .collect(Collectors.toList());

		//老師2和mary共同課程的id
		List<Integer> t2s = new ArrayList<>();
		t2s =mary.getClazzs().stream().map(Clazz::getId)
					     .filter(t2ClazzId::contains)
					     .collect(Collectors.toList());
		
		
		boolean b1 = t1ClazzId.containsAll(t1s);
		boolean b2 = t2ClazzId.containsAll(t2s);
		
		if(!t1s.isEmpty()) {
			if(b1)
			  teachers2.add(ctx.getBean("t1", Teacher.class));
		}
		
		if(!t2s.isEmpty()) {
			if(b2)
			  teachers2.add(ctx.getBean("t2", Teacher.class));
		}
		//------------------------------------------------------------------------------------------------------------------------
		System.out.println(mary.getName()+"的老師:"+teachers2.stream().map(Teacher::getName).collect(Collectors.toSet()));
		
		
		
	}
}