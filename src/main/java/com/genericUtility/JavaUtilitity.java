package com.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class JavaUtilitity {
public int random() {
	Random ran = new Random();
	int random=ran.nextInt(1000);
	return random;
}
public String getSysDate() {
	Date dt = new Date();
	String date=dt.toString();
	return date;
}
public String formatSystemDate() {
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	Date dt=new Date();
	String getDateandTime = dateFormat.format(dt);
	getDateandTime.replace(":","-");
	return getDateandTime;
}
}
