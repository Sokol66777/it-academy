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

import java.io.IOException;
import java.io.PrintWriter;
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
        try {

            List<User> users = userDAO.getAllUsers();

            for (User user : users) {
                if (!user.getUsername().equals(adminName)) {
                    trueUsers.add(user);
                }
            }
            request.setAttribute("allUsers", trueUsers);
        }catch (SQLException e){
            PrintWriter printWriter = response.getWriter();
            printWriter.write(e.getMessage());
            printWriter.close();
        }

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
        rd.forward(request,response);

    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
    }
}
