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
@WebServlet(name = "registroCategoriaServlet", value = "/registroCategoria")
public class RegistroCategoriaServlet {
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("LibrosBD");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroCategoria.jsp");
        respuesta.forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroErroneoCategoriaLibro.jsp");
        if(req.getParameter("categoria").length()!=0){
            String categoria = req.getParameter("categoria");
            CategoriaLibro categoriaLibro = CategoriaLibro.valueOf(categoria);
            try {
                if(agregarCategoriaLibro(categoriaLibro)){
                    req.setAttribute("categoria",categoriaLibro);
                    respuesta = req.getRequestDispatcher("/registroExitosoCategoriaLibro.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req,resp);
    }
    private boolean agregarCategoriaLibro(CategoriaLibro categoriaLibro) throws ClassNotFoundException {
        DSLContext query= DBGenerator.conectarBD("LibrosBD");
        List<CategoriaLibro> categorias = CategoriaLibroDAO.obtenerCategoriaLibro(query,"categoria",categoriaLibro);
        if(categorias.size()!=0){
            return false;
        }
        CategoriaLibroDAO.registrarCategoriaLibro(query,categoriaLibro);
        return true;
    }



}
