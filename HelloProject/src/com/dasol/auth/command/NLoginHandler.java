package com.dasol.auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.auth.service.LoginRequest;
import com.dasol.auth.service.NLoginService;
import com.dasol.auth.service.User;
import com.dasol.mvc.command.CommandHandler;
import com.dasol.noti.service.ReadMyNotiService;

public class NLoginHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private NLoginService nLoginService = new NLoginService();
	private ReadMyNotiService notiService = new ReadMyNotiService();
	
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

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(request.getParameter("email"));
		loginRequest.setNickname(request.getParameter("nickname"));
		loginRequest.setProfileImage(request.getParameter("profile_image"));
		loginRequest.setAccessToken(request.getParameter("access_token"));
		
		User authUser = nLoginService.login(loginRequest);
		
		boolean notiCheck = notiService.isNotiCheck(authUser.getId());
		request.getSession().setAttribute("notiCheck", notiCheck);
		
		request.getSession().setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return null;
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	
	
}
