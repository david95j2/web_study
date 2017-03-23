package com.dasol.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.auth.service.User;
import com.dasol.member.service.DuplicateIdException;
import com.dasol.member.service.EmailNotFoundException;
import com.dasol.member.service.JoinRequest;
import com.dasol.member.service.JoinService;
import com.dasol.mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();

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

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		JoinRequest joinRequest 
			= new JoinRequest(request.getParameter("email"),
					request.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);

		try {
			joinService.join(joinRequest);
			return "/login.do";
		} catch (DuplicateIdException e) {
			errors.put("duplicatedId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute("authUser");
			String email = user.getEmail();
			
			if (email.equals("") && email.isEmpty()) {
				email = request.getParameter("email");
				if (email.equals("") && email.isEmpty()) {
					throw new EmailNotFoundException();
				}
			}
			System.out.println("email=" + email);
			joinService.sendEmail(email, null);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
		} catch (EmailNotFoundException e) {
			return FORM_VIEW;
		} catch (Exception e) {
			return FORM_VIEW;
		}
		
	}

}
