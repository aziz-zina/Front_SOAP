package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        if (login.equals("admin") && password.equals("admin")) {
            request.getSession().setAttribute("loggedIn", true);
            response.sendRedirect(request.getContextPath() + "/bank");
        } else {
        	request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
		