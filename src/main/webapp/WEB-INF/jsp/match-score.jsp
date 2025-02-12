<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
        <td>${match.firstPlayer.name}</td>
        <td>${match.matchState.firstPlayerSets}</td>
        <td>${match.matchState.firstPlayerGames}</td>
        <td>${match.matchState.firstPlayerPoints}</td>
        <td>
            <form action="${pageContext.request.contextPath}/match-score" method="POST">
                <input type="hidden" name="player_id" value="${match.firstPlayer.id}">
                <input type="hidden" name="uuid" value="${match.uuid}">
                <input type="submit" value="Score">
            </form>
        </td>
    </tr>
    <tr>
        <td>${match.secondPlayer.name}</td>
        <td>${match.matchState.secondPlayerSets}</td>
        <td>${match.matchState.secondPlayerGames}</td>
        <td>${match.matchState.secondPlayerPoints}</td>
        <td>
            <form action="${pageContext.request.contextPath}/match-score" method="POST">
                <input type="hidden" name="player_id" value="${match.secondPlayer.id}">
                <input type="hidden" name="uuid" value="${match.uuid}">
                <input type="submit" value="Score">
            </form>
        </td>
    </tr>
</table>
</body>
</html>