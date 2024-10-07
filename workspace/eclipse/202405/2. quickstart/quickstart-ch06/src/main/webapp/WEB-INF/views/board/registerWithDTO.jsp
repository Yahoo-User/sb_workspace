<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- @ taglib uri="jakarta.tags.core" prefix="c" --%>
<%-- @ taglib uri="jakarta.tags.fmt" prefix="fmt" --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
	<title>Register with DTO</title>

	<style>
		div {
			width: 700px;
		}
		table {
			width: 100%;
			border-collapse: collapse;
		}
		table, th, td {
			border: 1px solid black;
			text-align: center;
		}
		table td {
			width: 80%;
			height: 25px;
			padding-left: 10px;

			text-align: left;
		}
		#submit {
			text-align: center;
		}
	</style>
</head>

<body>
	<h3>/WEB-INF/views/board/registerWithDTO.jsp</h3>
	<hr>
	
	<div>
		<h3>Register with DTO</h3>
		
		<form action="registerWithDTO" method="post">
			<input type="hidden" name="currPage" value="${param.currPage == '' ? 1 : param.currPage}">

			<table>
				<tr>
					<th>Title</th>
					<td><input type="text" size="78" name="title" placeholder="Title" required></td>
				</tr>
				<tr>
					<th>Writer</th>
					<td>
						<select name="writer" required>
							<option value="" selected>- Select -</option>
							<option value="Yoseph">1. Yoseph</option>
							<option value="Trinity">2. Trinity</option>
							<option value="Pyramid">3. Pyramid</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>Content</th>
					<td><textarea rows="15" cols="80"  name="content" placeholder="Please input content ..."></textarea></td>
				</tr>
				<tr>
					<td colspan="2" id="submit">
						<input type="submit" value="register">
						<input type="reset" value="cancel">
					</td>
				</tr>
			</table>
		</form>
		<br>

		<a href="getBoardList?currPage=${param.currPage == '' ? 1 : param.currPage}">Back To List</a>
	</div>

</body>
</html>
