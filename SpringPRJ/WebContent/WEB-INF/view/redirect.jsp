<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String msg = (String)request.getAttribute("msg");
	String url = (String)request.getAttribute("url");
	System.out.println("url"+url);
%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
alert("<%=msg %>")
top.location.href = "<%=url%>"

</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>