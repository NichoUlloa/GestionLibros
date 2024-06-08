<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 04-06-2024
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="estilos.css">
    <title>Registro categoria</title>
</head>
<body>
<h1 class="encabezado"> Registro categoria</h1>
<form action="registroCategoria" method="post">
    <div class="centrado">
        <label> Nombre:</label>
        <input name="nombre" type="text" class="campoTexto">
        <br><br>
        <input type="submit" value="enviar" class="boton">
    </div>
</form>
</body>
</html>
