<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
            <button class="btn">Home</button>
            <a href="${pageContext.request.contextPath}/matches">
                <button class="btn">Matches</button>
            </a>
        </div>
    </div>

    <hr class="divider">

    <div class="content">
        <h1>Welcome to Tennis Scoreboard</h1>
        <p class="manage-text">Manage your tennis matches, record results and track ranking</p>
        <div class="image-container">
            <img src="${pageContext.request.contextPath}/images/tennis.png" class="tennis-image" alt="tennis-scoreboard.image">
        </div>
    </div>

    <div class="footer">
        <a href="${pageContext.request.contextPath}/new-match">
            <button class="button">Start a new match</button>
        </a>
        <a href="${pageContext.request.contextPath}/matches">
            <button class="button">View match results</button>
        </a>
    </div>
</body>
</html>
