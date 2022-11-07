package com.web.servlets.topic;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddTopicServlet", urlPatterns = "/addTopic")
public class AddTopicServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/addTopic.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){

    }
}
