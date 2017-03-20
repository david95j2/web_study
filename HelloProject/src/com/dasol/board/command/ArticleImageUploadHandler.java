package com.dasol.board.command;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dasol.mvc.command.CommandHandler;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ArticleImageUploadHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("boardimg");
		int size = 1024 * 1024 * 10;
		String file = "";
		String oriFile = "";

		try {
			MultipartRequest multi 
				= new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());

			Enumeration files = multi.getFileNames();
			
			String str = (String) files.nextElement();
			
			file = multi.getFilesystemName(str);
			oriFile = multi.getOriginalFileName(str);
			
			String boardImage = file;
			
			System.out.println("/boardimg/" + file);
			PrintWriter out;
			out = response.getWriter();
			out.print("/boardimg/" + file);
			
			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
