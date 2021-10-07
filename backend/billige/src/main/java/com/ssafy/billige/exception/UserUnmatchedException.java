package com.ssafy.billige.exception;

public class UserUnmatchedException extends RuntimeException {
	public UserUnmatchedException(String need) {
		super("This Notice is for " + need);
	}
}
