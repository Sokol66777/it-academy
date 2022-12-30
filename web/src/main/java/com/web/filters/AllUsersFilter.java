package com.web.filters;

import com.web.forms.UserForm;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("allUsersFilter")
public class AllUsersFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        UserForm userForm=(UserForm) req.getSession().getAttribute("user");
        if(userForm!=null&&!userForm.getRole().equals("admin")){
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/welcome.jsp");
            rd.forward(request,response);
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
