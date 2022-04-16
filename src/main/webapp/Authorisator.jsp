<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Authorisator</title>
</head>
<body>

	<form name="authoForm" action="AuthoServlet" method="GET">
		<h3>ВХОД</h3>

		<div class="form-input">
			<label for="login">Логин</label> <input type="text" name="login"
				required />
		</div>

		<div class="form-input">
			<label for="password">Пароль</label> <input type="password"
				name="password" required />
		</div>

		<div class="buttons">
			<button type="submit" name="sign" value="sign">
				Зарегистрироваться</button>
			<button type="submit" name="sign" value="sign">Войти</button>
		</div>

		<div class="form-input">
			<span name="error">${error}</span>
		</div>

	</form>

</body>
</html>