/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.PemainDao;
import ResponseServer.ResponsePemain;
import ResponseServer.ResponseStatus;
import ResponseServer.ResponsepemainById;
import Utils.Constants;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;

/**
 *
 * @author teguh
 */
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 100, // 100 MB
  maxFileSize = 1024 * 1024 * 100,      // 100 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class PemainController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        RequestDispatcher rd;
     try  {
            /* TODO output your page here. You may use following sample code. */
            
             String page1 = request.getParameter("page1");
              String go = request.getParameter("go");
              String statusserver = request.getParameter("statuserrver");
              String statusdataserver = request.getParameter("statusdataserver");
                 
            
            ResponsePemain RESPONSE = new ResponsePemain();
            PemainDao mpemaindao = new PemainDao();
            if(go == null){    
            RESPONSE = mpemaindao.GetAll();
            
            if(RESPONSE.getStatusCode().equals("01")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              
              request.setAttribute("halaman", "pemain");
//              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              
              }else if(RESPONSE.getStatusCode().equals("02")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("halaman", "pemain");
//              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              
              }else{
                  
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              request.setAttribute("statusserver",statusserver);
              request.setAttribute("statusdataserver",statusdataserver);
              request.setAttribute("list", RESPONSE.getListData());
              request.setAttribute("halaman", "pemain");
               System.out.println( "ini status server : " + statusserver);
//              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              }
            }else if(go.equals("pemain_add")){
                
                request.setAttribute("halaman", "pemaindd");
                rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
                rd.forward(request, response);
//                
            }else if(go.equals("pemain_delete")){
            String idpemain= request.getParameter("idpemain");
            ResponseStatus resp =  new ResponseStatus();
             String token = (String)request.getSession().getAttribute("token");
            String username = (String)request.getSession().getAttribute("username");
            resp = mpemaindao.Delete(Integer.parseInt(idpemain),username,token);
                                if(resp.getStatusCode().equals("00")){

                                  String redirect= "PemainController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else if(resp.getStatusCode().equals("02")){
                                  String redirect= "PemainController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }
            
            }else if(go.equals("pemain_edit")){
               String idpemain= request.getParameter("idpemain");
                ResponsepemainById resp = new ResponsepemainById();
               resp = mpemaindao.getid(Integer.parseInt(idpemain));
              
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
              request.setAttribute("namapemain", resp.getData().getNama());
              request.setAttribute("idpemain", resp.getData().getId());
              request.setAttribute("nopunggung", resp.getData().getNopunggung());
              request.setAttribute("fakultas", resp.getData().getFakultas());
              request.setAttribute("image", resp.getData().getImage());
              request.setAttribute("fullimage", Constants.BaseUrlcms+resp.getData().getImage());
//              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
           

//              request.setAttribute("list", RESPONSE.getListData());
              request.setAttribute("halaman", "pemainedit");
              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
            
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              }
            }
            
        } catch (Exception e){
            Logger.getLogger(LigaController.class.getName()).log(Level.SEVERE, null, e);
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
            String nama = request.getParameter("namapemain");
            String fakultas = request.getParameter("fakultas");
            String nopunggung = request.getParameter("nopunggung");
            String idpemain= request.getParameter("idpemain");
            String image = request.getParameter("image");
         


            
           if(idpemain == null ){
            String content = request.getParameter("content");
         

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
                          PemainDao pemainDao = new PemainDao();
                         ResponseStatus resp =  new ResponseStatus();
                         String token = (String)request.getSession().getAttribute("token");
                         String username = (String)request.getSession().getAttribute("username");

                         resp = pemainDao.postPemain(nama, fakultas,nopunggung, path, fileName,username,token);
                         if(resp.getStatusCode().equals("00")){

                           String redirect= "PemainController?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else if(resp.getStatusCode().equals("02")){
                           String redirect= "PemainController?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else{

                         response.sendRedirect("PemainController?go=pemain_add");
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

                 
                 PemainDao pemainDao = new PemainDao();
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

                  
                  resp = pemainDao.puttPemain(idpemain,nama,fakultas, nopunggung, path, fileName,username,token);
                  if(resp.getStatusCode().equals("00")){
                                
                    String redirect= "PemainController?statuserrver={0}&statusdataserver={1}";
                    String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                    response.sendRedirect(alamatparam);
                  
                  }else if(resp.getStatusCode().equals("02")){
                    String redirect= "PemainController?statuserrver={0}&statusdataserver={1}";
                    String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                    response.sendRedirect(alamatparam);
                    
                  }else{
                  
                  response.sendRedirect("PemainController?go=pemain_edit");
                  }
                   
       
                   
              } catch (FileNotFoundException fne) {
//                 
                                 resp = pemainDao.putpemainnomedia(idpemain,nama,fakultas, nopunggung, image ,username,token);
                                if(resp.getStatusCode().equals("00")){

                                  String redirect= "PemainController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else if(resp.getStatusCode().equals("02")){
                                  String redirect= "PemainController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else{

                                response.sendRedirect("PemainController?go=pemain_edit");
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
