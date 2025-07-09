package com.sinse.mvcappp.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcappp.controller.Controller;
import com.sinse.mvcappp.model.Notice;
import com.sinse.mvcappp.repository.NoticeDAO;

public class DetailController implements Controller {

	NoticeDAO noticeDAO=new NoticeDAO();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 3단계: 일시키기
		String notice_id=request.getParameter("notice_id");
		Notice notice=noticeDAO.select(Integer.parseInt(notice_id));
		
		// 4단계: 결과페이지에 가져갈 것이 있으므로 저장
		HttpSession session=request.getSession();
		session.setAttribute("notice", notice);
		
	}

	@Override
	public String getViewPage() {
		return "/notice/content/view";
	}
	
}
