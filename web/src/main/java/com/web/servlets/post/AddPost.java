package com.web.servlets.post;

import dao.PostDAO;
import dao.UserDAO;
import exceptions.LogicException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Post;
import model.Topic;
import model.User;
import dao.daoImpl.PostDAOImpl;
import dao.daoImpl.UserDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@WebServlet(name = "AddPost",urlPatterns = "/addPost")
public class AddPost extends HttpServlet {

    UserDAO userDAO = new UserDAOImpl();
    PostDAO postDAO = new PostDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long idTopic = Long.parseLong(request.getParameter("idTopic"));
        request.setAttribute("idTopic",idTopic);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/addPost.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String postName = request.getParameter("postName");
        long idTopic = Long.parseLong(request.getParameter("idTopic"));
        String textTopic = request.getParameter("topicText");
        long idUser = Long.parseLong(session.getAttribute("ID").toString());
        User userWithTopic = userDAO.getUserByIdWithTopic(idUser);
        Set<Topic> topics = userWithTopic.getTopics();
        Post post = new Post();

        for(Topic topic:topics){
            if(topic.getID()==idTopic){
                post.setTopic(topic);
                break;
            }
        }

        post.setText(textTopic);
        post.setName(postName);
        post.setUser(userWithTopic);

        try {
            postDAO.add(post);
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
