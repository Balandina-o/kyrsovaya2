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
    
    <jsp:scriptlet>
    System.out.println(request.getSession().getAttribute("role"));
    if(!request.getSession().getAttribute("role").equals("ADMIN")){ 
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Aut.jsp");
		requestDispatcher.forward(request, response);
		return;} 
    </jsp:scriptlet>
    
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
            <form class="container" name="adminform" action="AdminServlet" method="POST">
                <h2>Панель изменения коэффициентов</h2>
                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Уфе</label>
                     <input type="number" name="coeffUfa" value="${coeffUfa}" max="10" step="0.5" min="1"/>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Казани</label>
                    <input type="number" name="coeffKazan" value="${coeffKazan}" max="10" step="0.5" min="1"/>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Москве</label>
                    <input type="number" name="coeffMoscow" value="${coeffMoscow}" max="10" step="0.5" min="1"/>
                </div>

                <div class="input-box input-box__one-column">
                    <label for="">Региональный коэффициент для налоговой ставки в г. Горно-Алтайске</label>
                    <input type="number" name="coeffGorn" value="${coeffGorn}" max="10" step="0.5" min="1"/>
                </div>
                <div class="button-box">
                    <button name="exitButton" value="exitButton">Выход</button>
                    <button name="changeButton" type="submit" value="changeButton">Изменить и сохранить</button>
                </div>
            </form>
        </main>

        <footer>
            <a href="./info.html" target="_blank">О разработчиках</a>
        </footer>
    </body>
</html>