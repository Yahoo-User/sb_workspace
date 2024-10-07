<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Denied</title>
</head>

<body>
    <h3>/WEB-INF/views/system/accessDenied.jsp</h3>
    <hr>
    
    <h3>* Access Denied *</h3>

    <p>죄송합니다. 페이지에 대한 접근권한이 없습니다.</p>
    <p style="color: red; font-weight: bold;">Message: ${SPRING_SECURITY_403_EXCEPTION.message}</p>
    <p>다시 로그인을 원하시면, <a href="/system/login" style="text-decoration: none;">여기를 누르세요.</a></p>
</body>
</html>


