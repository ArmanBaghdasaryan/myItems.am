<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/11/2022
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Items</title>
</head>

<%
    User user = (User) session.getAttribute("user");
    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<body>
<h1>Welcome <%=user.getName()%>
</h1>

<a href="/items/add">add item</a>

<a href="/logout">logout</a><br>

<% for (Item item : items) { %>
<% if (user.equals(item.getUser())) { %>
<%=item.getTitle()%><br>
<%=item.getCategory().getName()%><br>
<%=item.getPrice()%><br>
<a href="/myItems/remove?itemId=<%=item.getId()%>"> Remove</a><br>
<% if (item.getPicUrl() != null) {
%>
<img src="/image?path=<%=item.getPicUrl()%>" width="100"/>
<%
} else {
%>
<img src="/image/no-image-icon-13.png" width="100"/>
<%
        }
    }
%>
<%} %>


</body>
</html>
