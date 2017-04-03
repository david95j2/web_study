package com.dasol.board.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dasol.board.service.ReplyData;
import com.dasol.board.service.ReplyService;
import com.dasol.mvc.command.CommandHandler;

public class DeleteReplyHandler implements CommandHandler {

	ReplyService replyArticleService = new ReplyService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String reply_no = request.getParameter("reply_no");
		String article_no = request.getParameter("article_no");
		
		int replyNo = Integer.parseInt(reply_no);
		int articleNo = Integer.parseInt(article_no);
		
		ReplyData replyData = replyArticleService.deleteReply(articleNo, replyNo);
		
		JSONObject jsonObj = new JSONObject();
		//jsonObj.put("replyList", replyData.getReplyList());
		jsonObj.put("totReplyCnt", replyData.getTotCnt());
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out;
		out = response.getWriter();
		out.print(jsonObj.toString());
		
		return null;
	}
	
}
