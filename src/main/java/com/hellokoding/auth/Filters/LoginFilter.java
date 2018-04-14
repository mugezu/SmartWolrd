package com.hellokoding.auth.Filters;

import com.hellokoding.auth.model.User;
import com.hellokoding.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Роман on 09.04.2018.
 */
@WebFilter
public class LoginFilter implements Filter {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private HttpSession httpSession;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("LoginFilter doFilter() is invoked.");

        User user = new User();
        try {
            user = userService.getCurrentUser();
            if (httpSession.getAttribute("user") == null) {
                httpSession.setAttribute("user", user);

            }
        } catch (Exception e) {

        } finally {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}