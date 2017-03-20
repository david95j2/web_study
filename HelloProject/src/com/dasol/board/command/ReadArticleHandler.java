package com.dasol.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.board.service.ArticleContentNotFoundException;
import com.dasol.board.service.ArticleData;
import com.dasol.board.service.ArticleNotFoundException;
import com.dasol.board.service.ReadArticleService;
import com.dasol.mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler {

	private ReadArticleService readArticleService = new ReadArticleService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String no = request.getParameter("no");
		int articleNo = Integer.parseInt(no);
		try {
			ArticleData articleData = readArticleService.getArticle(articleNo, true);
			request.setAttribute("articleData", articleData);
			return "/WEB-INF/board/readArticle.jsp";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			request.getServletContext().log("no article", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
