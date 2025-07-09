package com.sinse.mvcappp.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.mvcappp.controller.Controller;
import com.sinse.mvcappp.model.Notice;
import com.sinse.mvcappp.repository.NoticeDAO;

public class RegistController implements Controller {

	NoticeDAO noticeDAO=new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		// 3단계 : 알맞은 로직 객체에 일 시키기
		noticeDAO.insert(notice);
		
		// 4단계 저장 (생략)
		
	}

	@Override
	public String getViewPage() {
		return "/notice/regist/view";
	}

	
	
}
