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
            <img src="./picture/usatu.png" alt="Логотип УГАТУ" />
        </header>

        <main>
            <form class="container" name="authoForm" action="AuthoServlet" method="GET">
                <h2>Вход</h2>
                <div class="input-box">
                    <label for="login">Логин</label>
                    <input type="text" name="login" required/>
                </div>
                <div class="input-box">
                    <label for="password">Пароль</label>
                    <input type="password" name="password" required/>
                </div>
                <div class="button-box">
                    <button type="button">Зарегистрироваться</button>
                    <button type="submit">Войти</button>
                </div>
            </form>
        </main>

        <footer>
            <a href="">О разработчиках</a>
        </footer>
    </body>
</html>