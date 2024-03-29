package org.example.wonderful.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.wonderful.core.Role;
import org.example.wonderful.service.UserService;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean outcome = UserService.login(req.getParameter("password"), req.getParameter("username"));
        if (outcome) {
            req.getSession().setAttribute("auth_token", Role.USER);
            resp.sendRedirect("homepage");
        }
        resp.sendRedirect("login");
    }
}
