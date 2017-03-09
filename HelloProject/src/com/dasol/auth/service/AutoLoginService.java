package com.dasol.auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;
import com.dasol.util.TokenGenerator;

public class AutoLoginService {

	MemberDAO memberDAO = new MemberDAO();
	
	public User autoLogin(String token) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDAO.selectByToken(conn, token);
			
			if(member == null) {
				throw new LoginFailException();
			}
			
			token = TokenGenerator.getTokenKey(); // 토큰키 재생성해서
			memberDAO.updateToken(conn, member.getMemberId(), token); // 업데이트
			conn.commit();
			
			return new User(member.getEmail(), member.isRegisterCheck(), token);
			
		} catch (SQLException e) {
			JdbcUtil.close(conn);
			throw new RuntimeException(e);
		}
		
		
	}
}
