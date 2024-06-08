package com.gestion.gestionlibros.model.data;

import org.jooq.DSLContext;
import org.jooq.DataType;
import org.jooq.impl.DSL;
import java.sql.Connection;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;
public class DBGenerator {
    // metodo inicial para crear una base de datos y sus respectivas tablas en caso de que no exista
    // ** este apartado es necesario modificarlo
    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaLibro(create);
        relacionarTabla(create, "Libro", "id", "Categoria");
        DBConnector.closeConnection();
    }

    // metodo para conectarse a una base de datos ya creada
    public static DSLContext conectarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombreBD, "root", "");
        DSLContext create = DSL.using(connection);
        return create;
    }

    // crea una base de datos en caso de que no exista
    private static void crearBaseDato(DSLContext create, String nombreBD) {
        create.createDatabaseIfNotExists(nombreBD).execute();
    }

    // actualiza la conexion inicial para conectar a la base de datos
    private static DSLContext actualizarConexion(Connection connection, String nombreBD) {
        DBConnector.closeConnection();
        connection = DBConnector.connection(nombreBD, "root", "");
        DSLContext create = DSL.using(connection);
        return create;
    }

    // metodo estandar para crear una tabla libro      // ISBN String, nombre String, editorial String, categoriaLibro CategoriaLibro, anio int, tipoLibro TipoLibro
    private static void crearTablaLibro(DSLContext create) {
        create.createTableIfNotExists("Libro").column("ISBN", VARCHAR(50))
                .column("nombre", VARCHAR(100))
                .column("editorial", VARCHAR(100))
                .column("categoria", VARCHAR(100))
                .column("anio", INTEGER)
                .column("tipoLibro", VARCHAR(100))
                .constraint(primaryKey("ISBN")).execute();
    }

    // relaciona una tabla con otra
    private static void relacionarTabla(DSLContext create, String nombreTabla, String claveForanea, String nombreTablaRelacion) {
        create.alterTableIfExists(nombreTabla).add(foreignKey(claveForanea).references(nombreTablaRelacion)).execute();
    }

    // agrega una columna a una tabla
    private static void agregarColumnaTabla(DSLContext create, String nombreTabla, String columna, DataType tipoColumna) {
        create.alterTableIfExists(nombreTabla).addColumn(columna, tipoColumna);
    }
}
