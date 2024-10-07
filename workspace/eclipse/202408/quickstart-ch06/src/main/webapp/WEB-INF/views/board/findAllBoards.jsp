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

	<title>Board List - 1</title>

	<style>
		div {
			/* text-align: center; */
		}
		table {
			width: 800px;
			border-collapse: collapse;
		}
		table, tr, th, td {
			border: 1px solid black;
		}
		tr:nth-of-type(1) {
			width: 100px;
			background-color: aqua;
		}
		tr:hover {
			background-color: blanchedalmond;
		}
		tr > td {
			text-align: center;
		}
		tr > td:nth-of-type(2) {
			text-align: left;
			padding-left: 5px;
		}
		a {
			text-decoration: none;
		}
	</style>
</head>

<body>
	<h3><%= request.getRequestURI() %></h3>
	<hr>

	<div>
        <h3>Board List</h3>

		<table>
			<tr>
				<th>seq</th>
				<th>title</th>
				<th>writer</th>
				<th>createDate</th>
				<th>updateDate</th>
				<th>cnt</th>
			</tr>

			<c:forEach var="board" items="${_BOARD_PAGE_}">
				<tr>
					<td>${board.seq()}</td>
					<td>${board.title()}</td>
					<td>${board.writer()}</td>
					
					<%-- **Important: <fmt:formatDate/> tag cannot convert `LocalDateTime` as defined pattern --%>
					
					<td><fmt:formatDate value="${board.createDate()}" pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${board.updateDate()}" pattern="yyyy-MM-dd" /></td>
					
					<td>${board.cnt()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>