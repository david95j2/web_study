package com.dasol.auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.jdbc.ConnectionProvider;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;

public class LoginService {
	MemberDAO memberDAO = new MemberDAO();

	public User login(String email, String password) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Member member = memberDAO.selectByEmail(conn, email);

			if (member == null) {
				throw new LoginFailException();
			}

			if (!member.matchPassword(password)) {
				throw new LoginFailException();
			}
			
			return new User(member.getEmail(), member.isRegisterCheck());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
