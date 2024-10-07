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

	<title>Board List - 2</title>

	<style>
		div {
			text-align: left;
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
		tr > th, td {
			text-align: center;
		}
		tr > td:nth-of-type(2) {
			width: 42%;
			text-align: left;
			padding-left: 5px;
		}
		a {
			text-decoration: none;
		}
		.pagination {
			margin: 0 0;
			padding: 5px 0;
			width: 755px;
			border: 0px solid red;
			text-align: center;
		}
		.pagination > li {
			display: inline-block;
			width: 40px;
			border-right: 1px solid purple;
			text-align: center;
		}
		.pagination > li:first-of-type {
			border-left: 1px solid purple;
		}
		a.current {
			color: red;
			font-weight: bold;
		}
	</style>
</head>

<body>
	<h3>/WEB-INF/views/board/getBoardList.jsp</h3>
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

				<c:forEach var="board" items="${_PAGE_.content}">
					<tr>
						<td>${board.seq}</td>
						<td><a href="getBoard?seq=${board.seq}&currPage=${_PAGE_.number + 1}">${board.title}</a></td>
						<td>${board.writer}</td>
						
						<%-- **Important: <fmt:formatDate/> tag cannot convert `LocalDateTime` as defined pattern --%>

						<td><fmt:formatDate value="${board.createDate}" pattern="yyyy/MM/dd" /></td>
						<td><fmt:formatDate value="${board.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						
						<td>${board.cnt}</td>
					</tr>
				</c:forEach>
		</table>
		<br>

		<ul class="pagination">
			<c:if test="${!_PAGE_.first}"><li><a href="/board/getBoardList?currPage=1">F</a></li></c:if>
			<c:if test="${_PAGE_.hasPrevious()}"><li><a href="/board/getBoardList?currPage=${_PAGE_.number + 1 - 1}">P</a></li></c:if>
			
			<c:forEach var="counter" begin="${1}" end="${_PAGE_.totalPages}" step="1">
				<li><a href="/board/getBoardList?currPage=${counter}" class='<c:if test="${_PAGE_.number + 1 == counter}">current</c:if>'>${counter}</a></li>
			</c:forEach>
			
			<c:if test="${_PAGE_.hasNext()}"><li><a href="/board/getBoardList?currPage=${_PAGE_.number + 1}">N</a></li></c:if>
			<c:if test="${!_PAGE_.last}"><li><a href="/board/getBoardList?currPage=${_PAGE_.totalPages}">L</a></li></c:if>
		</ul>
		<br>
		
        <a href="register?currPage=${_PAGE_.number + 1}">(1) REGISTER</a><br>
		<a href="registerWithDTO?currPage=${_PAGE_.number + 1}">(2) REGISTER with DTO</a>
	</div>
	
</body>
</html>
