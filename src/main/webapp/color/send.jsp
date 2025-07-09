<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 패턴 익히기</title>
</head>
<body>
	<!-- ============== VIEW ==================== -->
	<form action="/color.do" method="get"> <!-- 컨트롤러인 서블릿의 가상의 주소 (URI 확장자가 .do 여야함)-->
		<select name="color">
			<option value="">색상 선택</option>
			<option value="red">RED</option>
			<option value="blue">BLUE</option>
			<option value="yellow">YEOLLOW</option>
			<option value="green">GREEN</option>
		</select>
		<button>전송</button>
	</form>

</body>
</html>