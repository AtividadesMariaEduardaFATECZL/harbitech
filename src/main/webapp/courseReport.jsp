<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Relatório de cursos</title>
</head>
<body>
  <center><h1>Relatório de cursos que tem visibilidade pública e estão vínculados a uma categoria/subcategoria</h1> <br/></center>
  <a href="/harbitech/editaCategoria?id=">Ver todos os cursos</a><br><br>
  <table border=1 frame=void rules=rows>
    <tr align=center>
      <th scope="row">Id do curso</th>
      <th scope="row">Nome do curso</th>
      <th scope="row">Total de horas do curso</th>
      <th scope="row">Subcategoria vinculada</th>
      <th scope="row">Nome da Subcategoria vinculada</th>
    </tr>
    <tr>
      <c:forEach items="${courses}" var="course">
        <tr>
          <td scope="row">${course.id}<td/>
          <td scope="row">${course.name}<td/>
          <td scope="row">${course.completionTimeInHours}<td/>
          <td scope="row">${course.subcategoryDto.id}<td/>
          <td scope="row">${course.subcategoryDto.name}<td/>
        </tr>
      </c:forEach>
    </td>
    <br>
    <br>

    </tr>
  </table>
</body>
</html>