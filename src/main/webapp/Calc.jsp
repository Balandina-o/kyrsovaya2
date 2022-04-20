<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="./style/style.css" />
        <title>Document</title>
    </head>
    <body>
        <header>
            <div class="titles">
                <h1 class="title">Налоговый калькулятор</h1>
                <h2 class="subtitle">
                    Расчет налога на имущество физических лиц
                </h2>
            </div>
            <img src="./img/logo.png" alt="Логотип УГАТУ" />
        </header>

        <main>
            <form class="container" name="calcform" action="CalcServlet" method="POST">
                <h2>Введите данные для расчёта</h2>

                <div class="input-box input-box__one-column">
                    <label for="">Муницип. образование</label>
                    <select name="regionIndex">
                        <option value="10" ${regionIndex=="10"?"selected":""}> г.Уфа 02</option>
						<option value="20" ${regionIndex=="20"?"selected":""}> г.Казань 16</option>
						<option value="30" ${regionIndex=="30"?'selected':""}>г.Москва 77</option>
						<option value="40" ${regionIndex=="40"?'selected':""}>г.Горно-Алтайск 04</option>
                    </select>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Тип недвижимости</label>
                    <select name="propertyIndex">
                        <%--Изменены кофф--%>
						<option value="0" ${propertyIndex=="0"?"selected":""}>Комната</option>
						<option value="1" ${propertyIndex=="1"?"selected":""}>Квартира</option>
						<option value="2" ${propertyIndex=="2"?"selected":""}>Жилой дом</option>
						<option value="3" ${propertyIndex=="3"?"selected":""}>Машино-место</option>
						<option value="4" ${propertyIndex=="4"?"selected":""}>Иное сдание / сооружение</option>
                    </select>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Кадровая стоимость объекта (₽)</label>
                    <input type="number" name="kadastr" value="${kadastr}" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Налог на инвертариз. стоимости (₽)</label>
					<input type="number" name="tax" value="${tax}" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Площадь объекта (м²)</label>
                    <input type="number" name="square" value="${square}" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Размер доли в праве (1/* или 1)</label>
                    <input type="text" name="part" value="${part}" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Переод владения (в мес. от 1 до 12)</label>
                    <input type="number" min="1" max="12" name="period" value="${period}" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Число несовершеннолетних детей</label>
                    <input type="number" name="childrens" value="${childrens}" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Введите размер льготы (%)</label>
                   	<input type="number" name="benefit" min="0" max="100" value="${benefit}" />
                </div>

                <div class="input-box">
                    <label for="">Сумма к уплате:</label>
                    <input type="text" name="result" value="${result}" />
                </div>

                <div class="button-box">
                    <button name="pdfButton" value="pdfButton">Сгенерировать PDF-файл</button>
					<button name="exitButton" value="exitButton">Выйти из аккаунта</button>
					<button type="submit" name="button" value="calcButton">Расчитать</button>
                </div>
            </form>
        </main>

        <footer>
            <a href="">О разработчиках</a>
            </footer>
            </body>
            </html>