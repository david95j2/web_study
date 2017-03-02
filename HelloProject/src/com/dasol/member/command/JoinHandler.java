package com.dasol.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.member.service.DuplicateIdException;
import com.dasol.member.service.JoinRequest;
import com.dasol.member.service.JoinService;
import com.dasol.mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		JoinRequest joinRequest = new JoinRequest();
		joinRequest.setEmail(request.getParameter("email"));
		joinRequest.setPassword(request.getParameter("password"));
		joinRequest.setConfirmPassword(request.getParameter("confirmPassword"));
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		joinRequest.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			joinService.join(joinRequest);
			return "/WEB-INF/view/joinSuccess.jsp";
		} catch (DuplicateIdException e) {
			errors.put("duplicatedId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

}
