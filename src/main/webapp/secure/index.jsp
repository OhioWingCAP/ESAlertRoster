<%@ page import="org.springframework.security.core.userdetails.User" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="gov.cap.ohwg.es.alertroster.model.pojo.GaeUser" %><%--
  Created by IntelliJ IDEA.
  User: ckovacs
  Date: 1/17/16
  Time: 4:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    GaeUser gaeUser = (GaeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String user = gaeUser.getEmail();%>
<%=user%>
</body>
</html>
