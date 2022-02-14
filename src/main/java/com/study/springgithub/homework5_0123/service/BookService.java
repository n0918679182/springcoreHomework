package com.study.springgithub.homework5_0123.service;

import java.util.List;

import com.study.springgithub.homework5_0123.entity.theOrderLog;
import com.study.springgithub.homework5_0123.exception.InsufficientAmount;
import com.study.springgithub.homework5_0123.exception.InsufficientQuantity;

public interface BookService {
	void buyOne(Integer wid, Integer bid) throws InsufficientAmount, InsufficientQuantity;
	void buyMany(Integer wid, Integer... bids) throws InsufficientAmount, InsufficientQuantity;
	List<theOrderLog> printLog();
	
}
