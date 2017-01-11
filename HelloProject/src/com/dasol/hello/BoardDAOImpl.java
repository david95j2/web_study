package com.dasol.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardDAOImpl implements BoardDAO {
	
	private String url = "jdbc:mysql://125.181.79.156:3306/notice?useSSL=false";
	private String id = "root";
	private String pw = "root";
	
	@Override
	public void insertBoard(String bid, String btitle, String bmemo) {
		try ( Connection conn = DriverManager.getConnection(url, id, pw); 
				PreparedStatement pstmt 
				= conn.prepareStatement("insert into board value(null, ?, ?, ?, now(), 0)"); ){
			
			pstmt.setString(1, bid);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bmemo);

			pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteBoard(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO selectData(int boardNum) {
		BoardVO vo = null;
		try ( Connection conn = DriverManager.getConnection(url, id, pw); 
				Statement stmt = conn.createStatement(); ){
			String sql = "update board set HIT = HIT + 1 where num = " + boardNum;
			stmt.execute(sql);
			sql = "select * from board where num =" + boardNum;
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				int num = rs.getInt("NUM");
				String username = rs.getString("USERNAME");
				String title = rs.getString("TITLE");
				String memo = rs.getString("MEMO");
				Date time = rs.getTimestamp("TIME");
				int hit = rs.getInt("HIT");
				
				vo = new BoardVO(num, username, title, memo, time, hit);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public List<BoardVO> selectAllData(int pageNum) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try ( Connection conn = DriverManager.getConnection(url, id, pw); 
				PreparedStatement pstmt 
				= conn.prepareStatement("select * from board limit 10 offset " + pageNum ); ){

			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				int num = rs.getInt("NUM");
				String username = rs.getString("USERNAME");
				String title = rs.getString("TITLE");
				String memo = rs.getString("MEMO");
				Date time = rs.getTimestamp("TIME");
				int hit = rs.getInt("HIT");
				
				boardList.add(new BoardVO(num, username, title, memo, time, hit));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

}
