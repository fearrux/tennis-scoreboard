<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<body>
<form action="/new-match" method="POST">
    First Player: <label>
    <input type="text" name="first_player">
</label>
    <br/>
    Second Player: <label>
    <input type="text" name="second_player"/>
</label>
    <br/>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>