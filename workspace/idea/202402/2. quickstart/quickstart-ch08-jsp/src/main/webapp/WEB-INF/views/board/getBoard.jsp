<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>getLoad</title>

    <style>
        a {
            text-decoration: none;
        }
        td {
            line-height: 1.5;
        }
    </style>
</head>

<body>
    <h4>/WEB-INF/views/board/getLoad.jsp?pageNumber=${criteria.pageNumber}&seq=${board.seq}</h4>
    <hr>

    <h3>- Board Detail -</h3>

    <div style="width: 500px; text-align: right;">
        <a href="/board/insertBoard?pageNumber=${criteria.pageNumber}"><button type="button">Register</button></a>
        <a href="/board/deleteBoard?pageNumber=${criteria.pageNumber}&seq=${board.seq}"><button type="button">Remove</button></a>
        <a href="/board/getBoardList?pageNumber=${criteria.pageNumber}"><button type="button">List</button></a>
    </div>

    <form action="/board/updateBoard" method="post" style="margin-top: 5px;">
        <input type="hidden" name="seq" value="${board.seq}">
        <input type="hidden" name="cnt" value="${board.cnt}">
        <input type="hidden" name="pageNumber" value="${param.pageNumber}">

        <table border="1" cellspacing="0" cellpadding="0" width="500" style="margin-bottom: 10px;">
            <tr>
                <td align="center" bgcolor="gold" width="100">seq</td>
                <td>&nbsp;<strong style="color: red;">${board.seq}</strong></td>
            </tr>
            <tr>
                <td align="center" bgcolor="gold" width="80">title</td>
                <td>&nbsp;<input type="text" name="title" size="47" value="${board.title}" required></td>
            </tr>
            <tr>
                <td align="center" bgcolor="gold">content</td>
                <td>&nbsp;<textarea name="content" cols="49" rows="10" required>${board.content}</textarea></td>
            </tr>
            <tr>
                <td align="center" bgcolor="gold">writer(id)</td>
                <td>&nbsp;${board.member.name}(${board.member.id})</td>
            </tr>
            <tr>
                <td align="center" bgcolor="gold">cnt</td>
                <td>&nbsp;${board.cnt}</td>
            </tr>
            <tr>
                <td align="center" bgcolor="gold">insert_ts</td>
                <td>&nbsp;${board.insertTs}</td>
            </tr>
            <tr>
                <td align="center" bgcolor="gold">update_ts</td>
                <td>&nbsp;${board.updateTs}</td>
            </tr>
        </table>

        <div style="width: 500px;" align="center">
            <input type="submit" value="Update">
        </div>

    </form>
</body>

</html>