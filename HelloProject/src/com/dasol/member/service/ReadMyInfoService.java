package com.dasol.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.jdbc.ConnectionProvider;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;

public class ReadMyInfoService {
	MemberDAO memberDAO = new MemberDAO();

	public MyInfo getMyInfo(int memberId) {

		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDAO.selectByMemberId(conn, memberId);

			if (member == null) {
				throw new MemberNotFoundException();
			}

			return new MyInfo(member.getNickname(), member.getProfileImage());

		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
}
