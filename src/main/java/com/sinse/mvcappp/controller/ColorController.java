package com.sinse.mvcappp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sinse.mvcappp.model.ColorManager;

/**
 * MVC 패턴에 의해 디자인 영역과 로직 및 데이터 영역을 분리시키려면 
 * 중간에 중재자가 나서야하므로 코드에서 분리
 * 
 * 왜 서블릿으로 정의할까? 
 * 1) JSP는 VIEW로 사용할 것이므로
 * 2) 웹기반의 컨트롤러는 클라이언트의 요청을 받을 수 있어야 하므로
 * */
public class ColorController implements Controller{
	
	ColorManager manager=new ColorManager(); // model에 일시키자!
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Controller의 5대 업무
		 * 
		 * 1) 요청을 받는다
		 * 2) 요청을 분석한다
		 * 3) 알맞은 로직객체(모델)에 일 시킨다 (직접하지 않는다!)
		 * 4) 결과에 보여질 데이터를 보관한다
		 * 5) 알맞은 결과를 보여준다
		 * */
		
		// ========== controller ===================
		String color=request.getParameter("color");
		//if(color==null)color="";
		String result=manager.getAdvice(color);
		
		// 여기서 절대 결과 출력 X 
		// MVC로 분리해야 하므로 컨트롤러가 View 역할을 해서는 안됨
		
		// 지역변수인 result를 다른 jsp에 전달하자!
		HttpSession session=request.getSession();
		session.setAttribute("msg", result); // 세션에 보관 (톰캣 재가동, 브라우저 종료, 오랜시간 지나기 아니면 안죽음) 
		
		/*// result.jsp를 클라이언트가 보도록 처리
		response.sendRedirect("/color/result/view"); // 클라이언트가 지정된 url로 다시 요청을 시도하도록 강제
		// <script>location.href='/color/result.jsp'</script>와 같은 효과
		// 톰캣이 응답정보를 이용하여 응답 컨텐츠를 보내고 나서 클라이어느가 다시 접속 할 주소*/ 
	}
	
	@Override
	public String getViewPage() {
		return "/color/result/view";
	}
	
}
