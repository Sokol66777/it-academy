package com.web.servlets.topic;

import dao.PostDAO;
import dao.TopicDAO;
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
import dao.daoImpl.TopicDAOImpl;
import dao.daoImpl.UserDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@WebServlet(name = "DeleteTopic", urlPatterns = "/deleteTopic")
public class DeleteTopic extends HttpServlet {

    TopicDAO topicDAO = new TopicDAOImpl();
    UserDAO userDAO = new UserDAOImpl();
    PostDAO postDAO = new PostDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long idTopic = Long.parseLong(request.getParameter("deleteTopicID"));
        User userWithTopic =userDAO.getUserByIdWithTopic( Long.parseLong(session.getAttribute("ID").toString()));
        Set<Topic> topics = userWithTopic.getTopics();
        List<Post> deletePosts = postDAO.getPostsByUserTopic(userWithTopic.getID(), idTopic);

        for (Post deletePost:deletePosts){
            postDAO.delete(deletePost.getID());
        }

        Topic deleteTopic = topicDAO.get(idTopic);
        topics.remove(deleteTopic);

        try {

            userDAO.modify(userWithTopic);
            session.setAttribute("userWithTopic",userWithTopic);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            requestDispatcher.forward(request,response);

        } catch (LogicException e) {

            PrintWriter printWriter = response.getWriter();
            printWriter.write(e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            rd.include(request,response);
            printWriter.close();
        }

    }
}
