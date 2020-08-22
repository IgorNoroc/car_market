package ru.job4j.market.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/paramSearch")
public class SetParametersSearchingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().setAttribute("lastDay", req.getParameter("lastDay"));
        req.getServletContext().setAttribute("onlyWithPhoto", req.getParameter("onlyWithPhoto"));
        req.getServletContext().setAttribute("search", req.getParameter("search"));
        req.getRequestDispatcher("/allCars").forward(req, resp);
    }
}
