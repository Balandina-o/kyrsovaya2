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
            <form class="container">
                <h2>Привет админ</h2>
                <div class="input-box input-box__one-column">
                    <label for="">Кадровая стоимость объекта (₽)</label>
                    <input type="number" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Налог на инвертариз. стоимости (₽)</label>
                    <input type="number" max="3" min="1" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Площадь объекта (м²)</label>
                    <input type="number" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Размер доли в проаве (1/* или 1)</label>
                    <input type="text" />
                </div>
                <div class="button-box">
                    <button type="button">Выход</button>
                    <button type="submit">Изменить</button>
                </div>
            </form>
        </main>

        <footer>
            <a href="">О разработчиках</a>
        </footer>
    </body>
</html>