<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/atualiza-curso" var="linkServletUpdateCourse"/>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Novo Curso</title>
</head>
<body>
<form action="${linkServletUpdateCourse}" method="post">
    <fieldset>
        <legend>Editar Curso</legend>

        <input type="hidden" name="id" value="${course.id}">

        <p>
            <label>Nome:<input type="text" name="name" value="${course.name}"/></label>
        </p>

        <p>
            <label>Código: <input name="codeUrl" value="${course.codeUrl}"/></label>
        </p>

        <p>
            <label>Tempo finalização:<input type="number" name="completionTimeInHours" value="${course.completionTimeInHours}"/></label>
        </p>

        <p>
            <label>Instrutor:<input type="text" name="instructor" value="${course.instructor}"></label>
        </p>

        <div class="form-check">
            <label for="flexRadioDefault1" class="col-sm-2 control-label">Visibilidade</label>
            <div class="col-sm-2">
                <input class="form-check-input" type="radio" name="visibility" id="flexRadioDefault1"
                       value="PUBLIC" ${course.visibility == 'PUBLIC' ? "checked" : ""} />
                PÚBLICO
            </div>
            <div>
                <input class="form-check-input" type="radio" name="visibility" id="flexRadioDefault2"
                       value="PRIVATE" ${course.visibility == 'PRIVATE' ? "checked" : ""} />
                PRIVADO
            </div>
        </div>


        <p>
            <label>
                Ementa:
                <br />
                <textarea name="description" cols="30" rows="3">${course.description}</textarea>
            </label>
        </p>

        <p>
            <label>
                Habilidades desenvolvidas :
                <br />
                <textarea name="developedskills" cols="30" rows="3">${course.developedSkills}</textarea>
            </label>
        </p>

        <p>
            <label>
                Público alvo:
                <br />
                <textarea name="targetAudience">value="${course.targetAudience}</textarea>
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
            </select>
        </p>
    </fieldset>
</form>
</body>
</html>