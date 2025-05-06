package com.littleangels.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String uri = req.getRequestURI();

        // Allow access to static resources
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }

        // Allow public pages
        if (uri.endsWith("/login") || uri.endsWith("/register") || uri.endsWith("/") || uri.contains("/public")) {
            chain.doFilter(request, response);
            return;
        }

        // Get role from cookie
        String role = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("role".equals(c.getName())) {
                    role = c.getValue();
                    break;
                }
            }
        }

        // Admin access control
        if (uri.contains("/admin-product") || uri.contains("/add-product")) {
            if ("admin".equals(role)) {
                chain.doFilter(request, response);
            } else if ("user".equals(role)) {
                res.sendRedirect(req.getContextPath() + "/home");
            } else {
                res.sendRedirect(req.getContextPath() + "/login");
            }
            return;
        }

        // All other pages allowed if logged in
        if (session != null && session.getAttribute("username") != null) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {}
}
