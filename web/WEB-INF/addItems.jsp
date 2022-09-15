<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/11/2022
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");

%>
<body>
Please input item's
<form action="/items/add" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="please input title"/><br>
    <input type="number" name="price" placeholder="please input price"/><br>
    <select name="categoryId">
        <% for (Category category : categories) {%>
        <option value="<%=category.getId()%>">
            <%=category.getName()%>
        </option>
        <% } %><br>
    </select>
    <input type="file" name="picUrl"/><br>
    <input type="submit" value="Add">
</form>


</body>
</html>
