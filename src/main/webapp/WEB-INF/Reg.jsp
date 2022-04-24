<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="./style/style.css" />
        <title>Регистрация</title>
    </head>
    <body>
        <header>
            <div class="titles">
                <h1 class="title">Налоговый калькулятор</h1>
                <h2 class="subtitle">
                    Расчет налога на имущество физических лиц
                </h2>
            </div>
            <img src="./resources/picture/usatu.png" alt="Логотип УГАТУ" />
        </header>

        <main>
            <form class="container" name="regForm" action="${pageContext.request.contextPath}/reg" method="GET">
                <h2>Регистрация</h2>
                
                <div class="input-box">
                    <label for="login">Логин</label>
                    <input type="text" name="login" />
                </div>
                <div class="input-box">
                    <label for="password">Пароль</label>
                    <input type="password" name="password" />
                </div>
                <div class="button-box">
                    <button name="exitButton" value="exitButton">Назад</button>
                    <button name="regButton" type="submit">Зарегистрироваться</button>
                </div>

                 <input type="hidden" name="messageReg" value="${messageReg}" />
                
            </form>
        </main>

        <footer>
            <a href="./info.html" target="_blank">О разработчиках</a>
        </footer>
    </body>
    
     <script defer>        
        window.onload = function() {
			if (document.querySelector("input[name='messageReg']").value != ""){
				alert(document.querySelector("input[name='messageReg']").value);
			}}
	</script> 
</html>