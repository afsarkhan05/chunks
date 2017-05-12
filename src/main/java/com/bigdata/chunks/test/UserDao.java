package com.bigdata.chunks.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDao {

	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;

	//getting connection with Remote DB

	
	public Connection getSqlConnection(){
		
		try
		{

			Class.forName("com.mysql.jdbc.Driver");

			//set this to a MySQL DB you have on your machine
			String url = "jdbc:mysql://10.192.18.156:3306/realmedia";

			// get the connection from the DriverManager
			con = DriverManager.getConnection(url,"rahulp","rahulp");
			
			String Sql="desc bug_master ";
			
			System.out.println(Sql);
			pst=con.prepareStatement(Sql);
			rs=pst.executeQuery();
			
			
			while(rs.next()){
				
				System.out.println("");
				System.out.print(" "+rs.getString(1));
				System.out.print(" "+rs.getString(2));
				System.out.print(" "+rs.getString(3));
				System.out.print(" "+rs.getString(4));
								
			}
			
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return con;		
	}
	
	public static void main(String []args){
		
		UserDao dao=new UserDao();
		dao.getSqlConnection();
	}
}