package com.web.servlets;

import UserImpl.UserDAOImpl;
import UserImpl.UserModifyDAOImpl;
import dao.UserDAO;
import dao.UserModifyDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.User;
@WebServlet(name="UsersServlet", urlPatterns = {"/allUsers"})
public class UsersServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();
    public final UserModifyDAO userModifyDAO = new UserModifyDAOImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text.html");
        HttpSession session = request.getSession();
        String adminName = (String) session.getAttribute("username");
        List<User> users= userDAO.getAllUsers();
        List<User> trueUsers = new ArrayList<User>();
        for(User user:users){
            if(!user.getUsername().equals(adminName)){
                trueUsers.add(user);
            }
        }
        request.setAttribute("allUsers",trueUsers);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/users.jsp");
        rd.forward(request,response);


    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
    }
}
