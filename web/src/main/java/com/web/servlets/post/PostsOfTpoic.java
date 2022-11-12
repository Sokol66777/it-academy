package com.web.servlets.post;

import dao.PostDAO;
import dao.TopicDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Post;
import userImpl.PostDAOImpl;
import userImpl.TopicDAOImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PostsOfTpoic",urlPatterns = "/postsOfTopic")
public class PostsOfTpoic extends HttpServlet {

    PostDAO postDAO = new PostDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long idUser = Long.parseLong(session.getAttribute("ID").toString());
        long idTopic = Long.parseLong(request.getParameter("idTopic"));
        request.setAttribute("idTopic",idTopic);
        List<Post> posts = postDAO.getPostsByUserTopic(idUser,idTopic);
        session.setAttribute("posts",posts);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/postsOfTopic.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){

    }
}
