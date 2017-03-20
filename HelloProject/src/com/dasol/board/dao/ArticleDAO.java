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
					("select * from article order by article_no desc limit ?, ?");
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
	
	public Article insert(Connection conn, Article article) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into article "
					+ "(title, reply_cnt, like_cnt, read_cnt, regdate, moddate, writer_id, nickname, profile_image) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, article.getTitle());
			pstmt.setInt(2, article.getReplyCnt());
			pstmt.setInt(3, article.getLikeCnt());
			pstmt.setInt(4, article.getReadCnt());
			pstmt.setTimestamp(5, toTimestamp(article.getRegDate()));
			pstmt.setTimestamp(6, toTimestamp(article.getModDate()));
			pstmt.setInt(7, article.getWriter().getId());
			pstmt.setString(8, article.getWriter().getNickname());
			pstmt.setString(9, article.getWriter().getProfileImage());
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from article");
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Article(newNum, 
							article.getTitle(), 
							article.getReplyCnt(), 
							article.getLikeCnt(), 
							article.getReadCnt(), 
							article.getRegDate(), 
							article.getModDate(), 
							article.getWriter());
				}
			}
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public Article selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from article where article_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;
			if(rs.next()) {
				article = convertArticle(rs);
			}
			return article;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("update article set read_cnt = read_cnt + 1 "
				+ "where article_no=?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public void increaseLikeCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt 
				= conn.prepareStatement("update article set like_cnt = like_cnt + 1 "
				+ "where article_no=?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
}
