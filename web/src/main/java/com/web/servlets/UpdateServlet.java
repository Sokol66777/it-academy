package com.web.servlets;

import exceptions.RepeatedDataException;
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
import validation.ValidationParametrs;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateUsersUsername = request.getParameter("updateUsername");
        User updateUser = null;
        try {
            updateUser = userDAO.getByUsername(updateUsersUsername);
        } catch (SQLException | PropertyVetoException e) {
            request.setAttribute("error",e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request,response);
        }
        if (updateUser!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("updateUser", updateUser);
            session.setAttribute("updateUsersUsername", updateUsersUsername);
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
        String password="";
        String updateUsersUsername = (String)session.getAttribute("updateUsersUsername");
        String newUsername = request.getParameter("newUsername");
        String newEmail = request.getParameter("newEmail");
        if(updateUsersUsername.equals(session.getAttribute("username"))) {
            password = request.getParameter("password");
        }
        try {
            if(!password.equals("")) {
                ValidationParametrs.validationPassword(password);
            }

            User user = userDAO.getByUsername(updateUsersUsername);
            updateUser = new User();
            updateUser.setUsername(newUsername);
            updateUser.setEmail(newEmail);
            if(user!=null) {
                updateUser.setID(user.getID());
            }

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

        } catch (RepeatedDataException | SQLException e) {

            PrintWriter printWriter = response.getWriter();
            printWriter.write(e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/update.jsp");
            rd.include(request,response);
            printWriter.close();
            rd.forward(request,response);
        } catch (PropertyVetoException e){
            request.setAttribute("error",e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request,response);
        }


    }
}
