<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
//     response.sendRedirect("/");
     List<Category> categories = (List<Category>) request.getAttribute("categories");
     List<Item> items = (List<Item>) request.getAttribute("items");
%>
<body>
<h2>Categories</h2>
<table border="1">
    <tr>
        <th>Category</th>
        <th>Items Picture</th>
        <th>Items Data</th>
            <%
            for (Category category : categories) {  %>
    <tr>
        <td>
            <a href="/categories?categoryId=<%=category.getId()%>"><%=category.getName()%>
            </a><br>
        </td>
        <% } %>

        <%for (Item item : items) {%>
        <td>
            <img src="/getImage?picUrl=<%=item.getPicUrl()%>" width="100">
        </td>
        <td>
            <span><%=item.getTitle()%>|<%=item.getPrice()%></span>
        </td>
        <% } %>
    </tr>


</table>

</body>
</html>
