package com.web.servlets;

import Exceptions.RepeatedDataException;
import UserImpl.UserDAOImpl;
import UserImpl.UserModifyDAOImpl;
import dao.impl.UserDAO;
import dao.impl.UserModifyDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import validation.ValidationParametrs;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationServlet", urlPatterns = {"/add"})
public class RegistrationServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();
    public final UserModifyDAO userModifyDAO = new UserModifyDAOImpl();

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


        long id = userDAO.getGreatestID();
       if(!password.equals(confirmedPassword)){
           PrintWriter printWriter = response.getWriter();
           printWriter.write("Password is not confirmed");
           RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add.jsp");
           rd.include(request,response);
           printWriter.close();
       }else{
           try {
               ValidationParametrs.validationUsername(username);
               ValidationParametrs.validationEmail(email);
               ValidationParametrs.validationPassword(password);
               User user = new User(id,username,password,email,role);
               userModifyDAO.AddUser(user);
               HttpSession session = request.getSession();
               session.setAttribute("username", user.getUsername());
               session.setAttribute("role",user.getRole());
               session.setAttribute("user",user);
               RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
               rd.forward(request,response);

           } catch (RepeatedDataException e) {
              PrintWriter printWriter=response.getWriter();
              printWriter.write(e.getMessage());
              RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/add.jsp");
              rd.include(request,response);
              printWriter.close();
           }

       }

    }
}
