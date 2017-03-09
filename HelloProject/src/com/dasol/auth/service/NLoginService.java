package com.dasol.auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;

public class NLoginService {
	MemberDAO memberDAO = new MemberDAO();

	public User login(LoginRequest loginRequest) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDAO.selectByEmail(conn, loginRequest.getEmail());
			
			if (member == null) { // 널이라면 신규회원
				memberDAO.insertData(conn, 
						new Member(loginRequest.getEmail(), 
								null, 
								loginRequest.getNickname(),
								new Date(), 
								loginRequest.getProfileImage(), 
								null, 
								false, 
								null, 
								loginRequest.getAccessToken()));
			} else {
				// 기존 회원이라면 추가 정보만 업뎃
				memberDAO.updateDataWithNaverInfo(conn, loginRequest);
				System.out.println("acToken " + loginRequest.getAccessToken());
			}
			
			member = memberDAO.selectByEmail(conn, loginRequest.getEmail());
			conn.commit();

			return new User(member.getEmail(), member.isRegisterCheck(), member.getRememberToken());
			
		} catch (SQLException e) {
			JdbcUtil.close(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
}
