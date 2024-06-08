package com.gestion.gestionlibros.model.data.dao;

import com.gestion.gestionlibros.model.CategoriaLibro;
import com.gestion.gestionlibros.model.Libro;
import com.gestion.gestionlibros.model.TipoLibro;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;
import java.util.ArrayList;

import java.util.List;

import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;
public class CategoriaLibroDAO {

    public static void registrarCategoriaLibro(DSLContext query, CategoriaLibro categoriaLibro){
        Table tablaCategoriaLibro= table(name("CategoriaLibro"));
        Field[] columnas = tablaCategoriaLibro.fields("categoria");
        query.insertInto(tablaCategoriaLibro, columnas[0])
                .values(categoriaLibro.getCategoria())
                .execute();
    }

    public static void modificarCategoriaLibro(DSLContext query, String categoria, String columnaTabla, Object dato){
        query.update(DSL.table("CategoriaLibro")).set(DSL.field(columnaTabla),dato).
                where(DSL.field("categoria").eq(categoria)).execute();
    }

    public static List obtenerCategoriaLibro(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("CategoriaLibro")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaCategoriaLibros(resultados);
    }

    public static List obtenerCategoriaLibros(DSLContext query){
        Result resultados = query.select().from(DSL.table("CategoriaLibro")).fetch();
        return obtenerListaCategoriaLibros(resultados);
    }

    public static void eliminarCategoriaLibro(DSLContext query, CategoriaLibro categoria){
        Table tablaCategoriaLibro= table(name("CategoriaLibro"));
        query.delete(DSL.table("CategoriaLibro")).where(DSL.field("categoria").eq(categoria)).execute();
    }

    private static List obtenerListaCategoriaLibros(Result resultados){
        List<CategoriaLibro> categoriaLibros= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            String categoria = (String) resultados.getValue(fila,"categoria");
            categoriaLibros.add(CategoriaLibro.valueOf(categoria));
        }
        return categoriaLibros;
    }

}
