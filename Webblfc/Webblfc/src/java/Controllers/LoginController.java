/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import API.ApiConsume;
import ResponseServer.ResponseLogin;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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
public class LoginController extends HttpServlet {

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
            PrintWriter out = response.getWriter();
            RequestDispatcher rd;
        try {
            /* TODO output your page here. You may use following sample code. */
            Gson gson = new Gson();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if(username!= null && password != null ){
                 ApiConsume api = new ApiConsume();
                 Map<String,String> data = new HashMap<>();
                 data.put("username", username);
                 data.put("password", password);
                 Map<String,Object> req = new HashMap<>();
                 req.put("data", data);
                 String Response = api.POSTLogin(req, "login");
                  ResponseLogin resp = new ResponseLogin();
                        switch (Response){
        
                        case "URLException":
                           resp.setStatusCode("02");
                           resp.setStatus("URL TIDAK DIKENALI");
                        break;

                        case "IOException":
                          resp.setStatusCode("02");
                          resp.setStatus("SERVER NOT CONNECT");
                        break;
                        
                        }
                 
               
                resp = gson.fromJson(Response.toString(), ResponseLogin.class);
                
            if( resp.getStatusCode().equals("01")){

               
                request.setAttribute("message", resp.getStatus() + "  Silahkan coba Beberapa saat lagi");
                rd = request.getRequestDispatcher("webadmin/View/login_gagal.jsp");
                rd.forward(request, response);
               
               
                
            }else{
             
                    
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("token", resp.getToken());
                response.sendRedirect("Dashboard");
                
                System.out.println("ini token :" + resp.getToken() );

              
            }
               
                
                
            }else{
              
              rd = request.getRequestDispatcher("webadmin/View/login.jsp");
              rd.forward(request, response);
              
            }
              
              
        } catch (Exception e){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
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
