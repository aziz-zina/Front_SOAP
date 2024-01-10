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
        // Render the login JSP file
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("admin") && password.equals("admin")) {
            // Set a session attribute to indicate a successful login
            request.getSession().setAttribute("loggedIn", true);

            // Redirect to the BanqueServlet
            response.sendRedirect(request.getContextPath() + "/banqueServlet");
        } else {
        	request.setAttribute("loginFailed", true);
            // Forward back to the login page (preserves attributes)
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
		