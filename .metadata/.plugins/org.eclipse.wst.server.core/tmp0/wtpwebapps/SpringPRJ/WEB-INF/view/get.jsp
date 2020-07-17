<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	String name = nvl((String)request.getAttribute("getgo"));
	// controller의 model에 들어있는 고유키를 통해 가져와서 jsp내장변수 request에 저장
	// ModelMap객체를 받을때 Object로 받았기때문에 String 형변환 필요

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

안녕하세요, <%=name %> 님!
</body>
</html>