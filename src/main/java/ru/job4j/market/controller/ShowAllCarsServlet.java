package ru.job4j.market.controller;

import ru.job4j.market.model.Car;
import ru.job4j.market.persistance.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@WebServlet(value = "/allCars", loadOnStartup = 1)
public class ShowAllCarsServlet extends HttpServlet {
    private final DAO database = DAO.instOf();

    @Override
    public void init() {
        getServletContext().setAttribute("cars", DAO.instOf().getCars());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().setAttribute("cars", getNeedList(
                (String) req.getServletContext().getAttribute("lastDay"),
                (String) req.getServletContext().getAttribute("onlyWithPhoto"),
                (String) req.getServletContext().getAttribute("search")));
        req.getRequestDispatcher("show_car.jsp").forward(req, resp);
    }

    private Collection<Car> getNeedList(String date, String withPhoto, String search) {
        if (date == null && withPhoto == null && (search == null || search.equals(""))) {
            return database.getCars();
        } else if (date != null && withPhoto != null && !search.equals("")) {
            return database.getCarsFilterDayPhotoName(search);
        } else if (date != null && withPhoto == null && search.equals("")) {
            return database.getCarsFilerDay();
        } else if (date == null && withPhoto != null && search.equals("")) {
            return database.getCarsFilterPhoto();
        } else if (date == null && withPhoto == null && !search.equals("")) {
            return database.getCarsFilterName(search);
        } else if (date != null && withPhoto != null && search.equals("")) {
            return database.getCarsFilterDayPhoto();
        } else if (date == null && withPhoto != null && !search.equals("")) {
            return database.getCarsFilterPhotoName(search);
        } else if (date != null && withPhoto == null && !search.equals("")) {
            return database.getCarsFilterDayName(search);
        }
        return Collections.emptyList();
    }
}
