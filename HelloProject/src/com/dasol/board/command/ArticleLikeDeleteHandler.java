package com.dasol.board.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dasol.board.service.ArticleLikeUpdateService;
import com.dasol.mvc.command.CommandHandler;

public class ArticleLikeDeleteHandler implements CommandHandler {
	
	private ArticleLikeUpdateService service = new ArticleLikeUpdateService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("likedelHandler");
		
		String likeNoVal = request.getParameter("like_no");
		String articleNoVal = request.getParameter("article_no");
		
		System.out.println("likeNoVal="+likeNoVal);
		
		int likeNo = Integer.parseInt(likeNoVal);
		int articleNo = Integer.parseInt(articleNoVal);
		
		System.out.println(likeNo);
		
		int totLikeCnt = service.likeDelete(likeNo, articleNo);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totLikeCnt", totLikeCnt);
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out;
		out = response.getWriter();
		out.print(jsonObj.toString());
		
		return null;
	}
	
}
