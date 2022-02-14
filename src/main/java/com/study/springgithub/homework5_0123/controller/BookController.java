package com.study.springgithub.homework5_0123.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.study.springgithub.homework5_0123.entity.theOrderLog;
import com.study.springgithub.homework5_0123.exception.InsufficientAmount;
import com.study.springgithub.homework5_0123.exception.InsufficientQuantity;
import com.study.springgithub.homework5_0123.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	public void buyBook(Integer wid, Integer bid) {
		try {
			bookService.buyOne(wid, bid);
			System.out.println("單筆buyBook OK!");
		} catch (InsufficientAmount e) {
			System.err.println("金額不足"+e);
		} catch (InsufficientQuantity e) {
			System.err.println("庫存不足"+e);
		}
	}
	public void buyBooks(Integer wid, Integer... bids) {
		try {
			bookService.buyMany(wid, bids);
			System.out.println("多筆buyBook OK!");
		} catch (InsufficientAmount e) {
			System.err.println("金額不足"+e);
		} catch (InsufficientQuantity e) {
			System.err.println("庫存不足"+e);
		}
	}
	// 印出購買紀錄
	public void printLogs() {
		List<theOrderLog> log=bookService.printLog();
		for(theOrderLog o:log) {
			System.out.println(o.getWname()+"\t在 "+
							   o.getCt()+" 買了 "+
							   o.getBname()+"\t書 "+
							   o.getAmount()+" 本 共 "+
							   o.getTotalprice()+" 元");
		}
		
	}
	
}
