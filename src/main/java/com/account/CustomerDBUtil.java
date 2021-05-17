package com.account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDBUtil {
	
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stat = null;
	private static ResultSet rs = null;
	
	public static boolean validate (String username, String password){
		
		try {
		
			con = DBConnect.getConnection();
			stat = con.createStatement();
			
			String sql = "select * from customer where username = '"+username+"' and password = '"+password+"'" ;
			
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				
				isSuccess = true;	
			} 
			
			else {
			
				isSuccess = false;
			}
			
		}
		catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return isSuccess;

	}
	
	
	public static List<Customer> getCustomer(String userName) {
		
		ArrayList<Customer> customer = new ArrayList<>();
		
		try {
			
			con = DBConnect.getConnection();
			stat = con.createStatement();
			String sql = "select * from customer where username='"+userName+"'";
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String username = rs.getString(5);
				String password = rs.getString(6);
				
				Customer cus = new Customer(id,name,email,phone,username,password);
				customer.add(cus);
			}	
		} 
		
		catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		return customer;	
	}
	
	
	public static boolean updateCustomer(String id, String name, String email, String phone , String username, String password) {
		
		try {
			
			con = DBConnect.getConnection();
			stat = con.createStatement();
			
			String sql = "update customer set name='"+name+"', email='"+email+"', phonenumber='"+phone+"', username='"+username+"', password='"+password+"'"
					+ " where id='"+id+"'";
			
			int rs = stat.executeUpdate(sql);
			
			if(rs > 0) {
				isSuccess = true;
				
			}
			
			else {
				isSuccess = false;
			
			}
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	
	public static List<Customer> getCustomerDetails(String Id) {
    	
    	int convertedID = Integer.parseInt(Id);
    	
    	ArrayList<Customer> cus = new ArrayList<>();
    	
    	try {
    		
    		con = DBConnect.getConnection();
    		stat = con.createStatement();
    		String sql = "select * from customer where id='"+convertedID+"'";
    		rs = stat.executeQuery(sql);
    		
    		while(rs.next()) {
    			int id = rs.getInt(1);
    			String name = rs.getString(2);
    			String email = rs.getString(3);
    			String phone = rs.getString(4);
    			String username = rs.getString(5);
    			String password = rs.getString(6);
    			
    			Customer c = new Customer(id,name,email,phone,username,password);
    			cus.add(c);
    		}
    		
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}	
    	return cus;	
    }
    
	public static boolean deleteCustomer(String Id) {
		
		int convertedID = Integer.parseInt(Id);
		
		try {
			con = DBConnect.getConnection();
			stat = con.createStatement();
			
			String sql = "delete from customer where id = '"+convertedID+"'";
			int rs = stat.executeUpdate(sql);
			
			if(rs > 0) {
				
				isSuccess = true;
				
			}
			
			else {
				isSuccess = false;
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		return isSuccess;
	}
	
}