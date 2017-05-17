package com.test.utils;

import java.text.SimpleDateFormat;

public class Util {
	public static String getCurrentTime() {
		SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	    long currentTime = System.currentTimeMillis();
	    return formatter.format(currentTime) + ": ";
	}
}
