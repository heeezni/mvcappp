package com.sinse.mvcappp.controller.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcappp.controller.Controller;
import com.sinse.mvcappp.repository.NoticeDAO;

/**
 * 공지게시판의 목록 요청을 처리하는 컨트롤러
 */
public class ListController implements Controller {
	NoticeDAO noticeDAO = new NoticeDAO();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 3단계: 알맞은 로직 객체에 일 시키기
		List list = noticeDAO.selectAll();

		// 4단계: 결과 페이지에서 보여질 데이터 저장
		HttpSession session = request.getSession();
		session.setAttribute("noticeList", list); // (저장할 때 이름 뭘로 할 건지, 뭘 담을건지)
	}

	@Override
	public String getViewPage() {
		return "/notice/list/view";
	}
}
