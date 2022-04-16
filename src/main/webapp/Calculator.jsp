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
			<label for="">Муницип. образование</label> <select name="regionIndex">
				<option value="10" ${regionIndex=="10"?"selected":""}> г.Уфа 02</option>
				<option value="20" ${regionIndex=="20"?"selected":""}> г.Казань 16</option>
				<option value="30" ${regionIndex=="30"?'selected':""}>г.Москва 77</option>
				<option value="40" ${regionIndex=="40"?'selected':""}>г.Горно-Алтайск 04</option>
			</select>
		</div>

		<div class="form-input" id="selectprop">
			<label for="">Тип недвижимости</label> <select name="propertyIndex"
				id="property">
				<%--Изменены кофф--%>
				<option value="0" ${propertyIndex=="0"?"selected":""}>Комната</option>
				<option value="1" ${propertyIndex=="1"?"selected":""}>Квартира</option>
				<option value="2" ${propertyIndex=="2"?"selected":""}>Жилой дом</option>
				<option value="3" ${propertyIndex=="3"?"selected":""}>Машино-место</option>
				<option value="4" ${propertyIndex=="4"?"selected":""}>Иное сдание / сооружение</option>
			</select>
		</div>

		<div class="form-input">
			<label for="">Кадровая стоимость объекта (₽)</label> <input
				type="text" name="kadastr" value="${kadastr}" />
		</div>

		<div class="form-input">
			<label for="">Налог на инвертариз. стоимости (₽)</label> <input
				type="text" name="tax" value="${tax}" />
		</div>

		<div class="form-input">
			<label for="">Площадь объекта (м²)</label> <input type="text"
				name="square" value="${square}" />
		</div>

		<div class="form-input">
			<label for="">Размер доли в проаве (1/* или 1)</label> <input
				type="text" name="part" value="${part}" />
		</div>

		<div class="form-input">
			<label for="">Переод владения (в мес. от 1 до 12)</label> <input
				type="text" min="1" max="12" name="period" value="${period}" />
		</div>

		<div class="form-input">
			<label for="">Число несовершеннолетних детей</label> <input
				name="childrens" type="text" value="${childrens}" />
		</div>

		<div class="form-input">
			<label for="">Введите размер льготы (%)</label>
			<input type="text" name="benefit" min="0" max="100"
				value="${benefit}" />
		</div>

		<div class="form-input">
			<label for="">Сумма к уплате:</label> <input type="text"
				name="result" value="${result}" />
		</div>

		<div class="form-input">
			<label for="">Warnings:</label> <input type="text" name="warnings"
				value="${warnings}" />
		</div>

		<div class="buttons">
			<button type="submit" name="button" value="pdfButton">Сгенерировать PDF-файл</button>
			<button type="submit" name="button" value="calcButton">Расчитать</button>
			<button type="submit" name="button" value="exitButton">Выйти из аккаунта</button>
		</div>
	</form>