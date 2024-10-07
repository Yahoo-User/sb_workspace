<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


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

    <center>
        <h3>Board List</h3>

        <table border="1" cellspacing="0" cellpadding="0" width="700">
            <tr>
                <th bgcolor="orange" width="100">SEQ</th>
                <th bgcolor="orange" width="200">TITLE</th>
                <th bgcolor="orange" width="150">WRITER</th>
                <th bgcolor="orange" width="150">CREATE_DATE</th>
                <th bgcolor="orange" width="100">CNT</th>
            </tr>

            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td align="center">${board.seq}</td>
                    <td align="left">&nbsp;&nbsp;<a href="getBoard?seq=${board.seq}">${board.title}</a></td>
                    <td align="center">${board.writer}</td>
                    <td align="center"><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                    <td align="center">${board.cnt}</td>
                </tr>
            </c:forEach>
        </table>

        <br>

        <a href="insertBoard">REGISTER</a>
    </center>
</body>

</html>