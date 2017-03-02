package com.dasol.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.dasol.jdbc.JdbcUtil;
import com.dasol.member.model.Member;

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
		return new Member(rs.getString("email"), 
				rs.getString("password"), 
				new Date(rs.getTimestamp("regdate").getTime()), 
				rs.getString("profile_image"));
	}
	
	public void insertData(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement
				("insert into members values(?, ?, ?, ?)")) {
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setTimestamp(3, new Timestamp(member.getRegdate().getTime()));
			pstmt.setString(4, member.getProfile_image());
			pstmt.executeUpdate();
		}
	}
}
