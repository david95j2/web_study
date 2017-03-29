package com.dasol.board.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.board.dao.ArticleContentDAO;
import com.dasol.board.dao.ArticleDAO;
import com.dasol.board.model.Article;
import com.dasol.board.model.ArticleContent;
import com.dasol.jdbc.ConnectionProvider;

public class ReadArticleService {
	ArticleDAO articleDAO = new ArticleDAO();
	ArticleContentDAO articleContentDAO = new ArticleContentDAO();

	public ArticleData getArticle(int articleNo, boolean increadReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()) {

			Article article = articleDAO.selectByNo(conn, articleNo);

			if (article == null) {
				throw new ArticleNotFoundException();
			}

			ArticleContent content = articleContentDAO.selectById(conn, articleNo);

			if (content == null) {
				throw new ArticleContentNotFoundException();
			}

			if (increadReadCount) {
				articleDAO.increaseReadCount(conn, articleNo);
			}
			
			return new ArticleData(article, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
}
