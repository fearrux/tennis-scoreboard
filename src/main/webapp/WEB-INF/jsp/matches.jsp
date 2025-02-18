<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <title>Matches</title>
    <style>
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 10px;
            font-family: "JetBrains Mono", monospace;
        }

        th, td {
            border: 2px solid black;
            padding: 15px;
            text-align: left;
            font-size: 18px;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        .filter-form {
            margin: 20px 0;
            text-align: center;
        }

        #playerNameInput {
            padding: 10px;
            font-size: 18px;
            border: 2px solid #ccc;
            border-radius: 5px;
            width: 300px;
            transition: border-color 0.3s;
        }

        #playerNameInput:focus {
            border-color: #888;
            outline: none;
        }

        .reset {
            border: none;
        }

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination a {
            margin: 0 5px;
            padding: 10px 15px;
            border: 1px solid #ccc;
            text-decoration: none;
            color: black;
            border-radius: 5px;
        }

        .pagination a.current {
            background-color: #007bff;
            color: white;
        }

        .pagination a:hover {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<div class="header">
    <a class="roadmap-link" href="https://zhukovsd.github.io/java-backend-learning-course/" target="_blank">Sergey Zhukov Roadmap</a>
    <div class="btn-container">
        <a href="${pageContext.request.contextPath}/tennis-scoreboard.jsp">
            <button class="btn">Home</button>
        </a>
        <button class="btn">Matches</button>
    </div>
</div>

<hr class="divider">

<div class="content">
<h1>Completed matches</h1>

<p class="manage-text">You can see all completed matches</p>
<form action="${pageContext.request.contextPath}/matches" method="GET">
    <div class="filter-form">
        <label for="playerNameInput"></label>
        <input type="text" id="playerNameInput" name="filter_by_player_name" placeholder="Enter player name" onchange="this.form.submit()">
    </div>
</form>

<form action="${pageContext.request.contextPath}/matches" method="GET">
    <div class="reset">
        <button type="submit">Reset Filter</button>
    </div>
</form>

<c:choose>
    <c:when test="${requestScope.matches.size() == 0}">
        <h1>Ooops...Matches not found</h1>
    </c:when>
    <c:otherwise>
        <table>
        <thead>
        <tr>
        <th>First player</th>
        <th>Second player</th>
        <th>Winner</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="match" items="${requestScope.matches}">
            <tr>
                <td>${match.firstPlayerName}</td>
                <td>${match.secondPlayerName}</td>
                <td>${match.winner}</td>
            </tr>
        </c:forEach>
        </tbody>
        </table>

        <div class="pagination">
        <c:if test="${requestScope.currentPage > 1}">
            <a href="${pageContext.request.contextPath}/matches?page=${requestScope.currentPage - 1}"> < </a>
        </c:if>
            <c:forEach begin="1" end="${requestScope.pages}" var="page" varStatus="loop">
                <a href="${pageContext.request.contextPath}/matches?page=${loop.index}"
                   class="${requestScope.currentPage == loop.index ? 'current' : ''}">
                        ${loop.index}
                </a>
            </c:forEach>
            <c:if test="${requestScope.currentPage < requestScope.pages}">
                <a href="${pageContext.request.contextPath}/matches?page=${requestScope.currentPage + 1}"> > </a>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>
</div>

</body>
</html>
