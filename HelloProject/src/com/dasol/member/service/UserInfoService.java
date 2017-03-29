package com.dasol.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dasol.auth.service.User;
import com.dasol.board.dao.ArticleDAO;
import com.dasol.board.model.Article;
import com.dasol.board.model.ArticleLike;
import com.dasol.board.model.ArticleReply;
import com.dasol.jdbc.ConnectionProvider;
import com.dasol.jdbc.JdbcUtil;
import com.dasol.member.dao.MemberDAO;
import com.dasol.member.model.Member;
import com.dasol.member.model.UserInfo;

public class UserInfoService {
	private MemberDAO memberDAO = new MemberDAO();
	private ArticleDAO articleDAO = new ArticleDAO();

	public UserInfo selectUser(int userId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();

			Member member = memberDAO.selectByMemberId(conn, userId);

			if (member == null) {
				throw new MemberNotFoundException();
			}

			List<Article> articleList = articleDAO.selectListById(conn, userId);

			List<ArticleLike> articleLikeList = articleDAO.selectLikeListById(conn, userId);

			List<ArticleReply> articleReplyList = articleDAO.selectReplyListById(conn, userId);

			return new UserInfo(
					new User(member.getMemberId(), member.getEmail(), member.getNickname(), member.getProfileImage()),
					articleList, articleLikeList, articleReplyList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

	}
}
