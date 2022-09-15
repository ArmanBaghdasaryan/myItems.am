<%@ page import="manager.CategoryManager" %>
<%@ page import="model.Item" %>
<%@ page import="manager.UserManager" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/14/2022
  Time: 2:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
</head>
<%
    List<Item> items = (List<Item>) request.getAttribute("items");

%>
<body>
<div>
    <a href="/login" style="flex: auto">Login</a>
    <a href="/users/register" style="flex: auto">Register</a>
</div>


<% for (Item item : items) {
%>

<%if (item.getPicUrl() != null) { %>
<img src="/image?path=<%=item.getPicUrl()%>" width="100"/>
<%
} else {
%>
<img src="/image/defaultPic.png" width="100"/>
<%
    }
%>
<%=item.getTitle()%><br>
<%=item.getPrice()%><br>
<%=item.getUser().getName()%>
<%=item.getUser().getSurname()%>


<% }
%>
</body>

</html>
