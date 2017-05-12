package com.bigdata.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bigdata.chunks.util.CommonConstants;




public class LocationConnector {

	private static LocationConnector ref;
	Connection conn;
	
	String url = "jdbc:mysql://localhost/chunks";
	String user = "root";
	String password = "root";
	public static String driverClass = "com.mysql.jdbc.Driver";

	private LocationConnector() {

	}

	public static synchronized LocationConnector getLocationConnector() {
		if (ref == null) {
			ref = new LocationConnector();
			try {
				Class.forName(CommonConstants.KEY_JDBC_DRIVER);

				// Get a connection to the database
				ref.conn = DriverManager.getConnection(CommonConstants.KEY_JDBC_URL + CommonConstants.KEY_JDBC_SERVICE, CommonConstants.KEY_JDBC_USERNAME, CommonConstants.KEY_JDBC_PASSWORD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ref;

	}

	public Connection getConn() {
		return conn;
	}

	@SuppressWarnings("unchecked")
	public ArrayList fireQuery(String query, boolean executeUpdate) {
		ArrayList returnList = null;
		ArrayList rowList = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();

			// Execute the query
			if(executeUpdate){
				stmt.executeUpdate(query);
				//update, insert
			}else{
				rs = stmt.executeQuery(query);
				//select
				
				returnList = new ArrayList();

				ResultSetMetaData rsM = rs.getMetaData();
				while (rs.next()) {
					rowList = new ArrayList();
					for (int i = 1; i <= rsM.getColumnCount(); i++) {
						rowList.add(i - 1, rs.getString(i));
					}
					returnList.add(rowList);
				}

				if (rs != null) {
					rs.close();
				}
				
			}
			
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return returnList;
	}

}
