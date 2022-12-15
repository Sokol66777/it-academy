package com.web.servlets.topic;

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
import model.Topic;
import model.User;
import dao.daoImpl.TopicDAOImpl;
import dao.daoImpl.UserDAOImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@WebServlet(name = "AddTopicServlet", urlPatterns = "/addTopic")
public class AddTopicServlet extends HttpServlet {

    TopicDAO topicDAO = new TopicDAOImpl();
    UserDAO userDAO = new UserDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role =(String) session.getAttribute("role");
        if(role.equals("user")){
            long idUser = Long.parseLong(session.getAttribute("ID").toString());
            List<Topic> allTopics=  topicDAO.getAll();
            Set<Topic > usersTopic = (userDAO.getUserByIdWithTopic(idUser)).getTopics();
            for(Topic topic:usersTopic){
                allTopics.remove(topic);
            }
            session.setAttribute("allTopics",allTopics);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/addTopic.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session =request.getSession();
        String role =(String) session.getAttribute("role");

        if(role.equals("admin")) {

            String topicName = request.getParameter("topicName");
            Topic topic = new Topic();
            topic.setName(topicName);

            try {
                topicDAO.add(topic);
                session.setAttribute("allTopics", topicDAO.getAll());
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
                rd.forward(request, response);
            } catch (LogicException e) {
                PrintWriter printWriter = response.getWriter();
                printWriter.write(e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/addTopic.jsp");
                rd.include(request, response);
                printWriter.close();
            }
        }else{
            long idTopic = Long.parseLong(request.getParameter("idTopic"));
            long idUser = Long.parseLong(session.getAttribute("ID").toString());

            Topic topic = topicDAO.get(idTopic);
            User user = userDAO.getUserByIdWithTopic(idUser);
            Set<Topic> topicSet = user.getTopics();
            topicSet.add(topic);

            try {
                userDAO.modify(user);
            } catch (LogicException e) {
                PrintWriter printWriter = response.getWriter();
                printWriter.write(e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
                rd.include(request,response);
                printWriter.close();
                rd.forward(request,response);
            }
            session.setAttribute("userWithTopic",userDAO.getUserByIdWithTopic(idUser));
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            rd.forward(request,response);
        }
    }
}
