package com.web.servlets;

import userImpl.UserDAOImpl;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
@WebServlet(name="UsersServlet", urlPatterns = {"/allUsers"})
public class UsersServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text.html");
        HttpSession session = request.getSession();
        String adminName = (String) session.getAttribute("username");
        List<User> trueUsers = new ArrayList<User>();
        List<User> users = null;
        try {
            users = userDAO.getAllUsers();
        } catch (SQLException | PropertyVetoException e) {
            request.setAttribute("error",e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request,response);
        }

        if(users!=null) {
            for (User user : users) {
                if (!user.getUsername().equals(adminName)) {
                    trueUsers.add(user);
                }
            }
        }

        request.setAttribute("allUsers", trueUsers);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
        rd.forward(request,response);

    }


}
