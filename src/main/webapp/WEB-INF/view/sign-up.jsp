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


    <title>Sign-up</title>

    <script>
        function checkForm(form)
        {
            if(form.username.value.length<5) {
                alert("Ошибка, Имя пользователя должно быть длиннее 4 символов и состоять из цифр и букв английского алфавита");
                form.username.focus();
                return false;
            }
            if(form.username.value == "") {
                alert("Ошибка, Имя пользователя должно быть длиннее 4 символов и состоять из цифр и букв английского алфавита");
                form.username.focus();
                return false;
            }
            re = /^\w+$/;
            if(!re.test(form.username.value)) {
                alert("Ошибка: Имя пользователя должно быть длиннее 4 символов и состоять из цифр и букв английского алфавита");
                form.username.focus();
                return false;
            }

            if(form.password.value != "" && form.password.value == form.confirm_password.value) {
                if(form.password.value.length < 8) {
                    alert("Ошибка: Пароль и повтор пароля не совпадают");
                    form.password.focus();
                    return false;
                }
                if(form.password.value == form.username.value) {
                    alert("Ошибка: пароль и имя пользователя должны быть разными!");
                    form.password.focus();
                    return false;
                }
                re = /[0-9]/;
                if(!re.test(form.password.value)) {
                    alert("Ошибка: Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов");
                    form.password.focus();
                    return false;
                }
                re = /[a-z]/;
                if(!re.test(form.password.value)) {
                    alert("Ошибка: Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов");
                    form.password.focus();
                    return false;
                }
                re = /[A-Z]/;
                if(!re.test(form.password.value)) {
                    alert("Ошибка: Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов");
                    form.password.focus();
                    return false;
                }
            } else {
                alert("Ошибка: пожалуйста, проверьте, что вы набрали пароль");
                form.password.focus();
                return false;
            }

            return true;
        }

    </script>
</head>
<body>

<form action="sign-up"  method="post" onsubmit="return checkForm(this);">
    <fieldset style="width: 500px; border: none" >
        <table>
            <tr>
                <td align="left">Имя пользователя</td>
                <td align="left"><input type="text" id="username" name="username" required ></td>
            </tr>
            <tr>
                <td align="left">Пароль</td>
                <td align="left"><input type="password"  id="password" name="password" required ></td>
            </tr>

            <tr>
                <td align="left">Повтор пароля</td>
                <td align="left"> <input type="password"  id="confirm_password" name="confirm_password" required ></td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" id="mySubmit" value="Зарегистрироваться"  ></td>
                <td> </td>
            </tr>

        </table>
    </fieldset>
</form>

</body>
</html>
