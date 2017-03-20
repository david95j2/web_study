package com.dasol.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dasol.board.model.ArticleContent;
import com.dasol.jdbc.JdbcUtil;

public class ArticleContentDAO {
	
	public ArticleContent insert(Connection conn, ArticleContent content) throws SQLException {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("insert into article_content "
					+ "(article_no, content) values (?,?)");
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int insertedCount = pstmt.executeUpdate();
			if(insertedCount > 0) {
				return content;
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
}
