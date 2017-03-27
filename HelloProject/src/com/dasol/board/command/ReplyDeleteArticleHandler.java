package com.dasol.board.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dasol.board.service.ReplyArticleService;
import com.dasol.mvc.command.CommandHandler;

public class ReplyDeleteArticleHandler implements CommandHandler {

	ReplyArticleService replyArticleService = new ReplyArticleService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String reply_no = request.getParameter("reply_no");
		String article_no = request.getParameter("article_no");
		
		int replyNo = Integer.parseInt(reply_no);
		int articleNo = Integer.parseInt(article_no);
		
		int totReplyCnt = replyArticleService.deleteReply(articleNo, replyNo);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("totReplyCnt", totReplyCnt);
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out;
		out = response.getWriter();
		out.print(jsonObj.toString());
		
		return null;
	}
	
}
