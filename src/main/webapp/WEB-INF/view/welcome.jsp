<%@ page import="ru.innopolis.nur.model.UserBean" %>
<%--
  Created by IntelliJ IDEA.
  User: nur
  Date: 04.11.16
  Time: 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <% if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("sign-in"); // No logged-in user found, so redirect to login page.
    } else {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
    }
    %>

    <script>
        function myFunction() {

            var date = new Date();
            var greeting = "Доброго ";
            if(date.getHours()>=6 && date.getHours()<10) {
                greeting="Доброе утро, ";
            }
            else if (date.getHours()>=10 && date.getHours()<18) {
                greeting = "Добрый день, ";
            }
            else if (date.getHours()>=10 && date.getHours()<18) {
                greeting="Добрый вечер, ";
            }
            else {
                greeting="Доброй ночи, ";
            }
            greeting=greeting+name;
            document.getElementById("Welcome").innerHTML = greeting;
        }
        window.onload = myFunction;
    </script>

    <title>Welcome</title>
</head>
<body>

    <p id="Welcome"></p>
${user.userName}

<br><br><br>

<a href="logout" > Выйти </a>
</body>
</html>