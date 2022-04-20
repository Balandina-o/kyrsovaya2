<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="./style.css" />
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
            <form class="container">
                <h2>Введите данные для расчёта</h2>

                <div class="input-box input-box__one-column">
                    <label for="">Муницип. образование</label>
                    <select>
                        <option>г. Уфа 02</option>
                        <option>г. Казань 16</option>
                        <option>г. Москва 77</option>
                        <option>г. Горно-Алтайск 04</option>
                    </select>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Тип недвижимости</label>
                    <select>
                        <option>Комната</option>
                        <option>Квартира</option>
                        <option>Жилой дом</option>
                        <option>Машино-место</option>
                        <option>Иное сдание / сооружение</option>
                    </select>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Кадровая стоимость объекта (₽)</label>
                    <input type="number" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Налог на инвертариз. стоимости (₽)</label>
                    <input type="number" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Площадь объекта (м²)</label>
                    <input type="number" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Размер доли в проаве (1/* или 1)</label>
                    <input type="text" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Переод владения (в мес. от 1 до 12)</label>
                    <input type="number" min="1" max="12" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Число несовершеннолетних детей</label>
                    <input type="number" />
                </div>

                

                <div class="input-box input-box__one-column">
                    <label for="">Введите размер льготы (%)</label>
                    <input type="number" min="0" max="100" />
                </div>

                <div class="input-box">
                    <label for="">Сумма к уплате:</label>
                    <span> ₽</span>
                </div>

                <div class="button-box">
                    <button type="button">Сгенерировать PDF-файл</button>
                    <button type="submit">Расчитать</button>
                </div>
            </form>
        </main>

        <footer>
            <a href="">О разработчиках</a>
            </footer>>
            </body>>
            </html>>