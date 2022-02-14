package com.study.springgithub.homework5_0123.exception;

// 餘額不足
public class InsufficientAmount extends Exception {

	public InsufficientAmount(String message) {
		super(message);
	}

}
