package com.dasol.board.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dasol.board.model.ArticleLike;
import com.dasol.board.service.ArticleLikeData;
import com.dasol.board.service.ArticleLikeUpdateService;
import com.dasol.mvc.command.CommandHandler;

public class ArticleLikeUpdateHandler implements CommandHandler {
	
	private ArticleLikeUpdateService service = new ArticleLikeUpdateService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("likeHandler");
		
		String memberId = request.getParameter("member_id");
		String nickname = request.getParameter("nickname");
		String articleNo = request.getParameter("article_no");
		
		int id = Integer.parseInt(memberId);
		int no = Integer.parseInt(articleNo);
		
		ArticleLike articleLike = new ArticleLike(null, id, nickname, no);
		
		ArticleLikeData articleLikeData = service.likeUpdate(articleLike);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("like_no", articleLikeData.getLikeNo());
		jsonObj.put("member_id", articleLikeData.getMemberId());
		jsonObj.put("nickname", articleLikeData.getNickname());
		jsonObj.put("totLikeCnt", articleLikeData.getTotLikeCnt());
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out;
		out = response.getWriter();
		out.print(jsonObj.toString());
		
		return null;
	}
	
}
