package com.web.servlets;

import exceptions.LogicException;
import exceptions.TopicLogicException;
import exceptions.UserLogicException;
import userImpl.UserDAOImpl;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import validation.ValidationUsersParametrs;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/add"})
public class RegistrationServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add.jsp");
        rd.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       response.setContentType("text/html");
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       String confirmedPassword = request.getParameter("confirmedPassword");
       String role = "user";
       String email = request.getParameter("email");


       if(!password.equals(confirmedPassword)){
           PrintWriter printWriter = response.getWriter();
           printWriter.write("Password is not confirmed");
           RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add.jsp");
           rd.include(request,response);
           printWriter.close();
       }else{
           try {
               ValidationUsersParametrs.validationPassword(password);

               User newUser = new User();
               newUser.setEmail(email);
               newUser.setPassword(password);
               newUser.setUsername(username);

               userDAO.add(newUser);

               HttpSession session = request.getSession();
               session.setAttribute("username", username);
               session.setAttribute("role",role);
               RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
               rd.forward(request,response);

           } catch (LogicException e) {
              PrintWriter printWriter=response.getWriter();
              printWriter.write(e.getMessage());
              RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add.jsp");
              rd.include(request,response);
              printWriter.close();
           }

       }

    }
}
