package ru.job4j.market.controller;

import ru.job4j.market.model.Car;
import ru.job4j.market.model.User;
import ru.job4j.market.persistance.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

@WebServlet("/addCar")
@MultipartConfig
public class AddCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Part file = req.getPart("file");
        String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
        if (!fileName.equals("")) {
            uploadPhoto(file, fileName);
        }
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String created = req.getParameter("created");
        String color = req.getParameter("color");
        String mileage = req.getParameter("mileage");
        String type = req.getParameter("type");
        String bodyCar = req.getParameter("bodyCar");
        String engine = req.getParameter("engine");
        User user = (User) req.getSession().getAttribute("user");
        DAO.instOf().createCar(
                new Car(
                        name, price, new Date(System.currentTimeMillis()), created, color,
                        mileage, type, bodyCar, engine, fileName, user, true)
        );
        getServletContext().setAttribute("cars", DAO.instOf().getCars());
        req.getRequestDispatcher("show_car.jsp").forward(req, resp);
    }

    private void uploadPhoto(Part photo, String fileName) {
        File folderId = new File("Car_Market_images" + File.separator + fileName);
        try (FileOutputStream out = new FileOutputStream(folderId)) {
            out.write(photo.getInputStream().readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
