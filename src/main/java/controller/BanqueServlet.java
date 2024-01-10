package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BanqueService;
import service.BanqueServiceService;

@WebServlet("/banqueServlet")
public class BanqueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BanqueService banqueService;

    public void init() throws ServletException {
        try {
            // Initialize the BanqueService instance
            BanqueServiceService service = new BanqueServiceService();
            banqueService = service.getBanqueServicePort();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize BanqueService", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the JSP page in the WEB-INF directory
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	// Handle the form submission
        double amount = Double.parseDouble(request.getParameter("amount"));
        String operation = request.getParameter("operation");

        // Call the web service methods based on the selected operation
        double result = 0;
        String message = null;
        String destination = "/WEB-INF/result.jsp"; // Default destination

        switch (operation) {
            case "conversion":
                result = banqueService.conversion(amount);
                break;
            case "retirer":
                double currentSolde = banqueService.getCurrentSolde();
                if (amount > currentSolde) {
                    message = "Insufficient balance for withdrawal.";
                } else {
                    result = banqueService.retirer(amount);
                }
                break;
            case "verser":
                result = banqueService.verser(amount);
                break;
            case "getCurrentSolde":
                result = banqueService.getCurrentSolde();
                break;
            default:
                message = "Invalid operation selected.";
        }

        // Set attributes to be used in the JSP page
        request.setAttribute("result", result);
        if (message != null) {
            request.setAttribute("message", message);
        }

        //Forward the request to the JSP page in the WEB-INF directory
        request.getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
    }

}
