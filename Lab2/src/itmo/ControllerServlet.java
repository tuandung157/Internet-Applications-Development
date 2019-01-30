package itmo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valueX = request.getParameter("valueX");
        String valueY = request.getParameter("valueY");
        String valueR = request.getParameter("valueR");

        if ((valueX == null) || (valueR == null) || (valueY == null)) {
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else{
            request.getServletContext().getRequestDispatcher("/checking").forward(request,response);
        }
    }
}

