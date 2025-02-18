<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <title>Tennis Scoreboard</title>
</head>
<body>
<div class="header">
    <a class="roadmap-link" href="https://zhukovsd.github.io/java-backend-learning-course/" target="_blank">Sergey
        Zhukov Roadmap</a>
    <div class="btn-container">
        <a href="${pageContext.request.contextPath}/tennis-scoreboard.jsp">
            <button class="btn">Home</button>
        </a>
        <a href="${pageContext.request.contextPath}/matches">
            <button class="btn">Matches</button>
        </a>
    </div>
</div>

<hr class="divider">

<div class="content">
    <h1>Current match</h1>
    <p class="manage-text">Control player's points and lead one player to win</p>
</div>

<table class="unique-table">
    <tr>
        <th>Player</th>
        <th>Sets</th>
        <th>Games</th>
        <th>Points</th>
        <th>Action</th>
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
                <input type="submit" class="unique-button" value="Score"
                       <c:if test="${requestScope.match.matchState.firstPlayerSets == 3 or requestScope.match.matchState.secondPlayerSets == 3}">disabled</c:if>
                >
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
                <input type="submit" class="unique-button" value="Score"
                       <c:if test="${requestScope.match.matchState.firstPlayerSets == 3 or requestScope.match.matchState.secondPlayerSets == 3}">disabled</c:if>
                >
            </form>
        </td>
        <c:if test="${requestScope.match.matchState.firstPlayerSets == 3 or requestScope.match.matchState.secondPlayerSets == 3}">
            <h2 class="footer">Match completed.</h2>
            <h3><a class="footer" style="text-align: center; display: block" href="${pageContext.request.contextPath}/new-match">Let's start new match!</a></h3>
        </c:if>
    </tr>
</table>
</body>
</html>
