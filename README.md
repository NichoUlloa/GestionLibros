# Tarea - Caso Gestión de Libros

## Descripción

Este proyecto consiste en la creación de una aplicación web para la gestión de libros en la Universidad de La Frontera. La aplicación permitirá realizar diversas operaciones relacionadas con los libros y sus categorías.

## Funcionalidades

La aplicación debe permitir las siguientes funcionalidades:

1. **Agregar Categorías de Libros:**
    - Permitir al usuario agregar nuevas categorías de libros.
    - Ejemplos de categorías: Científico, Literario, Biografía, Monografía, etc.

2. **Agregar Libros:**
    - Permitir al usuario agregar nuevos libros con la siguiente información:
        - Nombre
        - Editorial
        - Categoría
        - Año
        - Tipo de libro (físico o digital)

3. **Buscar y Mostrar Libros:**
    - Permitir al usuario buscar y mostrar libros según:
        - Nombre
        - Categoría
        - Año

4. **Dar de Baja un Libro:**
    - Permitir al usuario dar de baja un libro de la base de datos.

5. **Conexión con Base de Datos:**
    - Almacenar las categorías y los libros en una base de datos.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes y clases:


- **controller**: Contiene los servlets para manejar las solicitudes HTTP relacionadas con el registro de categorías y libros.
    - `RegistroCategoriaServlet.java`
    - `RegistroLibroServlet.java`

- **model.data.dao**: Contiene las clases de acceso a datos (DAO) para interactuar con la base de datos.
    - `CategoriaLibroDAO.java`
    - `LibroDAO.java`
    - `DBConnector.java`

- **model.data**: Contiene las clases de modelo para las entidades del sistema.
    - `DBGenerator.java`: Clase para generar la base de datos.
    - `CategoriaLibro.java`: Clase que representa una categoría de libro.
    - `Libro.java`: Clase que representa un libro.
    - `TipoLibro.java`: Clase que representa el tipo de libro (físico o digital).
