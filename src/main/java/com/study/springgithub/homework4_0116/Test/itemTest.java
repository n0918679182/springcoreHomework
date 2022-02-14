package com.study.springgithub.homework4_0116.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.springgithub.homework4_0116.template.ItemDao;

public class itemTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("homework-item.xml");
		ItemDao itemDao = ctx.getBean("itemDao", ItemDao.class);
		
		System.out.println("每一張發票有那些商品?");
		itemDao.queryProductOnEachInvoice();
		System.out.println("========================================");
		
		System.out.println("每一張發票有幾件商品?");
		itemDao.howManyItemsForEachInvoice();
		System.out.println("========================================");		
		
		System.out.println("每一張發票價值多少?");
		itemDao.valueOfEachInvoice();
		System.out.println("========================================");		
		
		System.out.println("每一樣商品各賣了多少?");
		itemDao.sellCountForEachProduct();
		System.out.println("========================================");		
	
		System.out.println("哪一件商品賣得錢最多?");
		itemDao.hotProduct();
		System.out.println("========================================");		
		
		System.out.println("哪一張發票價值最高?");
		itemDao.TheMostOfMoneyInInvoices();
	}

}
