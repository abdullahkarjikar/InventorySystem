package com.cg.mps.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.mps.exception.MpsException;

public class JdbcUtility {

	static Connection connection = null;

	public static Connection getConnection() throws MpsException {

		File file = null;
		FileInputStream inputStream = null;

		file = new File("resources/jdbc.properties");
		try {
			inputStream = new FileInputStream(file);

			Properties properties = new Properties();
			properties.load(inputStream);

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				throw new MpsException("driver wasn't loaded..");
			}
			try {
				connection = DriverManager.getConnection(url, username, password);
				//System.out.println("connnected..");
			} catch (SQLException e) {
				throw new MpsException("connection issues");
			}

		} catch (FileNotFoundException e) {
			throw new MpsException("no file present with the given name");
		} catch (IOException e) {
			throw new MpsException("unable to load the file");
		}

		return connection;

	}
}
