<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Board List</title>
</head>

<body>
    <h3>/WEB-INF/views/getBoardList.jsp</h3>
    <hr>

    <c:forEach var="board" items="${boardList}">
        <h3>${board}</h3>
    </c:forEach>
</body>

</html>