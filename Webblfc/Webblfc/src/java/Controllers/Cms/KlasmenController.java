/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cms;


import DAO.JadwalDao;
import DAO.KlasmenDao;
import Models.Klasmen;
import ResponseServer.ResponseJadwal;
import ResponseServer.ResponseKlasmen;
import ResponseServer.ResponseLiga;
import Utils.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author teguh
 */
public class KlasmenController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        RequestDispatcher rd;
        try {
            /* TODO output your page here. You may use following sample code. */
          
             ResponseJadwal responseJadwal = new ResponseJadwal();
             JadwalDao jadwaldao= new JadwalDao();
             ResponseKlasmen responseKlasmen = new ResponseKlasmen();
             KlasmenDao klasmendao = new KlasmenDao();
             
             responseJadwal = jadwaldao.GetAll();
             responseKlasmen = klasmendao.GetKlasmen();
             
              request.setAttribute("listjadwal", responseJadwal.getListData());
              request.setAttribute("listklasmen", responseKlasmen.getListData());
              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
              rd = request.getRequestDispatcher("Views/cms/Kompetisi.jsp");
              rd.forward(request, response);
        } catch (Exception e){
            Logger.getLogger(KlasmenController.class.getName()).log(Level.SEVERE, null, e);
        }   finally {            
            out.close();
        }
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
