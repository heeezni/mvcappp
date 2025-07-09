package com.sinse.mvcappp.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 모든 종류의 요청을 다 받을 수 있는 서블릿 (get. post..)
 */
@Slf4j
public class DispatcherServlet extends HttpServlet {

	// 이 서블릿의 인스턴스가 생성될 때, 초기화가 진행되고, 이 초기화 진행 시점에
	// 설정 파일로부터, 해당 요청에 어떤 하위 컨트롤러가 작동해야 하는지 분석하여, 요청을 전달하기 위해
	FileInputStream fis; // key-value의 파일을 읽어들이는 스트림 but 해석능력이 없음
	// 그래서 해석능력있는 Properties씀
	Properties props; // java.util.Map의 자식 중 Properties라는 객체는 자신이 읽어들인 문자열이
					  // =을 기준으로 key=value 형태로 되어있다면, 이것을 해석할 수 있는 능력 가진 객체

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext(); // 현재 서블릿이 실행되고 있는 문맥상의 애플리케이션
		String realPath = context.getRealPath(config.getInitParameter("contextConfigLocation"));
		// WEB-INF : 외부 웹브라우저에서의 접근이 불가능한 보안 디렉토리이지만, 자바 코드 내부에서는 얼마든지 접근 가능

		log.debug(realPath);
		props=new Properties();
		
		try {
			fis = new FileInputStream(realPath);
			props.load(fis); // 실제적으로 파일을 읽어들인 객체는 fis이므로, props fis를 로드해야

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	/**
	 * 이 코드의 단점 : 서블릿 매핑이 if문으로 바뀐 것 뿐 (매 요청마다 if문 만들어야함) 매 요청마다 1:1 대응되는 매핑을 피하기 위해
	 * 하나의 진입점으로 몰았으나 진입점이 되는 클래스가 매 요청마다 1:1 대응되는 if 조건문이 작성되고 있음
	 *
	 * 어떻게 해야 if문을 없앨 수 있을꺄?
	 */
	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Controller controller = null;

		/*
		 * 혈액형을 전문적으로 처리하는 컨트롤러에게 업무 분담! 요청에 대한 처리를 1:1 대응하는 객체로 처리하는 개발 패턴 
		 * : Command Pattern
		 */
		//controller = new BloodController(); 이것때문에 if문을 못 없애는 중
		log.debug(props.getProperty(request.getRequestURI())); // key값으로 검색하면 오른쪽 문자열 검색됨
		// 로그: com.sinse.mvcappp.controller.BloodController
		
		// new 연산자만이 인스턴스를 생성할 수 있는 건 아님!
		try {
			Class cls=Class.forName(props.getProperty(request.getRequestURI()));
			controller=(Controller)cls.newInstance();
			controller.execute(request, response); // 컨트롤러 실행
			
			//5) 알맞은 뷰 보여주기
			response.sendRedirect(props.getProperty(controller.getViewPage())); // 클라이언트의 재접속 강제 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
