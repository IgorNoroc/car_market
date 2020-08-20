package ru.job4j.market.controller;


import ru.job4j.market.model.User;
import ru.job4j.market.persistance.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth")
public class AuthorizeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = DAO.instOf().findUserByEmail(email);
        } catch (SQLException e) {
            String error = "Почта или пароль не существует.";
            getServletContext().setAttribute("error", error);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
        if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            getServletContext().setAttribute("cars", DAO.instOf().getCars());
            resp.sendRedirect(req.getContextPath() + "/show_car.jsp");
        }
    }
}
