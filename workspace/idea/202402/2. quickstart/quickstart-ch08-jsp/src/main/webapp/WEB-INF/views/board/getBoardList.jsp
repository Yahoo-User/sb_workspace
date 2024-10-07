<%@page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 1st. method to get authentication from Spring Security --%>
<%@page import="org.zerock.myapp.security.SecurityUser" %>
<%@page import="org.springframework.security.core.Authentication" %>
<%@page import="org.springframework.security.core.context.SecurityContext" %>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>

<%-- 2nd. method to get authentication from Spring Security --%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>getLoadList</title>

    <style>
        a {
            text-decoration: none;
        }
    </style>
</head>

<%-- 1st. method to get authentication from Spring Security --%>
<%
    SecurityContext securityContext = SecurityContextHolder.getContext();

    /**
     * securityContext:
     *
     *      SecurityContextImpl [
     *          Authentication=UsernamePasswordAuthenticationToken
     *          [Principal=SecurityUser(member=Member(id=member, name=회원, password={bcrypt}$2a$10$4m2X1hEZhA3zcM0/F5pSH.EnfR8UJraSQiLePgo7e5cBs9RwPorX2,
     *                                                role=ROLE_MEMBER, enabled=true)),
     *           Credentials=[PROTECTED],
     *           Authenticated=true,
     *           Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1, SessionId=C9A02B70C8D71AA7F10BBDEB9610AB72],
     *           Granted Authorities=[ROLE_MEMBER]
     *          ]
     *      ]
     */

    Authentication auth = securityContext.getAuthentication();
    boolean isAuthenticated = auth.isAuthenticated();
    SecurityUser securityUser = (SecurityUser) auth.getPrincipal();

    /*
        out.println("1. securityContext: " + securityContext + "<br>");
        out.println("2. authentication: " + auth+"<br>");
        out.println("3. isAuthenticated: " + isAuthenticated+"<br>");
        out.println("4. securityUser: " + securityUser+"<br>");  // returns `SecurityUser` Object.
    */
%>

<body>
    <h4>/WEB-INF/views/board/getBoardList.jsp?pageNumber=${cri.pageNumber}</h4>
    <hr>

    <div style="width: 1100px; text-align: center;">
        
        <%-- 2nd. method to get authentication from Spring Security --%>
        <sec:authorize access="isAuthenticated()">
            <h3>- Board List (<span style="color: red;">Welcome Back, <sec:authentication property="name"/></span>) -</h3>
        </sec:authorize>

        <sec:authorize access="!isAuthenticated()">
            <h3>- Board List -</h3>
        </sec:authorize>

    </div>

    <div style="width: 1100px; text-align: right;">
        <a href="/board/insertBoard?pageNumber=${cri.pageNumber}"><button type="button">Register</button></a>
        <a href="/system/logout"><button type="button">Logout</button></a>
        <a href="/admin/adminPage"><button type="button">Board Management</button></a>
    </div>

    <table border="1" cellpadding="0" cellspacing="0" style="width: 1100px; margin-bottom: 10px; margin-top: 5px;">
        <tr>
            <th bgcolor="gold" align="center" width ="50">seq</th>
            <th bgcolor="gold" align="center" width="350">title</th>
            <th bgcolor="gold" align="center" width="150">writer</th>
            <th bgcolor="gold" align="center" width ="50">cnt</th>
            <th bgcolor="gold" align="center" width="250">insert_ts</th>
            <th bgcolor="gold" align="center" width="250">update_ts</th>
        </tr>

        <c:forEach var="board" items="${boardPage.content}">
            <tr>
                <td align="center">${board.seq}</td>
                <td align="left">&nbsp;<a href="/board/getBoard?pageNumber=${cri.pageNumber}&seq=${board.seq}">${board.title}</a></td>
                <td align="center">${board.member.id} (${board.member.name})</td>
                <td align="center">${board.cnt}</td>
                <td align="center">${board.insertTs}</td>
                <td align="center">${board.updateTs}</td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="6" style="color: purple; text-align: center;">>>> ${boardPage} (totalElements: ${cri.totalElements}) <<<</td>
        </tr>
    </table>

    <div style="width: 1100px; text-align: center; font-size: 18px; font-weight: bold;">
        <ul id="pageList">
            <c:if test="${cri.prevList}"><a href="/board/getBoardList?pageNumber=1">[First]</a>&nbsp;</c:if>
            <c:if test="${cri.prev}"><a href="/board/getBoardList?pageNumber=${cri.pageNumber-1}">Prev</a></c:if>

            <c:forEach var="index" begin="${cri.startPageNumber}" end="${cri.endPageNumber}">
                <c:if test="${cri.pageNumber == index}">
                    <li style="display: inline-block; width: 30px; background-color: aqua;">
                </c:if>

                <c:if test="${cri.pageNumber != index}">
                    <li style="display: inline-block; width: 30px;">
                </c:if>

                <a href="/board/getBoardList?pageNumber=${index}">${index}</a></li>
            </c:forEach>

            <c:if test="${cri.next}"><a href="/board/getBoardList?pageNumber=${cri.pageNumber+1}">Next</a></c:if>
            <c:if test="${cri.nextList}">&nbsp;<a href="/board/getBoardList?pageNumber=${cri.totalPages}">[Last]</a></c:if>
        </ul>
    </div>
</body>

</html>