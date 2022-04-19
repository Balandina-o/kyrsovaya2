<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
</head>

<body>
<h3>Введите данные для расчёта</h3>
<form name="calcform" action="CalcServlet" method="POST">
<div class="form-input">
<label for="">Муницип. образование</label> <select name="town">
<option value="1" selected="selected">г.Уфа 02</option>
<option value="1">г.Казань 16</option>
<option value="1">г.Москва 77</option>
<option value="0.6">г.Горно-Алтайск 04</option>
</select>
</div>

<div class="form-input" id="selectprop">
<label for="">Тип недвижимости</label> <select name="property"
id="property">
<option value="10" selected="selected">Комната</option>
<option value="20">Квартира</option>
<option value="50">Жилой дом</option>
<option value="0">Машино-место</option>
<option value="0">Иное сдание / сооружение</option>
</select>
</div>

<div class="form-input">
<label for="">Кадровая стоимость объекта (₽)</label> <input
type="text" name="kadastr" value="${kadastr}"/>
</div>

<div class="form-input">
<label for="">Налог на инвертариз. стоимости (₽)</label> <input
type="text" name="tax" value="${tax}"/>
</div>

<div class="form-input">
<label for="">Площадь объекта (м²)</label> <input type="text" name="square" value="${square}"/>
</div>

<div class="form-input">
<label for="">Размер доли в проаве (1/* или 1)</label> <input
type="text" name="part" value="${part}"/>
</div>

<div class="form-input">
<label for="">Переод владения (в мес. от 1 до 12)</label> <input
type="text" min="1" max="12" name="period" value="${period}"/>
</div>

<div class="form-input">
<label for="">Число несовершеннолетних детей</label> <input
name="childrens" type="text" value="${childrens}"/>
</div>

<div class="form-input">
<label for="">Имеются ли у вас льготы?</label>
<div class="lgots">
<div class="radio">
<label for="">Да</label> <input type="radio" name="lgots" id="">
</div>
<div class="radio">
<label for="">Нет</label> <input type="radio" name="lgots" id="">
</div>
</div>
</div>

<div class="form-input">
<label for="">Введите размер льготы (%)</label> <input type="text"
name="benefit" min="0" max="100" value="${benefit}"/>
</div>

<div class="form-input">
<label for="">Сумма к уплате:</label> <input type="text"
name="result" value="${result}"/>
</div>

<div class="form-input">
<label for="">Warnings:</label> <input type="text"
name="warnings" value="${warnings}"/>
</div>

<div class="buttons">
<button>Сгенерировать PDF-файл</button>
<button class="enterb">Расчитать</button>
</div>
</form>