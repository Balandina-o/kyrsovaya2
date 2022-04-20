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
            <form class="container" name="adminform" action="AdminServlet" method="POST">
                <h2>Панель изменения коэффициентов</h2>
                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Уфе</label>
                    <input type="number" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Казани</label>
                    <input type="number" max="3" min="1" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Москве</label>
                    <input type="number" />
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Горно-Алтайске</label>
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