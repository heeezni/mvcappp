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
	<form action="/blood/advice" method="get"> <!-- 컨트롤러인 서블릿의 가상의 주소 -->
		<select name="blood">
			<option value="">혈액형 선택</option>
			<option value="A">A형</option>
			<option value="B">B형</option>
			<option value="O">O형</option>
			<option value="AB">AB형</option>
		</select>
		<button>전송</button>
	</form>

</body>
</html>