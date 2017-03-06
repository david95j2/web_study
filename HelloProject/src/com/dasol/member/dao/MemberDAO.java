package com.dasol.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.dasol.jdbc.JdbcUtil;
import com.dasol.member.model.Member;
import com.dasol.util.AES256Util;

public class MemberDAO {
	
	public Member selectByEmail(Connection conn, String email) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from members where email=?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			Member member = null;
			if(rs.next()) {
				member = transeferResultsetToMember(rs);
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Member transeferResultsetToMember(ResultSet rs) throws SQLException {
		return new Member(rs.getInt("member_id"), 
				rs.getString("email"), 
				rs.getString("password"), 
				rs.getString("nickname"), 
				new Date(rs.getTimestamp("regdate").getTime()), 
				rs.getString("profile_image"), 
				rs.getString("register_code"), 
				rs.getBoolean("register_check"));
	}
	
	public void insertData(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement
				("insert into members(email, password, regdate, profile_image, register_check) "
						+ "values(?, ?, ?, ?, ?)")) {
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setTimestamp(3, new Timestamp(member.getRegdate().getTime()));
			pstmt.setString(4, member.getProfileImage());
			pstmt.setBoolean(5, member.isRegisterCheck());
			pstmt.executeUpdate();
		}
	}
	
}
