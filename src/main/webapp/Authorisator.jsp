<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="./style.css" />
<title>Authorisator</title>
</head>
<body>

<form name="authoForm" action="AuthoServlet" method="GET">
<h3>ВХОД</h3>

<div class="form-input">
<label for="login">Логин</label>
<input type="text" name="login" />
</div>

<div class="form-input">
<label for="password">Пароль</label>
<input type="password" name="password" />
</div>

<div class="buttons">
<button type="submit" name="sign" value="sign"> Зарегистрироваться </button>
<button type="submit" name="sign" value="sign"> Войти </button>
</div>

<div class="form-input">
<input type="" name="error" value="${error}"/>
</div>

</form>

</body>
</html>
