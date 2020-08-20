package ru.job4j.market.controller;

import ru.job4j.market.persistance.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/description")
public class DescriptionCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.getServletContext().setAttribute("car", DAO.instOf().findCarById(id));
        req.getRequestDispatcher("car_description.jsp").forward(req, resp);
    }
}
