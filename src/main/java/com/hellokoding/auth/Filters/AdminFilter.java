package com.hellokoding.auth.Filters;


import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.sasl.AuthenticationException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/adminPage", "/addItem"})
public class AdminFilter implements Filter {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private HttpSession httpSession;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("AdminFilter doFilter() is invoked.");

        User user = new User();
        try {
            user = userService.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
        }


        String role = user.getRole().getName();
        if (role.equals("user"))
            throw new AuthenticationException("Недостаточно прав доступа");
        else
            chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}

