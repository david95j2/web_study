package com.dasol.hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BoardDAOImpl implements BoardDAO {
	
	private String url = "jdbc:mysql://125.181.79.156:3306/notice?useSSL=false";
	private String id = "root";
	private String pw = "root";
	
	@Override
	public void insertBoard(BoardVO boardVO) {
		try ( Connection conn = DriverManager.getConnection(url, id, pw); 
				PreparedStatement pstmt 
				= conn.prepareStatement("insert into board value(null, ?, ?, ?, now(), 0, 0, 0, 0)"); ){
			
			pstmt.setString(1, boardVO.getUsername());
			pstmt.setString(2, boardVO.getTitle());
			pstmt.setString(3, boardVO.getMemo());

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
	public List<BoardVO> selectData(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> selectAllData() {
		// TODO Auto-generated method stub
		return null;
	}

}
