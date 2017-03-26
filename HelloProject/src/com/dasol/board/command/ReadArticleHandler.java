package com.dasol.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.auth.service.User;
import com.dasol.board.service.ArticleContentNotFoundException;
import com.dasol.board.service.ArticleData;
import com.dasol.board.service.ArticleLikeUpdateService;
import com.dasol.board.service.ArticleNotFoundException;
import com.dasol.board.service.ReadArticleService;
import com.dasol.mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler {

	private ReadArticleService readArticleService = new ReadArticleService();
	private ArticleLikeUpdateService articleLikeUpdateService = new ArticleLikeUpdateService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User authUser = (User)request.getSession().getAttribute("authUser");
		int memberId = 0;
		
		if(authUser != null)
			memberId = authUser.getId();
		
		String no = request.getParameter("no");
		int articleNo = Integer.parseInt(no);
		try {
			ArticleData articleData = readArticleService.getArticle(articleNo, true);
			request.setAttribute("articleData", articleData);
			
			boolean isLikeIt = articleLikeUpdateService.isLikeIt(articleNo, memberId);
			request.setAttribute("isLikeIt", isLikeIt);
			
			return "/WEB-INF/board/readArticle.jsp";
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			request.getServletContext().log("no article", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
