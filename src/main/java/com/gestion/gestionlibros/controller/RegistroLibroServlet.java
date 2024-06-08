package com.gestion.gestionlibros.controller;

import com.gestion.gestionlibros.model.CategoriaLibro;
import com.gestion.gestionlibros.model.Libro;
import com.gestion.gestionlibros.model.TipoLibro;
import com.gestion.gestionlibros.model.data.DBGenerator;
import com.gestion.gestionlibros.model.data.dao.CategoriaLibroDAO;
import com.gestion.gestionlibros.model.data.dao.LibroDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;

import java.io.IOException;
import java.util.List;

import static com.gestion.gestionlibros.model.data.dao.LibroDAO.agregarLibro;

@WebServlet(name = "registroLibroServlet", value = "/registroLibro")
public class RegistroLibroServlet {

    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("LibrosBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroLibro.jsp");
        respuesta.forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneo.jsp");
        if(req.getParameter("ISBN").length()!=0 && req.getParameter("nombre").length()!=0  &&
                req.getParameter("editorial").length()!=0 && req.getParameter("categoria").length()!=0 &&
                req.getParameter("anio").length()!=0 && req.getParameter("tipoLibro").length()!=0){
            String ISBN = req.getParameter("ISBN");
            String nombre = req.getParameter("nombre");
            String editorial = req.getParameter("editorial");
            String categoria = req.getParameter("categoria");
            int anio = Integer.parseInt(req.getParameter("anio"));
            String tipoLibro = req.getParameter("tipoLibro");
            Libro libro = new Libro(ISBN,nombre,editorial, CategoriaLibro.valueOf(categoria),anio, TipoLibro.valueOf(tipoLibro));
            try {
                if(agregarLibro(libro)){
                    req.setAttribute("libro",libro);
                    respuesta = req.getRequestDispatcher("/registroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req,resp);
    }


}
