package springgithub.homework5_0123;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.springgithub.homework5_0123.controller.BookController;
import com.study.springgithub.homework5_0123.dao.BookDao;
import com.study.springgithub.homework5_0123.dao.BookDaoImpl;

public class TestBook {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("tx.xml");
		BookController bookController = ctx.getBean(BookController.class);
		System.out.println(bookController);
		// case 1 - 買一本
//		Integer wid=1;
//		Integer bid=1;
//		bookController.buyBook(wid, bid);
		
		// case 2 - 買多本
		Integer wid = 1;
		bookController.buyBooks(wid, 1,2,1);
		// 印出購買紀錄
		bookController.printLogs();
		
	}
}
