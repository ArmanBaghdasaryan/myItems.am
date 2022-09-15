<%@ page import="model.Item" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<%
    List<Item> items = (List<Item>) request.getAttribute("items");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    User user = (User) session.getAttribute("user");
%>

<body>
<% if (user != null) {%>

<h1>Welcome <%= user.getName() %>
</h1>
<a href="/myItems">My Items </a> |
<a href="/items/add">Add Items</a> |
<a href="/myItems/remove?itemId=<%=user.getId()%>">Remove</a> |
<a href="/logout">Logout</a>


<%
} else {
%>
<div>
    <a href="/login" style="flex: auto">Login</a>
    <a href="/users/add" style="flex: auto">Register</a>
</div>
<a href="/items" style="flex: auto">Home</a>
<% for (Category category : categories) {%>
<a href="/main?categoryId=<%=category.getId()%> "> | <%=category.getName() %>
</a>
<%}%>

<%}%>
<div>
    <%for (Item item : items) {%>

    <% if (item.getPicUrl() != null) {
    %>
    <img src="/image?path=<%=item.getPicUrl()%>" width="100"/>
    <%
    } else {
    %>
    <img src="/image/no-image-icon-13.png" width="100" />
    <%
        }
    %>
    <span><%=item.getTitle()%>|<%=item.getPrice()%></span>
</div>

<%
    }
%>


</body>
</html>
