<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nur
  Date: 04.11.16
  Time: 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Sign-in</title>

    <% if (session.getAttribute("user") != null) {
      response.sendRedirect("welcome"); //пользователь не авторизован
    } else {
      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
      response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
      response.setDateHeader("Expires", 0);
    }
    %>
  </head>
  <body>


  <c:choose>
    <c:when test="${warn != null}">
  <span style="color: orange; ">  ${warn} </span>
    </c:when>
    <c:when test="${logout != null}">
      <span style="color: green; ">  ${logout} </span>
    </c:when>
    <c:when test="${error != null}">
      <span style="color: red; ">  ${error} </span>
    </c:when>
    <c:otherwise>

    </c:otherwise>
  </c:choose>



  <br><form action="welcome" method="post">
    <fieldset style="width: 500px; border: none" >
      <table>
        <tr>
          <td align="left">Имя пользователя</td>
          <td align="left"><input type="text" name="username"/></td>
          <td align="right"><a href="sign-up">Регистрация</a></td>
        </tr>
        <tr>
          <td align="left">Пароль</td>
          <td align="left"> <input type="password" name="password"/> </td>
        </tr>
        <tr>
          <td></td>
          <td><input type="submit" value="Войти"></td>
          <td></td>
        </tr>

      </table>


    </fieldset>
  </form>

  </body>
</html>
