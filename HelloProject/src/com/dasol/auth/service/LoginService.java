package com.dasol.auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;
import com.dasol.util.TokenGenerator;

public class LoginService {
	MemberDAO memberDAO = new MemberDAO();

	public User login(String email, String password, String remember) {
		Connection conn = null;
		String token = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDAO.selectByEmail(conn, email);

			if (member == null) {
				throw new LoginFailException();
			}

			if (!member.matchPassword(password)) {
				throw new LoginFailException();
			}

			if (remember != null) { // 체크가 없었던 유저가 로그인 시
				token = TokenGenerator.getTokenKey(); // 토큰키를 생성해서 dao에 넣어준다.
				memberDAO.updateToken(conn, member.getMemberId(), token);
			}
			
			conn.commit();
			return new User(member.getEmail(), member.isRegisterCheck(), token);
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}

	}
}
