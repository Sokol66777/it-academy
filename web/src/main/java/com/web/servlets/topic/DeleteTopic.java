package com.web.servlets.topic;

import dao.TopicDAO;
import dao.UserDAO;
import exceptions.UserLogicException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Topic;
import model.User;
import userImpl.TopicDAOImpl;
import userImpl.UserDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

@WebServlet(name = "DeleteTopic", urlPatterns = "/deleteTopic")
public class DeleteTopic extends HttpServlet {

    TopicDAO topicDAO = new TopicDAOImpl();
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long idTopic = Long.parseLong(request.getParameter("deleteTopicID"));
        User userWithTopic = (User) session.getAttribute("userWithTopic");
        Set<Topic> topics = userWithTopic.getTopics();
        Topic deleteTopic = topicDAO.get(idTopic);
        topics.remove(deleteTopic);
        try {
            userDAO.modify(userWithTopic);
            session.setAttribute("userWithTopic",userWithTopic);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            requestDispatcher.forward(request,response);
        } catch (UserLogicException e) {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            rd.include(request,response);
            printWriter.close();
        }

    }
}
