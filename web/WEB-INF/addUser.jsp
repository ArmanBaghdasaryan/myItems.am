<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/11/2022
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%
    String msg = (String) request.getAttribute("msg");
%>

<%
    if (msg != null) {%>
<p style="color: red"><%=msg%>
</p>

<%}%>

Please input User's data
<form action="/users/add" method="post" enctype="multipart/form-data">
    <input type="text" name="name" placeholder="please input name"/><br>
    <input type="text" name="surname" placeholder="please input surname"/><br>
    <input type="email" name="email" placeholder="please input email"/><br>
    <input type="password" name="password" placeholder="please input password"/><br>

    <input type="submit" value="Register">
</form>

</body>
</html>
