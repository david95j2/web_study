package com.dasol.member.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.jdbc.ConnectionProvider;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;
import com.dasol.util.SendEmail;

public class FindPasswordService {
	MemberDAO memberDAO = new MemberDAO();
	
	public boolean find(String email) {
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			Member member = memberDAO.selectByEmail(conn, email);
			
			if (member == null) {
				throw new MemberNotFoundException();
			}
			
			MailInfo mailInfo = new MailInfo();
			mailInfo.setEmail(member.getEmail());
			mailInfo.setPasswordContent();
			SendEmail.send(mailInfo);
			
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
