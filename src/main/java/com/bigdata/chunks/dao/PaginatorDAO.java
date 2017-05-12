package com.bigdata.chunks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bigdata.chunks.bean.AuthMaster;
import com.bigdata.chunks.bean.DisplayMaster;

 
public class PaginatorDAO {
    Connection connection;
    Statement stmt;
    private int noOfRecords;
 
    public PaginatorDAO() { }
 
 
    public List<AuthMaster> viewAllUsers(
                int offset,
                int noOfRecords)
    {
    	
    	List<AuthMaster> list = new ArrayList<AuthMaster>();
    	List list1 = new ArrayList();
    	/*Session session = HibernateUtil.getSessionFactory().openSession();
        String query = "select SQL_CALC_FOUND_ROWS * from employee limit "
                 + offset + ", " + noOfRecords;
    	String queryStr = " select * from auth_master am limit "+ offset +", " + noOfRecords; 
    	
    	try{
	    	Query query = session.createQuery(queryStr);
	    	
	    	list1 = query.list();
	    	
	    	for (Object object : list1) {
				
			}
	    	
	    	this.noOfRecords = 5;
	    	
    	}catch(Exception e){
    		
    	}finally{
    		session.close();
    	}*/
    	
    	try {
    		String query = "select SQL_CALC_FOUND_ROWS * from auth_master limit "
                + offset + ", " + noOfRecords;
			connection = getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			AuthMaster authMaster = null;
			while (rs.next()) {
				authMaster = new AuthMaster(rs.getString("userName"), null, rs.getString("firstName"),
						null, rs.getString("email"), rs.getString("mobile"), null,
						null, null, null,
						null, null, null, null,
						null, null);

				list.add(authMaster);
			}
			rs.close();

			rs = stmt.executeQuery("SELECT FOUND_ROWS()");
			if(rs.next())
				this.noOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally
		{
			try {
				if(stmt != null)
					stmt.close();
				if(connection != null)
					connection.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
     
    }
    
    public List<DisplayMaster> viewAllArticle(
            int offset,
            int noOfRecords)
{
	
	List<DisplayMaster> list = new ArrayList<DisplayMaster>();
	//List list1 = new ArrayList();
	
	try {
		String query = "select SQL_CALC_FOUND_ROWS * from article_master limit "
            + offset + ", " + noOfRecords;
		connection = getConnection();
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		DisplayMaster displayMaster = null;
		while (rs.next()) {
			/*displayMaster = new DisplayMaster(rs.getString("userName"), rs.getString("firstName"),
					null, rs.getString("email"), rs.getString("mobile"), null,
					null, null, null,
					null, null, null, null,
					null, null);

			list.add(authMaster);*/
		}
		rs.close();

		rs = stmt.executeQuery("SELECT FOUND_ROWS()");
		if(rs.next())
			this.noOfRecords = rs.getInt(1);
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}finally
	{
		try {
			if(stmt != null)
				stmt.close();
			if(connection != null)
				connection.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return list;
 
}
    
	@SuppressWarnings("unchecked")
	public ArrayList fireQuery(String query, boolean executeUpdate, String[] items) {
		ArrayList returnList = null;
		ArrayList rowList = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			connection = getConnection();
			
			stmt = connection.createStatement();

			// Execute the query
			if(executeUpdate){
				stmt.executeUpdate(query);
				//update, insert
			}else{
				rs = stmt.executeQuery(query);
				//select
				
				returnList = new ArrayList();

				java.sql.ResultSetMetaData rsM = rs.getMetaData();
				while (rs.next()) {
					rowList = new ArrayList();
					
					//System.out.println("rsM Coutn "+ rsM.getColumnCount());
					if(items !=null){
						String item = null;
						for (int i = 0; i < items.length; i++) {
							try{
							//System.out.println("item is #### "+items[i]);
							item = rs.getString(items[i]);
							//System.out.println(item + " "+ items[i]);
							rowList.add(i , item);
							}catch(Exception e){
								
							}
						}
					}else{
						for (int i = 1; i <= rsM.getColumnCount(); i++) {
							rowList.add(i - 1, rs.getString(i));
						}
					}
					returnList.add(rowList);
				}

				if (rs != null) {
					rs.close();
				}
				
				rs = stmt.executeQuery("SELECT FOUND_ROWS()");
				if(rs.next())
					this.noOfRecords = rs.getInt(1);
				
			}
			
			if (stmt != null) {
				stmt.close();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
				if(connection != null)
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return returnList;
	}
    
    
 
    public int getNoOfRecords() {
        return noOfRecords;
    }
    
    
	
		String url = "jdbc:mysql://localhost/chunks";
		String user = "root";
		String password = "root";
		public static String driverClass = "com.mysql.jdbc.Driver"; 
		
		//private constructor
		static {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
		

		
		public Connection getConnection() throws SQLException,
		ClassNotFoundException {
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
		}
}
