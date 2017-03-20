package com.dasol.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.dasol.board.dao.ArticleContentDAO;
import com.dasol.board.dao.ArticleDAO;
import com.dasol.board.model.Article;
import com.dasol.board.model.ArticleContent;
import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;

public class WriteArticleService {
	ArticleDAO articleDAO = new ArticleDAO();
	ArticleContentDAO articleContentDAO = new ArticleContentDAO();
	
	public Integer write(WriteRequest writeRequest) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = toArticle(writeRequest);
			Article savedArticle = articleDAO.insert(conn, article);
			
			if (savedArticle == null) {
				throw new RuntimeException("fail to insert to article");
			}
			
			ArticleContent articleContent = new ArticleContent(savedArticle.getNumber(), writeRequest.getContent());
			ArticleContent savedArticleContent = articleContentDAO.insert(conn, articleContent);
			
			if(savedArticleContent == null) {
				throw new RuntimeException("fail to insert article_content");
			}
			
			conn.commit();
			
			return savedArticle.getNumber();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

	private Article toArticle(WriteRequest writeRequest) {
		Date now = new Date();
		return new Article(null, writeRequest.getTitle(), 0, 0, 0, now, now, writeRequest.getWriter());
	}
}
