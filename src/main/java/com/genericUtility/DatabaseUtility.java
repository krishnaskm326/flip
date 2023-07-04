package com.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	Connection con=null;
	public void connectToDb() throws Throwable
	{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con=DriverManager.getConnection(IpathConstants.DBURL,IpathConstants.DBUSERNAME,IpathConstants.DBPASSWORD);
	}
	public String executeQueryAndGetData(String query,String expdata,int columnIndex) throws Throwable {
		ResultSet result = con.createStatement().executeQuery(query);
		boolean flag = false;
		while(result.next()) {
			String data = result.getString(columnIndex);
			if(data.equalsIgnoreCase(expdata)) {
				flag=true;
				break;
			}
		}
		if(flag) {
			System.out.println("data is verified");
					
	return expdata;
	}
		else {
			System.out.println("data is not updated");
			return "";
		}
}
	/**
	 * 
	 * @throws Throwable
	 */
	public void closeDb() throws Throwable {
		con.close();
		System.out.println("database connection closed");
	}
	}
