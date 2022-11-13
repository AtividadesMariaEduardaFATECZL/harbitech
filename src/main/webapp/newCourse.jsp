<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Novo Curso</title>
</head>
<body>
<form>
    <fieldset>
        <legend>Novo Curso</legend>

        <p>
            <label>Nome:<input type="text" name="email" /></label>
        </p>

        <p>
            <label>Código: <input name="lastName" /></label>
        </p>

        <p>
            <label>Tempo finalização:<input type="number" name="email" /></label>
        </p>


        <p>
            <label>Instrutor:<input type="text" name="birthDate"></label>
        </p>

        <p>
            <label>Público alvo:<input type="text" name="birthDate"></label>
        </p>

        <p>
            <label>
                Visibilidade
                <br />
                <select>
                    <option>Público</option>
                    <option>Privado</option>
                </select>
            </label>
        </p>


        <p>
            <label>
                Ementa :
                <br />
                <textarea name="address" cols="30" rows="3"></textarea>
            </label>
        </p>

        <p>
            <label>
                Habilidades desenvolvidas :
                <br />
                <textarea name="address" cols="30" rows="3"></textarea>
            </label>
        </p>



        <p>
            <button type="submit">Salvar</button>
        </p>


    </fieldset>
</form>
</body>
</html>
