package springgithub.homework3_0109;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.springgithub.homework3_0109.aop.catchLog;
import com.study.springgithub.homework3_0109.template.EmpDao;

public class TemplateTest1 {
	

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		EmpDao empDao = ctx.getBean("empDao", EmpDao.class);
		System.out.println(empDao.queryAll());
		
		//如何取得 eid=2 的員工姓名
//		List<Map<String, Object>> emps = empDao.queryAll();
//		String ename = emps.stream()
//						.filter(e-> (e.get("eid")+"").equals("2"))
//						.findFirst()
//						.get()
//						.get("ename")+"";
//		System.out.println(ename);
		
		/*
		作業
		在每次的查詢 queryAll() 都可以將 查詢時間的 Log 記錄下來
		+-------------+--------------------+
		| method_name |    log_timestamp   |
		+-------------+--------------------+
		|   queryAll  | 2022/1/10 13:50:43 |
		+-------------+--------------------+
		|   queryAll  | 2022/1/10 14:53:03 |
		+-------------+--------------------+
		|   queryAll  | 2022/1/10 15:20:16 |
		+-------------+--------------------+
		
		*/
		empDao.printTime();
		
		
		
		
		
		
	}

}
