package itmo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;

public class AreaCheckServlet extends HttpServlet {
    private double arrX[] = new double[]{-2,-1.5,-1,-0.5,0,0.5,1,1.5,2};
    private double arrR[] = new double[]{1,2,3,4,5};
    private static double arrRes[][] = new double[10][3];
    private static String tmp[] = new String[10];
    private static int count = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        count ++;
        String valueX = request.getParameter("valueX");
        String valueY = request.getParameter("valueY");
        String valueR = request.getParameter("valueR");

        double x = Double.parseDouble(valueX);
        double y = Double.parseDouble(valueY);
        double r = Double.parseDouble(valueR);

        String res ;
        boolean ok  = false;
        boolean ok1 = false;
        for(int i=0;i<arrX.length;i++) if (arrX[i]==x) ok = true;
        for(int i=0;i<arrR.length;i++) if (arrR[i]==r) ok1 = true;
        if ((y>3) || (y<-5) || (!ok) || (!ok1) ) res = "ERROR";
        else{
            if (x<=0 && y<=0){
                if (x<-r || (y<-r/2)) res = "FALSE";
                else res = "TRUE";
            }else if (x>0 && y<0){
                if (Math.sqrt(Math.pow(x,2) + Math.pow(y,2))<=r) res = "TRUE";
                else res = "FALSE";
            }else if (x>0 && y>0){
                /*
                double ab = r;
                double ac = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
                double bc = Math.sqrt(Math.pow(y,2) + Math.pow(r/2-x,2));
                double cosB = (Math.pow(bc,2)+ Math.pow(ab,2) - Math.pow(ac,2))/(2*bc*ab);
                if (cosB > Math.sqrt(2)/2) res = "TRUE";

                else res = "FALSE";*/
                if (x+y <= r/2) res = "TRUE";
                else res = "FALSE";
            }else res = "FALSE";
        }
        arrRes[count][0] = x;
        arrRes[count][1] = y;
        arrRes[count][2] = r;
        tmp[count] = res;
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<p class = \"center\">Result</p>");
        out.println("<div style =\"margin-top: 2%\">");
        out.println("<table id = \"tableresult\" class = \"center\" border=\"5\" width=\"50%\">");
        out.println("<tr style=\"width: 300px\">");
        out.println("<th> No </th>");
        out.println("<th>Value X</th>");
        out.println("<th>Value Y</th>");
        out.println("<th>Value R</th>");
        out.println("<th>Result</th>");
        out.println("</tr>");
        for(int i=1;i<=count;i++){
            out.println("<tr style=\"width: 300px\">");
            out.println("<th>"+ i + "</th>");
            out.println("<th>"+ arrRes[i][0] + "</th>");
            out.println("<th>"+ arrRes[i][1] + "</th>");
            out.println("<th>"+ arrRes[i][2] + "</th>");
            out.println("<th>"+ tmp[i] + "</th>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</div>");

        out.println("<a href=\"index.jsp\" id=\"back-link\">another input</a>");

    }

}

