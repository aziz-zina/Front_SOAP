package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BanqueService;
import service.BanqueServiceService;

@WebServlet("/bank")
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
    	HttpSession session = request.getSession();
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            session.removeAttribute("loggedIn");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (loggedIn == null || !loggedIn) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
    	
    	double solde = banqueService.getCurrentSolde();
    	request.setAttribute("solde", solde);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        double amount = Double.parseDouble(request.getParameter("amount"));
        String operation = request.getParameter("operation");

        double result = 0;
        switch (operation) {
            case "conversion":
            	double soldeBefore = amount;
                result = banqueService.conversion(soldeBefore);
                request.setAttribute("convertedAmount", result);
                request.setAttribute("soldeBefore", soldeBefore);
                request.setAttribute("soldeAfter", result);
                request.setAttribute("conversion", true);
                break;
            case "retirer":
                double currentSolde = banqueService.getCurrentSolde();
                if (amount > currentSolde) {
                	request.setAttribute("soldeInsuffisant", true);
                } else {
                    result = banqueService.retirer(amount);
                }
                break;
            case "verser":
                result = banqueService.verser(amount);
                break;
            default:
        }
        
        double solde = banqueService.getCurrentSolde();
        request.setAttribute("solde", solde);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

}
