package com.web.servlets.post;

import dao.PostDAO;
import exceptions.LogicException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Post;
import dao.daoImpl.PostDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdatePost", urlPatterns = "/updatePost")
public class UpdatePost extends HttpServlet {

    PostDAO postDAO = new PostDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idPost =Long.parseLong( request.getParameter("idPost"));
        Post post = postDAO.get(idPost);

        request.setAttribute("updatePost",post);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/updatePost.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        long idPost = Long.parseLong(request.getParameter("idPost"));
        String newPostName = request.getParameter("newPostName");
        String newPostText = request.getParameter("newPostText");

        Post post = postDAO.get(idPost);
        post.setName(newPostName);
        post.setText(newPostText);

        try {
            postDAO.modify(post);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            requestDispatcher.forward(request,response);
        } catch (LogicException e) {
            PrintWriter printWriter=response.getWriter();
            printWriter.write(e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/addPost.jsp");
            rd.include(request,response);
            printWriter.close();
        }

    }
}
