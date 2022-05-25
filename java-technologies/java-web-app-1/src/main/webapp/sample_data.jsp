<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java Web Tutorial</title>
</head>
<body>
    <h3>Get data from Servlet(Scriptlet)</h3>

    <p>Using scriptlet(not recommended):</p>
    <p><strong>Data:
    <%
        String bigData = (String) request.getAttribute("key");
        out.write(bigData);
    %>
    </strong></p>

    <p>Second scriptlet:</p>
    <p><strong>Data: <%=request.getAttribute("key")%></strong></p>
</body>
</html>
