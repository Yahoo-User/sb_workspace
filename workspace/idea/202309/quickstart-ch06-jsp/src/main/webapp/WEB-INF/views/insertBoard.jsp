<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>


<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>insertBoard</title>
</head>

<body>
    <h3>/insertBoard.jsp</h3>
    <hr>

    <center>

        <h3>REGISTER</h3>

        <form action="insertBoard" method="POST">

            <table border="1" cellspacing="0" cellpadding="0" width="500">
                <tr>
                    <td align="center" bgcolor="orange">TITLE</td>
                    <td align="left" style="padding: 5px;"><input type="text" name="title"></td>
                </tr>

                <tr>
                    <td align="center" bgcolor="orange">WRITER</td>
                    <td align="left" style="padding: 5px;"><input type="text" name="writer" maxlength="10"></td>
                </tr>

                <tr>
                    <td align="center" bgcolor="orange">CONTENT</td>
                    <td align="left" style="padding: 5px;"><textarea name="content" cols="50" rows="10"></textarea></td>
                </tr>

                <tr>
                    <td colspan="2" align="center" style="padding: 5px;"><input type="submit" value="Register"></td>
                </tr>
            </table>

        </form>

    </center>
</body>

</html>