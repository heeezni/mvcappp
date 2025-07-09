package com.sinse.mvcappp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcappp.model.BloodManager;

/**
 * 혈액형에 대한 판단 요청을 처리하는 컨트롤러 정의
 * 서블릿 X 일반 클래스
 * */
public class BloodController {
	
	BloodManager bloodManger=new BloodManager();

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청을 받는다
		String blood=request.getParameter("blood");
		
		// 2) 요청을 분석한다 (생략. 어차피 이 컨트롤러는 혈액형에 대한 처리이므로)
		// 3) 일 시킨다
		String result = bloodManger.getAdvice(blood);
		// 4) 저장할 것이 있다면  (뷰로 가져갈 것이 있다면) 저장
		HttpSession session=request.getSession(); // 이 요청에 의해 접근할 수 있는 세션 얻기
		session.setAttribute("msg", result); // 저장
		// 5) 알맞은 뷰 보여주기
		response.sendRedirect("/blood/result.jsp"); // 클라이언트의 재접속 강제
	}
}
