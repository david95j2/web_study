package com.dasol.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;

public class ModifyMyInfoService {
	MemberDAO memberDAO = new MemberDAO();

	public MyInfo modify(MyInfo myInfo, int memberId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDAO.selectByMemberId(conn, memberId);

			if (member == null) {
				new MemberNotFoundException();
			}

			if(myInfo.getProfileImage() == null && member.getProfileImage() != null) {
				myInfo.setProfileImage(member.getProfileImage());
			}
			
			memberDAO.updateDataByModifyMyInfo(conn, myInfo.getProfileImage(), myInfo.getNickname(), memberId);
			conn.commit();
			
			return myInfo;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
