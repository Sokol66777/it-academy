package com.web.servlets;

import UserImpl.UserDAOImpl;
import UserImpl.UserModifyDAOImpl;
import dao.impl.UserDAO;
import dao.impl.UserModifyDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {


    UserModifyDAO userModifyDAO = new UserModifyDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        userModifyDAO.DeleteUser(username);
        UsersServlet usersServlet = new UsersServlet();
        usersServlet.doPost(request,response);

    }



    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
