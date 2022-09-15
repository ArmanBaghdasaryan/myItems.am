<%@ page import="model.Item" %>
<%@ page import="java.util.List" %>
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
<img src="/image/no-image-icon-13.png" width="100"/>
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
