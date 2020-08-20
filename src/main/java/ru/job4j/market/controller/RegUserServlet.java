package ru.job4j.market.controller;


import ru.job4j.market.model.User;
import ru.job4j.market.persistance.DAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/reg")
public class RegUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(name, email, password);
        DAO.instOf().createUser(user);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        getServletContext().setAttribute("cars", DAO.instOf().getCars());
        resp.sendRedirect(req.getContextPath() + "/show_car.jsp");
    }
}
