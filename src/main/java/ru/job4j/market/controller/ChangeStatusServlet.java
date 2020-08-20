package ru.job4j.market.controller;

import ru.job4j.market.model.Car;
import ru.job4j.market.persistance.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeStatus")
public class ChangeStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car =  DAO.instOf().findCarById(Integer.parseInt(req.getParameter("id")));
        car.setStatus(!car.getStatus());
        DAO.instOf().update(car);
        req.getServletContext().setAttribute("car", car);
        req.getRequestDispatcher("car_description.jsp").forward(req, resp);
    }
}
