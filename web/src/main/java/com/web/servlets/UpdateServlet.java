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

@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();
    public final UserModifyDAO userModifyDAO = new UserModifyDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateUser = request.getParameter("updateUsername");
        HttpSession session = request.getSession();
        session.setAttribute("updateUser",updateUser);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/update.jsp");
        rd.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String updateUsername = (String)session.getAttribute("updateUser");
        String newUsername = request.getParameter("newUsername");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        User modifyUser = userDAO.getByUsername(updateUsername);
        if(!modifyUser.getPassword().equals(oldPassword)){
            PrintWriter printWriter = response.getWriter();
            printWriter.write("Password is wrong");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/update.jsp");
            rd.include(request,response);
            printWriter.close();
        }else{
            userModifyDAO.DeleteUser(updateUsername);
            try {
                ValidationParametrs.validationUsername(newUsername);
                ValidationParametrs.validationPassword(newPassword);
                ValidationParametrs.validationEmail(newEmail);
                modifyUser.setUsername(newUsername);
                modifyUser.setPassword(newPassword);
                modifyUser.setEmail(newEmail);
                userModifyDAO.AddUser(modifyUser);
                if(updateUsername.equals(session.getAttribute("username"))){
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
}
