package com.dasol.board.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.dasol.board.dao.ArticleContentDAO;
import com.dasol.board.dao.ArticleDAO;
import com.dasol.board.model.Article;
import com.dasol.board.model.ArticleContent;
import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;

public class ModifyArticleService {
	ArticleContentDAO articleContentDAO = new ArticleContentDAO();
	ArticleDAO articleDAO = new ArticleDAO();

	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Article article = articleDAO.selectById(conn, modReq.getArticleNumber());
			if (article == null) {
				throw new ArticleNotFoundException();
			}

			ArticleContent content = articleContentDAO.selectById(conn, modReq.getArticleNumber());
			if (content == null) {
				throw new ArticleContentNotFoundException();
			}

			if (!PermissionChecker.canDo(modReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}

			articleDAO.update(conn, article.getNumber(), modReq.getTitle());
			articleContentDAO.update(conn, article.getNumber(), modReq.getContent());
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
