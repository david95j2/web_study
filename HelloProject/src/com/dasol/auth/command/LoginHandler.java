package com.dasol.auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.auth.service.LoginFailException;
import com.dasol.auth.service.LoginService;
import com.dasol.auth.service.User;
import com.dasol.mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginService loginService = new LoginService();

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
		String email = trim(request.getParameter("email"));
		String password = trim(request.getParameter("password"));
		String remember = request.getParameter("remember");
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		try {
			User user = loginService.login(email, password, remember);
			
			if (user.getRememberToken() != null) {
				Cookie cookie = new Cookie("aT", user.getRememberToken());
				cookie.setMaxAge(14*24*60*60);
				response.addCookie(cookie);
			}
			
			request.getSession().setAttribute("authUser", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
			
		} catch (LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
		
	}
	
	private String trim(String str) {
		return str == null ? null : str.trim();
	}

}
