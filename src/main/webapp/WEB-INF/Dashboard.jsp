<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="./style/style.css" />
        <title>Изменение коэффициентов</title>
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
            <form action="${pageContext.request.contextPath}/admin" class="container" name="adminform" method="POST">
                
                <h2>Панель изменения коэффициентов</h2>
                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Уфе</label>
                     <input id="coffU" type="number" name="coeffUfa" value="${coeffUfa}" max="10" step="0.1" min="1"/>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Казани</label>
                    <input id="coffK" type="number" name="coeffKazan" value="${coeffKazan}" max="10" step="0.1" min="1"/>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Москве</label>
                    <input id="coffM" type="number" name="coeffMoscow" value="${coeffMoscow}" max="10" step="0.1" min="1"/>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Горно-Алтайске</label>
                    <input id="coffG" type="number" name="coeffGorn" value="${coeffGorn}" max="10" step="0.1" min="1"/>
                </div>
                <div class="button-box">
                    <button name="exitButton" value="exitButton">Выход</button>
                    <button id="change" name="changeButton" type="submit" value="changeButton">Изменить и сохранить</button>
                    <button name="calcButton" value="calcButton">Перейти к калькулятору</button>
                </div>
            </form>
        </main>
		<input type="hidden" name="hidden" value="${hidden}" />
        <footer>
            <a href="./info.html" target="_blank">О разработчиках</a>
        </footer>
    </body>
    
    <script defer>        
        window.onload = function() {
			if (document.querySelector("input[name='hidden']").value != "noMessage"){	
					alert('Коэффициенты успешно изменены');
        	}
		}
		</script>            
</html>