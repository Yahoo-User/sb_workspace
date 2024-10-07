<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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

