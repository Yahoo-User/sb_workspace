<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- @ taglib uri="jakarta.tags.core" prefix="c" --%>
<%-- @ taglib uri="jakarta.tags.fmt" prefix="fmt" --%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<h3>/WEB-INF/views/error.jsp</h3>
<hr>

<h3>* Error Details *</h3>

<ol>
    <li>Timestamp: ${timestamp}</li>
    <li>Status: ${status}</li>
    <li>Error: ${error}</li>
    <li>Message: ${message}</li>
    <li>Path: ${path}</li>
    <li>trace: ${trace}</li>
</ol>

