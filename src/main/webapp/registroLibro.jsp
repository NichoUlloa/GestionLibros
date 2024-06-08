<%--
  Created by IntelliJ IDEA.
  User: Nicho
  Date: 04-06-2024
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="estilos.css">
    <title>Registro libro</title>
</head>
<body>
<h1 class="encabezado"> Registro libro</h1>
<form action="registroLibro" method="post">
    <div class="centrado">
        <label> Nombre:</label>
        <input name="nombre" type="text" class="campoTexto">
        <label> Autor:</label>
        <input name="autor" type="text" class="campoTexto">
        <label> Categoria:</label>
        <input name="categoria" type="text" class="campoTexto">
        <br><br>
        <input type="submit" value="enviar" class="boton">
    </div>
</form>
</body>
</html>

