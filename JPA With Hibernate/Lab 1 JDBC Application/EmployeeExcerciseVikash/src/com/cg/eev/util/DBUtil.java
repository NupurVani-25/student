package com.cg.eev.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.cg.eev.exception.EmployeeExcpetion;

public class DBUtil {

	static Connection con;
	static Properties prop;

	static {
		try {
			prop = new Properties();
			File file = new File("jdbc.properties");
			FileInputStream fin = new FileInputStream(file);
			prop.load(fin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnect() throws EmployeeExcpetion {
		try {
			String driver = prop.getProperty("driver");
			String user = prop.getProperty("username");
			String pass = prop.getProperty("password");
			String url = prop.getProperty("url");
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmployeeExcpetion("technical problem");
		}
		return con;
	}

}
