<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- @ taglib uri="jakarta.tags.core" prefix="c" --%>
<%-- @ taglib uri="jakarta.tags.fmt" prefix="fmt" --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Test Request Scope Bean</title>
</head>

<body>
	<h3>/WEB-INF/views/scope/request.jsp</h3>
	<hr>
	
	<ul style="font-size: 16px; font-weight: bold;">
		<li>Message: ${message}</li>
		<li>RequestScopeBean: ${requestScopeBean}</li>
	</ul>
</body>
</html>

