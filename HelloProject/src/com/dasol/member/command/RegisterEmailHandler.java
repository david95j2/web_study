package com.dasol.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.auth.service.User;
import com.dasol.member.service.MemberNotFoundException;
import com.dasol.member.service.RegisterCodeNotMatchException;
import com.dasol.member.service.RegisterEmailService;
import com.dasol.mvc.command.CommandHandler;

public class RegisterEmailHandler implements CommandHandler {

	private RegisterEmailService registerEmailService = new RegisterEmailService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String registerCode = request.getParameter("registerCode");
		String email = request.getParameter("email");

		try {
			User user = registerEmailService.register(email, registerCode);
			request.getSession().setAttribute("authUser", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
		} catch (MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		} catch (RegisterCodeNotMatchException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

	}

}
