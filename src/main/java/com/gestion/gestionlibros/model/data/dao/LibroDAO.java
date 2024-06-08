package com.gestion.gestionlibros.model.data.dao;

import com.gestion.gestionlibros.model.Libro;
import com.gestion.gestionlibros.model.TipoLibro;
import com.gestion.gestionlibros.model.CategoriaLibro;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;
import java.util.ArrayList;

import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;
public class LibroDAO {

    // metodo para registrar un libro en la base de datos
    public static void agregarLibro(DSLContext query, Libro libro){
        Table tablaLibro= table(name("Libro"));
        Field[] columnas = tablaLibro.fields("ISBN", "nombre","editorial","categoria","anio","tipoLibro");
        query.insertInto(tablaLibro, columnas[0], columnas[1],columnas[2],columnas[3], columnas[4], columnas[5])
                .values(libro.getISBN(), libro.getNombre(),libro.getEditorial(),libro.getCategoriaLibro().getCategoria(),libro.getAnio(),libro.getTipoLibro().getTipoLibro())
                .execute();
    }


    public static void modificarLibro(DSLContext query, String ISBN, String columnaTabla, Object dato){
        query.update(DSL.table("Libro")).set(DSL.field(columnaTabla),dato).
                where(DSL.field("ISBN").eq(ISBN)).execute();
    }

    public static List obtenerLibro(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Libro")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaLibros(resultados);
    }

    public static List obtenerLibros(DSLContext query){
        Result resultados = query.select().from(DSL.table("Libro")).fetch();
        return obtenerListaLibros(resultados);
    }

    public static void eliminarLibro(DSLContext query, String ISBN){
        Table tablaLibro= table(name("Libro"));
        query.delete(DSL.table("Libro")).where(DSL.field("ISBN").eq(ISBN)).execute();
    }

    private static List obtenerListaLibros(Result resultados){
        List<Libro> libros= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String ISBN = (String) resultados.getValue(fila,"ISBN");
            String nombre = (String) resultados.getValue(fila,"nombre");
            String editorial = (String) resultados.getValue(fila,"editorial");
            String categoria = (String) resultados.getValue(fila,"categoria");
            int anio = (Integer) resultados.getValue(fila,"anio");
            String tipoLibro = (String) resultados.getValue(fila,"tipoLibro");
            libros.add(new Libro(ISBN,nombre,editorial, CategoriaLibro.valueOf(categoria),anio, TipoLibro.valueOf(tipoLibro)));
        }
        return libros;
    }
}

