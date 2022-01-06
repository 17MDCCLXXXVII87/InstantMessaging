<html>
<body>
<p style="color:red;">${errorMessage}</p>
<form method="POST" action="${pageContext.request.contextPath}/login">
	<input type="email">
	<input type="password">
	<input type="submit" value="Submit">
</form>
</body>
</html>
