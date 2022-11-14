package com.web.servlets;

import exceptions.LogicException;
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

@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long updateUserID = Long.parseLong(request.getParameter("updateUsersID"));
        User updateUser = userDAO.get(updateUserID);

        if (updateUser!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("updateUserID",updateUserID);
            session.setAttribute("updateUsersUsername", updateUser.getUsername());
            session.setAttribute("updateUsersPassword", updateUser.getPassword());
            session.setAttribute("updateUsersEmail", updateUser.getEmail());
        }
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/update.jsp");
        rd.forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User updateUser;
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        long updateUserID = Long.parseLong(session.getAttribute("updateUserID").toString());
        String password="";
        String updateUsersUsername = (String)session.getAttribute("updateUsersUsername");
        String newUsername = request.getParameter("newUsername");
        String newEmail = request.getParameter("newEmail");
        if(updateUsersUsername.equals(session.getAttribute("username"))) {
            password = request.getParameter("password");
        }
        try {
            if(!password.equals("")) {
                ValidationUsersParametrs.validationPassword(password);
            }

            updateUser = userDAO.getUserByIdWithTopic(updateUserID);
            updateUser.setUsername(newUsername);
            updateUser.setEmail(newEmail);

            if (password.equals("")){
                userDAO.modify(updateUser);
            }
            else{
                updateUser.setPassword(password);
                userDAO.modify(updateUser);
            }

            if(updateUsersUsername.equals(session.getAttribute("username"))){
                session.setAttribute("username", newUsername);
            }
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            rd.forward(request,response);

        } catch (LogicException e) {

            PrintWriter printWriter = response.getWriter();
            printWriter.write(e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/update.jsp");
            rd.include(request,response);
            printWriter.close();
        }
    }
}
