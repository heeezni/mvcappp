package com.sinse.mvcappp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcappp.model.BloodManager;
import com.sinse.mvcappp.model.ColorManager;


/**
 * 모든 종류의 요청을 다 받을 수 있는 서블릿 (get. post..)
 * */
public class DispatcherServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	/**
	 * 이 코드의 단점 : 서블릿 매핑이 if문으로 바뀐 것 뿐 (매 요청마다 if문 만들어야함)
	 * 매 요청마다 1:1 대응되는 매핑을 피하기 위해 하나의 진입점으로 몰았으나
	 * 진입점이 되는 클래스가 매 요청마다 1:1 대응되는 if 조건문이 작성되고 있음 
	 *
	 * 어떻게 해야 if문을 없앨 수 있을꺄? 
	 * */
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getRequestURI().equals("/blood.do")) { // 클라이언트의 요청이 혈액형이면
			
		// 요청을 받는다
		String blood=request.getParameter("blood");
		// 요청 분석한다 (생략)
		// 일시킨다
		BloodManager manager=new BloodManager();
		String result=manager.getAdvice(blood);
		
		HttpSession session=request.getSession(); // 이 요청에 의해 접근할 수 있는 세션 얻기
		session.setAttribute("msg", result); // 저장
		
		// 알맞은 뷰 선택
		response.sendRedirect("/blood/result.jsp"); // 클라이언트의 재접속 강제
		
		} else if(request.getRequestURI().equals("/color.do")) {
			String color=request.getParameter("color");
			ColorManager manager=new ColorManager();
			
			String result=manager.getAdvice(color);
			
			HttpSession session=request.getSession();
			session.setAttribute("msg", result);
			
			response.sendRedirect("/color/result.jsp");
			
		}
	}
	


}
