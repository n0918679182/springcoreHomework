package com.study.springgithub.homework5_0123.dao;

import java.util.List;
import java.util.Map;

import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.study.springgithub.homework5_0123.entity.theOrderLog;
import com.study.springgithub.homework5_0123.exception.InsufficientAmount;
import com.study.springgithub.homework5_0123.exception.InsufficientQuantity;

@Repository
public class BookDaoImpl implements BookDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer getPrice(Integer bid) {
		String sql = "select price from book where bid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getStockAmount(Integer bid) {
		String sql = "select amount from stock where bid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getWalletMoney(Integer wid) {
		String sql = "select money from wallet where wid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, wid);
	}

	@Override
	public Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity {
		// 確認該書的最新庫存量
		Integer new_amount=getStockAmount(bid);
		if(new_amount<=0) {
			throw new InsufficientQuantity(String.
					format("此書號:%d 目前沒有庫存, 目前數量:%d", bid,new_amount));
		}else if(new_amount<amount) {
			throw new InsufficientQuantity(String.
					format("此書號:%d 目前庫存不足, 目前數量:%d, 欲購買數量:%d", bid,new_amount, amount));
		}
		// 修改庫存
		String sql ="update stock set amount=amount-? where bid=?";
		return jdbcTemplate.update(sql, amount, bid);
	}

	@Override
	public Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount{
		// 先確認錢包裡的餘額
		Integer new_money=getWalletMoney(wid);
		if(new_money<=0) {
			throw new InsufficientAmount(String.
					format("錢包號:%d 目前沒有餘額, 目前餘額:$%d", wid,new_money));
		}else if(new_money<money) {
			throw new InsufficientAmount(String.
					format("錢包號:%d 目前餘額不足, 目前餘額:$%d, 欲扣款金額:$%d", wid,new_money, money));
		}
		// 修改餘額
		String sql ="update wallet set money=money-? where wid=?";
		return jdbcTemplate.update(sql, money, wid);
	}
	//-----------------------Homework-----------------------------------------
	//取得錢包名稱
	@Override
	public String getWname(Integer wid) {
		String sql = "select wname from wallet where wid=?";
		return jdbcTemplate.queryForObject(sql, String.class, wid);
	}
	
	//取得書籍名稱
	@Override
	public String getBname(Integer bid) {
		String sql = "select bname from book where bid=?";
		return jdbcTemplate.queryForObject(sql, String.class, bid);
	}
	
	//寫入資料表order_log
	@Override
	public Integer setLog(String bname, String wname, Integer amount, Integer price) {
		String sql="insert into order_log(bname, wname,amount,totalprice) values(?,?,?,?)";
		int rowcount =jdbcTemplate.update(sql, bname, wname, amount, price);//回傳一個異動筆數
		return rowcount;
	}
	
	//蒐集購買資訊並寫入資料表
	@Override
	public void updateLogForBuyBooks(Integer wid, Integer... bids) {
		Integer Amount1 =0;
		Integer Amount2 =0;
		String WName = getWname(wid);
		Integer Price1 =0;
		Integer Price2 =0;
				
		for(Integer bid : bids) {
			if(bid==1)
				Amount1++;
			if(bid==2)
				Amount2++;
		}
		String BName;
		Price1 =getPrice(1)*Amount1;
		Price2 =getPrice(2)*Amount2;
		if(Amount1>0) {
			BName=getBname(1);
			setLog(BName, WName, Amount1, Price1);
		}
		if (Amount2>0) {
			BName=getBname(2);
			setLog(BName, WName, Amount2, Price2);
		}
	}
	// 查詢orderlog資料表並轉成List
	@Override
	public List<theOrderLog> queryOrderLog() {
		String sql = "select oid ,bname ,wname ,amount ,totalprice ,ct from order_log";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(theOrderLog.class));
	}
	
	
	

}
