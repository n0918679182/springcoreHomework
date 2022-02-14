package com.study.springgithub.homework5_0123.dao;

import java.util.List;

import com.study.springgithub.homework5_0123.entity.theOrderLog;
import com.study.springgithub.homework5_0123.exception.InsufficientAmount;
import com.study.springgithub.homework5_0123.exception.InsufficientQuantity;

public interface BookDao {
	Integer getPrice (Integer bid);
	Integer getStockAmount(Integer bid);
	Integer getWalletMoney(Integer wid);
	Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity; // 依據bid減去book的庫存
	Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount; // 依據wid減去wallet的金額
	
	//-----------------------Homework-----------------------------------------
	String getWname (Integer wid);
	String getBname (Integer bid);
	
	Integer setLog (String bname, String wname, Integer amount, Integer price);
	void updateLogForBuyBooks (Integer wid, Integer... bids);
	
	List<theOrderLog> queryOrderLog();
	
	
	
}
