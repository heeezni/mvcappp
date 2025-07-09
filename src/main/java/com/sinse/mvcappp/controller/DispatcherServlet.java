package com.sinse.mvcappp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinse.mvcappp.model.BloodManager;

import lombok.extern.slf4j.Slf4j;

/**
 * 모든 종류의 요청을 다 받을 수 있는 서블릿 (get. post..)
 * */
@Slf4j
public class DispatcherServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청을 받는다
		String blood=request.getParameter("blood");
		// 요청 분석한다 (생략)
		// 일시킨다
		BloodManager manger=new BloodManager();
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
