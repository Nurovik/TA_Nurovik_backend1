/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Cms;

import DAO.NewsDao;
import Models.News;
import ResponseServer.ResponseNews;
import ResponseServer.ResponseNewsById;
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
public class CmsNewsController extends HttpServlet {

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
        try  {
            /* TODO output your page here. You may use following sample code. */
            
             ResponseNews RESPONSE = new ResponseNews();
             NewsDao newsdao = new NewsDao();
             News ns = new News();
             String page1 = request.getParameter("page1");
             String newsid = request.getParameter("news_id");
            if(newsid == null){ 
             if(page1 == null){
                 page1 = "1";
                }
             
            int startResult = 0;
            int endResult = 0;
            RESPONSE = newsdao.GetAll(Integer.parseInt(page1), "id_news-desc","statuspublish=1");
              
              if(RESPONSE.getStatusCode().equals("01")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              rd = request.getRequestDispatcher("Views/cms/News.jsp");
              rd.forward(request, response);
              
              }else if(RESPONSE.getStatusCode().equals("02")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              rd = request.getRequestDispatcher("Views/servernotconnect.jsp");
              rd.forward(request, response);
              
              }else{
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              request.setAttribute("totalnews", RESPONSE.getTotal());
              request.setAttribute("startResult", startResult);
              request.setAttribute("endResult", endResult);
              request.setAttribute("list", RESPONSE.getListData());
              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
              rd = request.getRequestDispatcher("Views/cms/News.jsp");
              rd.forward(request, response);
              }
              
            }else{
                ResponseNewsById Mrespbyid = new ResponseNewsById();
                Mrespbyid = newsdao.getid(Integer.parseInt(newsid));
              
              if(Mrespbyid.getStatusCode().equals("01")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              rd = request.getRequestDispatcher("Views/cms/NewsId.jsp");
              rd.forward(request, response);
              
              }else if(Mrespbyid.getStatusCode().equals("02")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              rd = request.getRequestDispatcher("Views/servernotconnect.jsp");
              rd.forward(request, response);
              
              }else{
              request.setAttribute("status", Mrespbyid.getStatus());
              request.setAttribute("statusdata", Mrespbyid.getStatusCode());
              request.setAttribute("news_id", Mrespbyid.getData().getId_news());
              request.setAttribute("title", Mrespbyid.getData().getTitle());
              request.setAttribute("content", Mrespbyid.getData().getContent());
              request.setAttribute("image", Mrespbyid.getData().getImage());
              request.setAttribute("datepost", Mrespbyid.getData().getDatecreated());
              request.setAttribute("userpost", Mrespbyid.getData().getUser());
              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
              rd = request.getRequestDispatcher("Views/cms/NewsId.jsp");
              rd.forward(request, response);
              }
                
       
            }
            
        } catch (Exception e){
            Logger.getLogger(CmsNewsController.class.getName()).log(Level.SEVERE, null, e);
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
