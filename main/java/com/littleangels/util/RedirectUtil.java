package com.littleangels.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RedirectUtil {

    public static void redirectToPage(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {

        String target;

        switch (page) {
            case "product":
                target = "/WEB-INF/pages/product.jsp";
                break;
            case "about":
                target = "/WEB-INF/pages/about.jsp";
                break;
            case "contact":
                target = "/WEB-INF/pages/contact.jsp";
                break;
            case "addcart":
                target = "/WEB-INF/pages/addcart.jsp";
                break;
            case "my-order":
                target = "/WEB-INF/pages/my-order.jsp";
                break;
            default:
                target = "/WEB-INF/pages/404.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
    }
}
