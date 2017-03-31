package com.dasol.member.command;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.auth.service.User;
import com.dasol.member.service.DuplicatedNickNameException;
import com.dasol.member.service.MemberNotFoundException;
import com.dasol.member.service.ModifyMyInfoService;
import com.dasol.member.service.MyInfo;
import com.dasol.mvc.command.CommandHandler;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ModifyMyInfoHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/modifyMyInfoForm.jsp";
	private ModifyMyInfoService modifyMyInfoService = new ModifyMyInfoService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("profileimg");
		User authUser = (User) request.getSession().getAttribute("authUser");

		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		int size = 1024 * 1024 * 10;
		String file = "";
//		String oriFile = "";

		try {
			MultipartRequest multi 
				= new MultipartRequest(request, path, size, "UTF-8", 
						new DefaultFileRenamePolicy());

			String nickname = multi.getParameter("nickname").trim();
			Enumeration files = multi.getFileNames();

			String str = (String) files.nextElement();
			
			file = multi.getFilesystemName(str);
//			oriFile = multi.getOriginalFileName(str);
			
			String profileImage = file;
			
			if (file != null) // 프로필 이미지 경로 셋팅
				profileImage = "/profileimg/" + file;
			else // 이미지 입력 null 처리
				profileImage = authUser.getProfileImage();
			
			MyInfo myInfo = new MyInfo(nickname, profileImage);
			authUser = modifyMyInfoService.modify(authUser.getId(), myInfo);
			
			request.getSession().setAttribute("authUser", authUser);
			
			if(authUser.getNickname() != null && authUser.getNickname().length() > 0)
				request.setAttribute("isSuccess", true);
			
			return FORM_VIEW;

		} catch (MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} catch (DuplicatedNickNameException e) {
			errors.put("duplicatedNickname", Boolean.TRUE);
			return FORM_VIEW;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 

	}

}
