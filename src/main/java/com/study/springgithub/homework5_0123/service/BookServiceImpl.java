package com.study.springgithub.homework5_0123.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.springgithub.homework5_0123.dao.BookDao;
import com.study.springgithub.homework5_0123.entity.theOrderLog;
import com.study.springgithub.homework5_0123.exception.InsufficientAmount;
import com.study.springgithub.homework5_0123.exception.InsufficientQuantity;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bookDao;
	
	@Transactional(propagation = Propagation.REQUIRED, 
			rollbackFor = {InsufficientAmount.class, InsufficientQuantity.class},
			noRollbackFor = {ArithmeticException.class})
	// getConnection(), setAutoCommit(false), commit() 這三項交給資料庫控管
	@Override
	public void buyOne(Integer wid, Integer bid) throws InsufficientAmount, InsufficientQuantity{
		// 減去一本庫存
		bookDao.updateStock(bid, 1);
		// 取得書籍價格
		Integer price = bookDao.getPrice(bid);
		// 減去錢包裡的金額
		bookDao.updateWallet(wid, price);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, 
			rollbackFor = {InsufficientAmount.class, InsufficientQuantity.class},
			noRollbackFor = {ArithmeticException.class})
	// getConnection(), setAutoCommit(false), commit() 這三項交給資料庫控管
	@Override
	public void buyMany(Integer wid, Integer... bids) throws InsufficientAmount, InsufficientQuantity{
		// 重複執行buyOne
		for(Integer bid : bids) {
			buyOne(wid, bid);
		}
		bookDao.updateLogForBuyBooks(wid, bids);
		
		
	}
	
	@Override
	public List<theOrderLog> printLog() {
		List<theOrderLog> log=bookDao.queryOrderLog();
		
		return log;
		
	}
	


}
