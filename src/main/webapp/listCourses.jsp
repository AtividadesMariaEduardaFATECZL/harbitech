<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Lista todos os cursos</title>
</head>
<body>
    <center><h1>Lista de cursos</h1> <br/></center>
    <a href="/harbitech/formNewCategory.jsp">Cadastrar um novo curso</a><br><br>
    <table border=1 frame=void rules=rows>
        <tr align=center>
            <th scope="row">Nome</th>
            <th scope="row">Código da url</th>
            <th scope="row"></th>
            <th scope="row"></th>
        </tr>
        <tr>
            <c:forEach items="${courses}" var="course">
        <tr>
            <td scope="row">${course.name}<td/>
            <td scope="row">${course.codeUrl}<td/>
            <a href="/harbitech/editaCategoria?id=">edita</a><br><br>
            <a href="/harbitech/inativaCategoria?codeUrl=">Excluir curso</a>
        </tr>
        </c:forEach>
        </td>
        <br>
        <br>

        </tr>
    </table>
</body>
</html>