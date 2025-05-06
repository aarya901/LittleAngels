package com.littleangels.controller;

import com.littleangels.util.RedirectUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {
        "/product",
        "/about",
        "/contact",
        "/addcart",
        "/my-order"
})

public class PageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String page = uri.substring(context.length() + 1);

        System.out.println("Routing to: " + page);
        RedirectUtil.redirectToPage(request, response, page);
    }
    
}
