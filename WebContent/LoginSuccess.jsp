<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String userName = null;
Cookie[] cookies = request.getCookies();
if (cookies != null){
for(Cookie cookie:cookies){
if (cookie.getName().equals("user")) userName = cookie.getValue();
}
}
if (userName == null) response.sendRedirect("login.html");
%>
<table bgcolor=Gainsboro>
<tr >
<td  height="100" width="800">
<center><h1>Добро пожаловать, <%=userName %> !!!</h1></center>
</td>
<td>
<form action="LogoutServlet" method="post">
<center><input type="submit" value="Выйти"></center>
</td>
</form>
</tr>

<tr>
<td bgcolor=RosyBrown><a href = "start.html"><h3><b>На главную</b></h3></a>
</td>
<td rowspan = "4">
<img src= image/7.jpg height="300" width="300">
</tr>


</table>
</body>
</html>>