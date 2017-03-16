package com.dasol.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;

public class DeleteMyAccountService {
	MemberDAO memberDAO = new MemberDAO();

	public void deleteAccount(int memberId, String password) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Member member = memberDAO.selectByMemberId(conn, memberId);

			if (member == null) {
				JdbcUtil.rollback(conn);
				throw new MemberNotFoundException();
			}

			if (!member.matchPassword(password)) {
				throw new InvalidPasswordException();
			}

			memberDAO.deleteMyAccount(conn, member.getMemberId());
			conn.commit();

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
