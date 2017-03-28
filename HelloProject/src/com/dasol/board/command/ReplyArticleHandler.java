package com.dasol.board.command;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dasol.board.model.ArticleReply;
import com.dasol.board.service.ArticleReplyData;
import com.dasol.board.service.ReplyArticleService;
import com.dasol.mvc.command.CommandHandler;
import com.dasol.noti.model.MyNotification;
import com.dasol.noti.service.WriteMyNotiService;

public class ReplyArticleHandler implements CommandHandler {

	private ReplyArticleService replyArticleService = new ReplyArticleService();
	private WriteMyNotiService writeMyNotiService = new WriteMyNotiService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String article_no = request.getParameter("article_no");
		String nickname = request.getParameter("nickname");
		String member_id = request.getParameter("member_id");
		String comment = request.getParameter("comment");
		String articleUserId = request.getParameter("article_userId");
		
		int articleNo = Integer.parseInt(article_no);
		int memberId = Integer.parseInt(member_id);
		int myId = Integer.parseInt(articleUserId);
		
		ArticleReply articleReply = new ArticleReply(null, memberId, nickname, comment, new Date(), articleNo);
		ArticleReplyData articleReplyData = replyArticleService.insertReply(articleReply);
		
		MyNotification myNoti 
		= new MyNotification(null, articleNo, memberId, "reply", false, new Date(), myId);
			writeMyNotiService.writeMyNoti(myNoti);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("reply_no", articleReplyData.getReplyNo());
		jsonObj.put("member_id", articleReplyData.getMemberId());
		jsonObj.put("nickname", articleReplyData.getNickname());
		jsonObj.put("comment", articleReplyData.getCommnet());
		jsonObj.put("regDate", articleReplyData.getTransferRegDate());
		jsonObj.put("totReplyCnt", articleReplyData.getTotReplyCnt());
		
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out;
		out = response.getWriter();
		out.print(jsonObj.toString());
		
		return null;
	}
	
}
