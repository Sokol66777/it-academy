package com.web.servlets;

import userImpl.UserDAOImpl;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name= "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {


    UserDAO userDAO = new UserDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        try {
            userDAO.delete(username);
        } catch (SQLException | PropertyVetoException e) {
            request.setAttribute("error",e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request,response);
        }
        RequestDispatcher rd = request.getRequestDispatcher("allUsers");
        rd.forward(request,response);

    }




}
