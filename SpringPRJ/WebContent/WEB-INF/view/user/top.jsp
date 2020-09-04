<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String user_name = nvl((String)session.getAttribute("user_name"));
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navber-light navbar-expand-lg navbar-fixed white no-background bootsnav" style="background-color:red;">

<!--  Start Top Search -->
<div class="top-search">
	<div class=container">
		<div class=input-group">
			<span class="input-group-addon"><i class="fa-search"></i></span>
			<input type="text" class="form-control" placeholder="Search">
			<span class="input-group-addon close-search" onclick="JavaScript:search();"><i class="fa fa-times">검색</i></span>
		</div>
	</div>
</div>
<!--  End Top Search -->

<div class="container">
	<!--  Start Atribute Navigation -->
	<div class="attr-nav">
		<ul>
			<li>
				<%if(user_name.isEmpty()){ %>
				<a href="/user/userLogin.do" style="color:#2b2b2b;">
					Sign in
				</a>
				<%} else{%>
				<a href="/user/userLogOut.do" style="color:#2b2b2b;"><%=user_name %>님 Sign Out</a>
				<%} %>
			</li>
		</ul>
	</div>
	<!-- End Atribute Navigation -->
</div>
<!--  End Side Menu -->
</nav>


</body>
</html>