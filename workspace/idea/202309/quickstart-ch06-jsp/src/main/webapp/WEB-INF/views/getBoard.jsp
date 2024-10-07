<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Detail</title>

    <style>
        td {
            padding: 5px;
        }
        tr > td:first-of-type {
            font-weight: bold;
        }
    </style>
</head>

<body>
    <h3>/WEB-INF/views/getBoard.jsp</h3>
    <hr>

    <center>
        
        <h3>DETAIL</h3>

        <form action="updateBoard" method="POST">
            <input type="hidden" name="seq" value="${board.seq}">

            <table border="1" cellspacing="0" cellpadding="0" width="500">
                <tr>
                    <td bgcolor="orange" align="center">TITLE</td>
                    <td align="left"><input type="text" name="title" value="${board.title}"></td>
                </tr>
                <tr>
                    <td bgcolor="orange" align="center">WRITER</td>
                    <td align="left">${board.writer}</td>
                </tr>
                <tr>
                    <td bgcolor="orange" align="center">CONTENT</td>
                    <td align="left"><textarea name="content" cols="45" rows="10">${board.content}</textarea></td>
                </tr>
                <tr>
                    <td bgcolor="orange" align="center">CREATE_DATE</td>
                    <td align="left"><fmt:formatDate value="${board.createDate}" pattern="yyyy/MM/dd HH:mm:ss"></fmt:formatDate></td>
                </tr>
                <tr>
                    <td bgcolor="orange" align="center">CNT</td>
                    <td align="left">${board.cnt}</td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="UPDATE"></td>
                </tr>
            </table>
        </form>

        <br>

        <a href="insertBoard">REGISTER</a>&nbsp;&nbsp;&nbsp;
        <a href="deleteBoard?seq=${board.seq}">REMOVE</a>&nbsp;&nbsp;&nbsp;
        <a href="getBoardList">LIST</a>

    </center>
</body>
</html>