package com.dasol.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// jdk 6 above
public class BoardDAO {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet set = null;
		String url = "jdbc:mysql://125.181.79.156:3306/notice?useSSL=false";
		String id = "root";
		String pw = "root";
		
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println(conn);
			
			stmt = conn.createStatement();
			
			String sql = "select * from board";
			
			set = stmt.executeQuery(sql);
			
			int row = 0;
			while(set.next()) {
				int num = set.getInt("NUM");
				String name = set.getString("USERNAME");
				String title = set.getString("TITLE");
				String memo = set.getString("MEMO");
				String date = set.getString("TIME");
				int hit = set.getInt("HIT");
				int ref = set.getInt("REF");
				++row;
				System.out.println(num + " " + name + " " + title + " " + memo + " " + date + " " + hit + " " + ref);
			}
			System.out.println("tot row : " + row);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
