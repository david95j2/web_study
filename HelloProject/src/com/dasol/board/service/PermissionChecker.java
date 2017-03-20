package com.dasol.board.service;

import com.dasol.board.model.Article;

public class PermissionChecker {
	public static boolean canDo(int memberId, Article article) {
		return article.getWriter().getId() == memberId;
	}
}
