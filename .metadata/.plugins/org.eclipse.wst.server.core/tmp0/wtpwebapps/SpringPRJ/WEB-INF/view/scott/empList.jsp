<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="poly.dto.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	List<EmpDTO> rList = (List<EmpDTO>)request.getAttribute("rList");

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border='1'>
<tr>
<td>사번</td>
<td>사원이름</td>
<td>직책</td>
<td>매니저</td>
<td>고용일</td>
<td>급여</td>
<td>보너스</td>
<td>부서번호</td>
</tr>
	<% for(EmpDTO e : rList) { %>
	<tr>
	<td><%=nvl(e.getEmpno()) %></td>
	<td><%=nvl(e.getEname()) %></td>
	<td><%=nvl(e.getJob()) %></td>
	<td><%=nvl(e.getMgr(),"0") %></td>
	<td><%=nvl(e.getHiredate()) %></td>
	<td><%=nvl(e.getSal()) %></td>
	<td><%=nvl(e.getComm(),"0") %></td>
	<td><%=nvl(e.getDeptno()) %></td>
	</tr>
	<% } %>
</table>
</body>
</html>