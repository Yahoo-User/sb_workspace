<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>getBoardList.jsp</title>

	<style>

		table {
			width: 980px;
			border: 3 solid purple;
		}

		th {
			border-width: 1px;
			text-align: center;
			background-color: purple;
			color: white;
		}

		td {
			text-align: center;
		}

	</style>
</head>
<body>
	<h2>/WEB-INF/views/getBoardList.jsp</h2>
	
	<hr>
	
	<table border=1 cellpadding="0" cellspacing="0">
		<tr>
			<th width="50">Seq</th>
			<th width="200">Title</th>
			<th width="100">Writer</th>
			<th width="100">Content</th>
			<th width="200">CreateDate</th>
			<th width="50">Cnt</th>
		</tr>

		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.seq}</td>
				<td>${board.title}</td>
				<td>${board.writer}</td>
				<td>${board.content}</td>
				<td><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd HH:mm:ss.SSS"></fmt:formatDate></td>
				<td>${board.cnt}</td>
			</tr>
		</c:forEach>
	</table>

	<p></p>

	<a href="insertBoard">새글등록</a>
	
</body>
</html>