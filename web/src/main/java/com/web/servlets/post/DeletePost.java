package com.web.servlets.post;

import dao.PostDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.daoImpl.PostDAOImpl;

import java.io.IOException;

@WebServlet(name = "DeletePost",urlPatterns = "/deletePost")
public class DeletePost extends HttpServlet {

    PostDAO postDAO = new PostDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long idTopic = Long.parseLong(request.getParameter("idPost"));

        postDAO.delete(idTopic);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/welcome.jsp");
        requestDispatcher.forward(request,response);


    }
}
