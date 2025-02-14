<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
<table>
    <tr>
        <td>Player</td>
        <td>Sets</td>
        <td>Games</td>
        <td>Points</td>
    </tr>
    <tr>
        <td><i class="fa-solid fa-user"></i> ${requestScope.match.firstPlayer.name}</td>
        <td>${requestScope.match.matchState.firstPlayerSets}</td>
        <td>${requestScope.match.matchState.firstPlayerGames}</td>
        <td>
            <c:choose>
                <%--Tie-break--%>
                <c:when test="${
                        requestScope.match.matchState.secondPlayerGames == 6 &&
                        requestScope.match.matchState.firstPlayerGames == 6
                }">${requestScope.match.matchState.firstPlayerPoints}</c:when>

                <%--Other--%>
                <c:when test="${requestScope.match.matchState.firstPlayerPoints == 0}">0</c:when>
                <c:when test="${requestScope.match.matchState.firstPlayerPoints == 1}">15</c:when>
                <c:when test="${requestScope.match.matchState.firstPlayerPoints == 2}">30</c:when>
                <c:when test="${requestScope.match.matchState.firstPlayerPoints == 3}">40</c:when>

                <%--Advantage--%>
                <c:when test="${
                        requestScope.match.matchState.firstPlayerPoints >= 4 &&
                        requestScope.match.matchState.firstPlayerPoints == requestScope.match.matchState.secondPlayerPoints + 1
                }">AD</c:when>
                <c:otherwise>40</c:otherwise>
            </c:choose>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/match-score" method="POST">
                <input type="hidden" name="player_id" value="${requestScope.match.firstPlayer.id}">
                <input type="hidden" name="uuid" value="${requestScope.match.uuid}">
                <input type="submit" value="Score">
            </form>
        </td>
    </tr>
    <tr>
        <td><i class="fa-solid fa-user"></i> ${requestScope.match.secondPlayer.name}</td>
        <td>${requestScope.match.matchState.secondPlayerSets}</td>
        <td>${requestScope.match.matchState.secondPlayerGames}</td>
        <td>
            <c:choose>
                <%--Tie-break--%>
                <c:when test="${
                        requestScope.match.matchState.secondPlayerGames == 6 &&
                        requestScope.match.matchState.firstPlayerGames == 6
                }">${requestScope.match.matchState.secondPlayerPoints}</c:when>

                <%--Other--%>
                <c:when test="${requestScope.match.matchState.secondPlayerPoints == 0}">0</c:when>
                <c:when test="${requestScope.match.matchState.secondPlayerPoints == 1}">15</c:when>
                <c:when test="${requestScope.match.matchState.secondPlayerPoints == 2}">30</c:when>
                <c:when test="${requestScope.match.matchState.secondPlayerPoints == 3}">40</c:when>

                <%--Advantage--%>
                <c:when test="${
                        requestScope.match.matchState.secondPlayerPoints >= 4 &&
                        requestScope.match.matchState.secondPlayerPoints == requestScope.match.matchState.firstPlayerPoints + 1
                }">AD</c:when>
                <c:otherwise>40</c:otherwise>
            </c:choose>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/match-score" method="POST">
                <input type="hidden" name="player_id" value="${requestScope.match.secondPlayer.id}">
                <input type="hidden" name="uuid" value="${requestScope.match.uuid}">
                <input type="submit" value="Score">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
