/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.KlasmenDao;
import DAO.LigaDao;
import ResponseServer.ResponseKlasmen;
import ResponseServer.ResponseKlasmenByid;
import ResponseServer.ResponseLiga;
import ResponseServer.ResponseStatus;
import Utils.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author teguh
 */

@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 100, // 100 MB
  maxFileSize = 1024 * 1024 * 100,      // 100 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ControllerKlasmen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
           private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    Logger.getLogger(NewsController.class.getName()).log(Level.INFO, "Part Header = {0}", partHeader);
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd;
        PrintWriter out = response.getWriter();
        try  {
            /* TODO output your page here. You may use following sample code. */
            ResponseKlasmen RESPONSE = new ResponseKlasmen();
             KlasmenDao klasmendao = new KlasmenDao();
           String go = request.getParameter("go");
               String statusserver = request.getParameter("statuserrver");
              String statusdataserver = request.getParameter("statusdataserver");
             
             RESPONSE = klasmendao.GetKlasmen();
        
            if(go == null){       

             
             if(RESPONSE.getStatusCode().equals("01")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              rd = request.getRequestDispatcher("Views/cms/Kompetisi.jsp");
              rd.forward(request, response);
              
              }else if(RESPONSE.getStatusCode().equals("02")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              rd = request.getRequestDispatcher("Views/cms/Kompetisi.jsp");
              rd.forward(request, response);
              
              }else{
                  
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              request.setAttribute("list", RESPONSE.getListData());
              request.setAttribute("statusserver",statusserver);
              request.setAttribute("statusdataserver",statusdataserver);
//              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
                  request.setAttribute("halaman", "klasmen");
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");;
              rd.forward(request, response);
              }
             
             
            }else if(go.equals("klasmenadd")){
                
            ResponseLiga RESPONSELIGA = new ResponseLiga();
            LigaDao mligaDao = new LigaDao();
            RESPONSELIGA = mligaDao.GetLiga();
                  
                request.setAttribute("listliga", RESPONSELIGA.getListData());
                request.setAttribute("halaman", "klasmenadd");
                rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
                rd.forward(request, response);
                
            }else if(go.equals("klasmen_delete")){
            String id_klasmen = request.getParameter("id_klasmen");
            ResponseStatus resp =  new ResponseStatus();
             String token = (String)request.getSession().getAttribute("token");
            String username = (String)request.getSession().getAttribute("username");
              resp = klasmendao.Delete(Integer.parseInt(id_klasmen),username,token);
                                if(resp.getStatusCode().equals("00")){

                                  String redirect= "ControllerKlasmen?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else if(resp.getStatusCode().equals("02")){
                                  String redirect= "ControllerKlasmen?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }
            
            }else if(go.equals("klasmen_edit")){
               String id_klasmen = request.getParameter("id_klasmen");
                ResponseKlasmenByid resp = new ResponseKlasmenByid();
               resp = klasmendao.getid(Integer.parseInt(id_klasmen));
              
              if(resp.getStatusCode().equals("01")){
                  
              request.setAttribute("status", resp.getStatus());
              request.setAttribute("statusdata", resp.getStatusCode());
              request.setAttribute("statusdata", resp.getStatusCode());
              request.setAttribute("halaman", "cmsnews");
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              
              }else if(resp.getStatusCode().equals("02")){
                  
              request.setAttribute("status", resp.getStatus());
              rd = request.getRequestDispatcher("Views/servernotconnect.jsp");
              rd.forward(request, response);
              
              }else{
                  
                   ResponseLiga RESPONSELIGA = new ResponseLiga();
            LigaDao mligaDao = new LigaDao();
            RESPONSELIGA = mligaDao.GetLiga();
                  
              request.setAttribute("listliga", RESPONSELIGA.getListData());
              request.setAttribute("id_klasmen", resp.getData().getId_klasmen());
              request.setAttribute("namaliga", resp.getData().getNamaliga());
              request.setAttribute("gambar", resp.getData().getGambar());
              request.setAttribute("detail", resp.getData().getDetail());
              request.setAttribute("fullimage", Constants.BaseUrlcms+resp.getData().getGambar());
//              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
           

//              request.setAttribute("list", RESPONSE.getListData());
              request.setAttribute("halaman", "klasmenedit");
              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
            
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              }
            }
         } catch (Exception e){
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, e);
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
     
            String liga = request.getParameter("liga");
            String Detail = request.getParameter("Detail");
            String nopunggung = request.getParameter("nopunggung");
            String idklasmen= request.getParameter("idklasmen");
            String gambar= request.getParameter("gambar");
        
         


            
           if(idklasmen == null ){
           
         

             String path = Constants.Uploadfile;

              final Part filePart = request.getPart("file");
              final String fileName = getFileName(filePart);

              OutputStream out1 = null;
              InputStream filecontent = null;
              final PrintWriter writer = response.getWriter();
                        try {
                         out1 = new FileOutputStream(new File(path + File.separator
                                 + fileName));
                         filecontent = filePart.getInputStream();

                         int read = 0;
                         final byte[] bytes = new byte[1024];

                         while ((read = filecontent.read(bytes)) != -1) {
                             out1.write(bytes, 0, read);
                         }
                          KlasmenDao klasmendao = new KlasmenDao();
                         ResponseStatus resp =  new ResponseStatus();
                         String token = (String)request.getSession().getAttribute("token");
                         String username = (String)request.getSession().getAttribute("username");

                         resp = klasmendao.postliga(liga, Detail, path, fileName,username,token);
                         if(resp.getStatusCode().equals("00")){

                           String redirect= "ControllerKlasmen?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else if(resp.getStatusCode().equals("02")){
                           String redirect= "ControllerKlasmen?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else{

                         response.sendRedirect("ControllerKlasmen?go=klasmen_add");
                         }



                     } catch (FileNotFoundException fne) {
                         writer.println("You either did not specify a file to upload or are "
                                 + "trying to upload a file to a protected or nonexistent "
                                 + "location.");
                         writer.println("<br/> ERROR: " + fne.getMessage());

                        Logger.getLogger(LigaController.class.getName()).log(Level.SEVERE, "Problems during file upload. Error: {0}", 
                                 new Object[]{fne.getMessage()});
                     } finally {
                         if (out != null) {
                             out.close();
                         }
                         if (filecontent != null) {
                             filecontent.close();
                         }
                         if (writer != null) {
                             writer.close();
                         }

                  
//                  System.out.println("test4");
                  
              }
                      
                      
                  }else{
                    String content = request.getParameter("content");
             

               

              final Part filePart = request.getPart("file");
              final InputStream filePart2 = filePart.getInputStream();

                 
                 KlasmenDao klasmenDao = new KlasmenDao();
                 ResponseStatus resp =  new ResponseStatus();
                  String token = (String)request.getSession().getAttribute("token");
                  String username = (String)request.getSession().getAttribute("username");
                              
                      final String fileName = getFileName(filePart);
                        OutputStream out1 = null;
                       InputStream filecontent = null;
                       final PrintWriter writer = response.getWriter();
                        String path = Constants.Uploadfile;
                      try {
                     out1 = new FileOutputStream(new File(path + File.separator
                             + fileName));
                     filecontent = filePart.getInputStream();

                     int read = 0;
                     final byte[] bytes = new byte[1024];

                     while ((read = filecontent.read(bytes)) != -1) {
                         out1.write(bytes, 0, read);
                  }

                  
                  resp = klasmenDao.putklasmen(idklasmen,liga, Detail, path, fileName,username,token);
                  if(resp.getStatusCode().equals("00")){
                                
                    String redirect= "ControllerKlasmen?statuserrver={0}&statusdataserver={1}";
                    String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                    response.sendRedirect(alamatparam);
                  
                  }else if(resp.getStatusCode().equals("02")){
                    String redirect= "ControllerKlasmen?statuserrver={0}&statusdataserver={1}";
                    String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                    response.sendRedirect(alamatparam);
                    
                  }else{
                  
                  response.sendRedirect("ControllerKlasmen?go=klasmen_edit");
                  }
                   
       
                   
              } catch (FileNotFoundException fne) {
//                 
                                 resp = klasmenDao.putklasmenomedia(idklasmen,liga,Detail, gambar ,username,token);
                                if(resp.getStatusCode().equals("00")){

                                  String redirect= "ControllerKlasmen?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else if(resp.getStatusCode().equals("02")){
                                  String redirect= "ControllerKlasmen?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else{

                                response.sendRedirect("ControllerKlasmen?go=klasmen_edit");
                                }
              } finally {
                  if (out != null) {
                      out.close();
                  }
                  if (filecontent != null) {
                      filecontent.close();
                  }
                  if (writer != null) {
                      writer.close();
                  }

                  
                    }
                      

                  

              
                  
        
                
                  }
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
