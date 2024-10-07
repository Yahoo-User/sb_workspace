<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>insertBoard</title>

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
    <h4>/WEB-INF/views/board/insertBoard.jsp?pageNumber=${param.pageNumber}</h4>
    <hr>

    <h3>- Register -</h3>

    <form action="/board/insertBoard" method="post">
        <div style="width: 500px;" align="right">
            <a href="/board/getBoardList?pageNumber=${param.pageNumber}"><button type="button">List</button></a>
        </div>

        <table border="1" cellspacing="0" cellpadding="0" width="500" style="width: 500px; margin-top: 5px; margin-bottom: 10px;">
            <tr>
                <td align="center" bgcolor="gold">writer(id)</td>
                <td>&nbsp;<select name="writer">
                        <option value="member" selected>Member</option>
                        <option value="admin">Admin</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center" bgcolor="gold" width="100">title</td>
                <td>&nbsp;<input type="text" name="title" size="47" placeholder="Please input title ..." required></td>
            </tr>
            <tr>
                <td align="center" bgcolor="gold">content</td>
                <td>&nbsp;<textarea name="content" cols="49" rows="10" placeholder="Please input content ..." required></textarea></td>
            </tr>
        </table>

        <div style="width: 500px;" align="center">
            <input type="submit" value="Register">
        </div>
    </form>
</body>

</html>