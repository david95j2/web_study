package com.dasol.auth.command;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.auth.service.AutoLoginService;
import com.dasol.auth.service.User;
import com.dasol.mvc.command.CommandHandler;
import com.dasol.util.CookieBox;

public class AutoLoginHandler implements CommandHandler {

	private AutoLoginService autoLoginService = new AutoLoginService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> cookiesMap = null;
		cookiesMap = CookieBox.getCookiesValueMap(request);
		String token = cookiesMap.get("aT");
		
		User user = autoLoginService.autoLogin(token);
		
		if (user.getRememberToken() != null) {
			Cookie cookie = new Cookie("aT", user.getRememberToken());
			cookie.setMaxAge(14*24*60*60);
			response.addCookie(cookie);
		}
		
		request.getSession().setAttribute("authUser", user);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return null;
		
	}

}
