/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Patryk
 */
public class PollServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PollServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String nazwaCookie = "ankieta";
            boolean czyJestCookie=false;
            Cookie [] cookies = request.getCookies();
            if(cookies!=null){
                for(int i=0;i<cookies.length;i++){
                    Cookie c = cookies[i];
                    if(nazwaCookie.equals(c.getName())){
                        out.println("Głosowanie zakończone");
                        czyJestCookie=true;
                    }
                }              
            }
            if(!czyJestCookie){
            out.println("<h2>Wybrałeś następujące technologie:</h2>");
            getAllParametersMap(request, out);
            out.println("<br><h2>Zobacz wyniki ankiety</h2><br>");
            HashMap read = Helper.readResults();
            for (Iterator it = read.keySet().iterator(); it.hasNext();) {
                String key = (String) it.next();
                out.println(key+" : "+read.get(key)+"<br>");
            }
            out.println("<a href = ExcelServlet>Pokaż wyniki ankiety w arkuszu kalkulacyjnym</a><br>");
            out.println("<a href = ChartServlet>Pokaż wyniki ankiety jako wykres</a><br>");
            out.println("<a href = RedirectServlet>Przekierowanie na stronę główną</a>");
            Cookie c = new Cookie("ankieta", "1");
            c.setMaxAge(60*60*24*2);
            c.setPath("/");
            response.addCookie(c);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
     public static void getAllParametersMap(HttpServletRequest request, PrintWriter out){
        HashMap hm =(HashMap) request.getParameterMap();
        Set set = hm.entrySet(); 
        Iterator i = set.iterator();
        out.println();
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            String[] tab= (String[]) me.getValue();
            out.println(me.getKey()+"<br>");
            }
        Helper.writeResults(hm);
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
