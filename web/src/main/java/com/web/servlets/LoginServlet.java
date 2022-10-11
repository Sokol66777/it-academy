package com.web.servlets;
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
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpSession;
import model.User;


@WebServlet (name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    public final UserDAO userDAO = new UserDAOImpl();
    public final UserModifyDAO userModifyDAO = new UserModifyDAOImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userDAO.getByUsername(name);

        if(user!=null && user.getPassword().equals(password)){
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role",user.getRole());
            session.setAttribute("user",user);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/welcome.jsp");
            rd.forward(request,response);
        }
        else{
            PrintWriter printWriter = response.getWriter();
            printWriter.write("Incorrect name or password");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request,response);
            printWriter.close();
        }


    }
}
