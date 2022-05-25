<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Java Web Tutorial</title>
</head>
<body>
    <h3>JSTL - Java Standard Tag Library</h3>
    <c:forEach var="i" items="1,2,3,4,5,6,7,8">
        Item <c:out value="No. ${i}"/>
    </c:forEach>
</body>
</html>
