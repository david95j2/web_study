package com.dasol.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dasol.board.model.Article;
import com.dasol.board.model.Writer;
import com.dasol.jdbc.JdbcUtil;

public class ArticleDAO {

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from article");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Article> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement
					("select article.*, members.profile_image"
						+ " from article inner join members"
							+ " on article.writer_id = members.member_id"
								+ " order by article_no desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Article> articleList = new ArrayList<>();
			while (rs.next()) {
				articleList.add(convertArticle(rs));
			}
			return articleList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Article convertArticle(ResultSet rs) throws SQLException {
		return new Article(rs.getInt("article_no"), 
				rs.getString("title"), 
				rs.getInt("reply_cnt"), 
				rs.getInt("like_cnt"), 
				rs.getInt("read_cnt"), 
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")), 
				new Writer(rs.getInt("writer_id"), 
						rs.getString("nickname"), 
						rs.getString("profile_image")));
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

}
