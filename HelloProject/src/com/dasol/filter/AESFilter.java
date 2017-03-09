package com.dasol.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AESFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("AESFILTER");
		AESRequestWrapper wrapper = new AESRequestWrapper((HttpServletRequest) req);
		chain.doFilter(wrapper, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
