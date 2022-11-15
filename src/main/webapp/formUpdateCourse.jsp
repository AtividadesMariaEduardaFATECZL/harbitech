<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/cria-curso" var="linkServletNovoCurso"/>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Novo Curso</title>
</head>
<body>
<form action="${linkServletNovoCurso}" method="post">
    <fieldset>
        <legend>Novo Curso</legend>
        <p>
            <label>Nome:<input type="text" name="name"/></label>
        </p>

        <p>
            <label>Código: <input name="codeUrl" /></label>
        </p>

        <p>
            <label>Tempo finalização:<input type="number" name="completionTimeInHours"/></label>
        </p>

        <p>
            <label>Instrutor:<input type="text" name="instructor"></label>
        </p>

        <p>
            <label>Público alvo:<input type="text" name="targetAudience"></label>
        </p>

        <p>
            <label>
                Visibilidade
                <br />
                <select name="visibility">
                    <option>PÚBLICA</option>
                    <option>PRIVADA</option>
                </select>
            </label>
        </p>


        <p>
            <label>
                Ementa:
                <br />
                <textarea name="description" cols="30" rows="3"></textarea>
            </label>
        </p>

        <p>
            <label>
                Habilidades desenvolvidas :
                <br />
                <textarea name="developedskills" cols="30" rows="3"></textarea>
            </label>
        </p>

        <p>
            <button type="submit">Salvar</button>
        </p>

        <p>
            <label for="idSubCategory">Subcategoria:</label>
            <select name="idSubCategory" id="idSubCategory">
                <option disabled selected>Selecione uma subcategoria</option>
                <c:forEach var="subcategoryCode" items="${subcategoriesCodes}">
                <option>${subcategoryCode}</option>
                </c:forEach>
        </p>

    </fieldset>
</form>
</body>
</html>