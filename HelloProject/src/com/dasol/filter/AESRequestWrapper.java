package com.dasol.filter;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.dasol.util.AES256Util;

public class AESRequestWrapper extends HttpServletRequestWrapper {

	private AES256Util aes256 = null;
	private HttpServletRequest request;
	private String key = "12345678901234567890/";
	
	public AESRequestWrapper(HttpServletRequest request) throws UnsupportedEncodingException {
		super(request);
		this.request = request;
		aes256 = new AES256Util(key);
	}
	
	@Override
	public String getParameter(String name) {
		try {
			System.out.println("do " + doEncryption(request.getParameter(name)));
			return doEncryption(request.getParameter(name));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String doEncryption(String str) throws Exception {
		return aes256.aesEncode(str); 
	}
	
}
