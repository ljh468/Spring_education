<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="poly.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<BoardDTO> rList = (List<BoardDTO>) request.getAttribute("rList");
	System.out.println(rList.size());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Board
	<div style="margin:auto;width:800px">
	<table border='1' style="width:100%">
		<tr>
		<td>글번호</td>
		<td>글제목</td>
		<td>게시일</td>
		<td>작성자</td>
		</tr>
		
		<% for(BoardDTO B : rList) { %>
		<tr>
		<td><%=nvl(B.getPost_no()) %></td>
		<td><a href="/board/boardDetail.do?no=<%=B.getPost_no()%>"><%=nvl(B.getPost_title()) %></a></td>
		<td><%=nvl(B.getReg_dt()) %></td>
		<td><%=nvl(B.getReg_id()) %></td>
		</tr>
		<% } %>
	</table>
	<div style="width:100%; text-align:right; margin-top:5px;">
	<button type="button"  onclick="location.href='/board/newPost.do'">새글</button></div>
	</div>
</body>
</html>