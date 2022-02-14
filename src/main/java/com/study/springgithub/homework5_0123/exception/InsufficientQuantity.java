package com.study.springgithub.homework5_0123.exception;

// 庫存書籍存量不足
public class InsufficientQuantity extends Exception{

	public InsufficientQuantity(String message) {
		super(message);
	}

}
