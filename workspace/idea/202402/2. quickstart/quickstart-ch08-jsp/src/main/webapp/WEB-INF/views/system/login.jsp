<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <h3>/WEB-INF/views/system/login.jsp</h3>
    <hr>

    <c:url var="loginProcessingUrl" value="/system/login" />

    <form action="${loginProcessingUrl}" method="post">
        <fieldset>

            <legend>Please Login</legend>
    
            <!-- use `param.error` assuming `FormLoginConfigurer#failureUrl` contains the query parameter error -->
            <c:if test="${param.error != null}">
                <div style="color: red; font-weight: bold;">
                    Failed to login.<br>

                    <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                    </c:if>
                </div>
            </c:if>
            
            <!-- the configured `LogoutConfigurer#logoutSuccessUrl` is `/login?logout` and contains the query param `logout` -->
            <c:if test="${param.logout != null}">
                <div>
                    You have been logged out.
                </div>
            </c:if>
            
            <p>
                <label for="username">Username</label>
                <input type="text" id="username" name="username"/>
            </p>
            
            <p>
                <label for="password">Password</label>
                <input type="password" id="password" name="password"/>
            </p>
            
            <!-- if using `RememberMeConfigurer` make sure `remember-me` matches `RememberMeConfigurer#rememberMeParameter` -->
            <p>
                <label for="remember-me">Remember Me?</label>
                <input type="checkbox" id="remember-me" name="remember-me"/>
            </p>
            
            <div>
                <button type="submit" class="btn">Sign-In</button>
            </div>

        </fieldset>
    </form>
</body>
</html>


