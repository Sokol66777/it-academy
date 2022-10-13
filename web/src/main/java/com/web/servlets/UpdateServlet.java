package com.web.servlets;

import Exceptions.RepeatedDataException;
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
import model.User;
import validation.ValidationParametrs;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();
    public final UserModifyDAO userModifyDAO = new UserModifyDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateUsersUsername = request.getParameter("updateUsername");
        User updateUser = userDAO.getByUsername(updateUsersUsername);
        HttpSession session = request.getSession();
        session.setAttribute("updateUser",updateUser);
        session.setAttribute("updateUsersUsername",updateUsersUsername);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/update.jsp");
        rd.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String password="";
        String updateUsersUsername = (String)session.getAttribute("updateUsersUsername");
        String newUsername = request.getParameter("newUsername");
        String newEmail = request.getParameter("newEmail");
        if(updateUsersUsername.equals(session.getAttribute("username"))) {
            password = request.getParameter("password");
        }
        User modifyUser = userDAO.getByUsername(updateUsersUsername);
        userModifyDAO.DeleteUser(updateUsersUsername);
        try {
            ValidationParametrs.validationUsername(newUsername);
            if(!password.equals("")) {
                ValidationParametrs.validationPassword(password);
            }
            ValidationParametrs.validationEmail(newEmail);
            modifyUser.setUsername(newUsername);
            modifyUser.setEmail(newEmail);
            if(!password.equals("")) {
                modifyUser.setPassword(password);
            }
            userModifyDAO.AddUser(modifyUser);
            if(updateUsersUsername.equals(session.getAttribute("username"))){

                session.setAttribute("username", modifyUser.getUsername());
                session.setAttribute("role",modifyUser.getRole());
                session.setAttribute("user",modifyUser);

            }
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            rd.forward(request,response);

        } catch (RepeatedDataException e) {

            userModifyDAO.AddUser(modifyUser);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/update.jsp");
            rd.include(request,response);
            printWriter.close();
            rd.forward(request,response);
        }


    }
}
