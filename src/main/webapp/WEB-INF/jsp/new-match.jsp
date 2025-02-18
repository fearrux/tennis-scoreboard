<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <title>Tennis Scoreboard</title>
</head>
<body>
<div class="header">
    <a class="roadmap-link" href="https://zhukovsd.github.io/java-backend-learning-course/" target="_blank">Sergey Zhukov Roadmap</a>
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
    <h1>Start new match</h1>
    <p class="manage-text">Enter name of players and start game</p>
    <img src="${pageContext.request.contextPath}/images/new-match.jpeg" class="new-match-image" alt="tennis-scoreboard.image">
</div>

<div class="error-container">
    <c:if test="${not empty sessionScope.error}">
        <div class="error-message">
                ${sessionScope.error}
        </div>
        <%
            session.removeAttribute("error");
        %>
    </c:if>
</div>

<form action="${pageContext.request.contextPath}/new-match" method="POST">
    <div class="player_forms">
        <label for="first_player"><b>First player</b></label><br />
        <input type="text" placeholder="Enter first player name" name="first_player" id="first_player" required><br />

        <label for="second_player"><b>Second player</b></label><br />
        <input type="text" placeholder="Enter second player name" name="second_player" id="second_player" required><br />

        <button type="submit" class="submit-button">Submit</button>
    </div>

</form>
</body>
</html>
