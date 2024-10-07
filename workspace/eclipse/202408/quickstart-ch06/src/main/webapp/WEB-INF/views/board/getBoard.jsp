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
    
	<title>Board Detail</title>

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
	<h3><%= request.getRequestURI() %></h3>
	<hr>
	
	<div>
		<h3>Board Detail</h3>
		
		<form action="update" method="post">
			<input type="hidden" name="currPage" value="${param.currPage}">

			<table>
				<tr>
					<th>Seq</th>
					<td><input type="text" name="seq" value="${_BOARD_.seq}" readonly></td>
				</tr>
				<tr>
					<th>Create Date</th>
					<td>${_BOARD_.createDate}</td>
				</tr>
				<tr>
					<th>Last Modified Date</th>
					<td>${_BOARD_.updateDate}</td>
				</tr>
				<tr>
					<th>Cnt</th>
					<td>${_BOARD_.cnt}</td>
				</tr>
				<tr>
					<th>Title</th>
					<td><input type="text" size="78" name="title" value="${_BOARD_.title}" required></td>
				</tr>
				<tr>
					<th>Writer</th>
					<td><input type="text" name="writer" value="${_BOARD_.writer}" readonly></td>
				</tr>
				<tr>
					<th>Content</th>
					<td><textarea rows="15" cols="80"  name="content">${_BOARD_.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="2" id="submit">
						<input type="submit" value="update">
						<input type="reset" value="cancel">
					</td>
				</tr>
			</table>
		</form>
		<br>

		<a href="getBoardList?currPage=${param.currPage}">Back To List</a> | <a href="delete?seq=${_BOARD_.seq}&currPage=${param.currPage}">Delete</a>
	</div>

</body>
</html>