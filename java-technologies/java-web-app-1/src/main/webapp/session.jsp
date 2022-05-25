<%--
  Created by IntelliJ IDEA.
  User: gafur
  Date: 5/16/22
  Time: 3:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Web Tutorial</title>
</head>
<body>
    <h3>Session Sample: JSP Page</h3>

    <p>
    <%
        String name = (String) request.getSession().getAttribute("uname");
        out.write(name);
    %>
    </p>
    <p>Get name saved in second session servlet: <a href="/java-web-app/session-second">get</a></p>
</body>
</html>
