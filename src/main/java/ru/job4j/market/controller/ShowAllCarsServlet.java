package ru.job4j.market.controller;

import ru.job4j.market.persistance.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/allCars", loadOnStartup = 1)
public class ShowAllCarsServlet extends HttpServlet {
    @Override
    public void init() {
        getServletContext().setAttribute("cars", DAO.instOf().getCars());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute("cars", DAO.instOf().getCars());
        req.getRequestDispatcher("show_car.jsp").forward(req, resp);
    }
}
