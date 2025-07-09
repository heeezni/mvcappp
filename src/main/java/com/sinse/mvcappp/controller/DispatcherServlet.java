package com.sinse.mvcappp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		Controller controller=null;
		
		if(request.getRequestURI().equals("/blood.do")) { // 클라이언트의 요청이 혈액형이면
			/*
			 * 혈액형을 전문적으로 처리하는 컨트롤러에게 업무 분담!
			 * 요청에 대한 처리를 1:1 대응하는 객체로 처리하는 개발 패턴 : Command Pattern
			 * */
			controller = new BloodController();
		} else if(request.getRequestURI().equals("/color.do")) {
			controller=new ColorController();
		}
		controller.execute(request, response);
	}
	


}
