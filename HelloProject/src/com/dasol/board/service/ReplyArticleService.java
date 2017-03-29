package com.dasol.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dasol.board.dao.ArticleDAO;
import com.dasol.board.model.Article;
import com.dasol.board.model.ArticleReply;
import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;

public class ReplyArticleService {
	ArticleDAO articleDAO = new ArticleDAO();
	
	public ArticleReplyData insertReply(ArticleReply articleReply) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Article article = articleDAO.selectByNo(conn, articleReply.getArticleNo());
			
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			
			ArticleReply savedArticleReply = articleDAO.insertReply(conn, articleReply);
			
			List<ArticleReply> articleReplyList = articleDAO.getArticleReplyList(conn, article.getNumber());

			updateArticleReplyCnt(conn, article.getNumber(), articleReplyList.size());
			
			return new ArticleReplyData(savedArticleReply.getArticleNo(), 
					savedArticleReply.getNickname(), 
					savedArticleReply.getMemberId(), 
					savedArticleReply.getContent(),
					savedArticleReply.getRegdate(),
					articleReplyList.size());
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public int deleteReply(int articleNo, int replyNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			Article article = articleDAO.selectByNo(conn, articleNo);
			
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			
			articleDAO.deleteArticleReply(conn, replyNo);
			
			List<ArticleReply> articleReplyList = articleDAO.getArticleReplyList(conn, articleNo);
			updateArticleReplyCnt(conn, articleNo, articleReplyList.size());
			return articleReplyList.size();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
	
	}
	
	private void updateArticleReplyCnt(Connection conn, int no, int totReplyCnt) throws SQLException {
		articleDAO.updateReplyCnt(conn, no, totReplyCnt);
	}
}
