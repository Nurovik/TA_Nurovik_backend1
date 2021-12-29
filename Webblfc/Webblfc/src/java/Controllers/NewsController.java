/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import DAO.NewsDao;
import Models.News;
import ResponseServer.ResponseNews;
import ResponseServer.ResponseNewsById;
import ResponseServer.ResponseStatus;
import Utils.Constants;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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

//@WebServlet(name = "FileUploadServlet", urlPatterns = { "/fileuploadservlet" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 100, // 100 MB
  maxFileSize = 1024 * 1024 * 100,      // 100 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)


public class NewsController extends HttpServlet {

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
            
             ResponseNews RESPONSE = new ResponseNews();
             NewsDao newsdao = new NewsDao();
             News ns = new News();
             String page1 = request.getParameter("page1");
              String go = request.getParameter("go");
              String statusserver = request.getParameter("statuserrver");
              String statusdataserver = request.getParameter("statusdataserver");
             if(page1 == null){
                 page1 = "1";
                }
        if(go == null){     
            int startResult = 0;
            int endResult = 0;
            RESPONSE = newsdao.GetAll(Integer.parseInt(page1), "id_news-desc","");
              
              if(RESPONSE.getStatusCode().equals("01")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              request.setAttribute("halaman", "cmsnews");
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              
              }else if(RESPONSE.getStatusCode().equals("02")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              rd = request.getRequestDispatcher("Views/servernotconnect.jsp");
              rd.forward(request, response);
              
              }else{
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusserver",statusserver);
              request.setAttribute("statusdataserver",statusdataserver);
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              request.setAttribute("totalnews", RESPONSE.getTotal());
              request.setAttribute("startResult", startResult);
              request.setAttribute("endResult", endResult);
              request.setAttribute("list", RESPONSE.getListData());
              request.setAttribute("halaman", "cmsnews");
              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
              System.out.println( "ini status server : " + statusserver);
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              }
            }else if(go.equals("news_add")){
                
                request.setAttribute("halaman", "newsadd");
                rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
                rd.forward(request, response);
                
            }else if(go.equals("news_edit")){
               String id_news = request.getParameter("news_id");
               ResponseNewsById resp = new ResponseNewsById();
               resp = newsdao.getid(Integer.parseInt(id_news));
              
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
              request.setAttribute("title", resp.getData().getTitle());
              request.setAttribute("id_news", resp.getData().getId_news());
              request.setAttribute("preview", resp.getData().getPriview());
              request.setAttribute("content", resp.getData().getContent());
              request.setAttribute("image", resp.getData().getImage());
              request.setAttribute("fullimage", Constants.BaseUrlcms+resp.getData().getImage());
//              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
           

//              request.setAttribute("list", RESPONSE.getListData());
              request.setAttribute("halaman", "newsedit");
              request.setAttribute("baseurlcms", Constants.BaseUrlcms);
            
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              }
            
            }else if(go.equals("news_delete")){
            String id_news = request.getParameter("news_id");
            ResponseStatus resp =  new ResponseStatus();
             String token = (String)request.getSession().getAttribute("token");
            String username = (String)request.getSession().getAttribute("username");
              resp = newsdao.Delete(Integer.parseInt(id_news),username,token);
                                if(resp.getStatusCode().equals("00")){

                                  String redirect= "NewsController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else if(resp.getStatusCode().equals("02")){
                                  String redirect= "NewsController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

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
               response.setContentType("text/html;charset=UTF-8");
             PrintWriter out = response.getWriter();
             
    
            String title = request.getParameter("title");
            String preview = request.getParameter("preview");
            String id_news = request.getParameter("id_news");
            String image = request.getParameter("image");
            String status = request.getParameter("status");


            
           if(id_news == null ){
            String content = request.getParameter("content");
            System.out.println("title : " + title);
            System.out.println("preview :  " + preview );

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
                          NewsDao newsdao = new NewsDao();
                         ResponseStatus resp =  new ResponseStatus();
                         String token = (String)request.getSession().getAttribute("token");
                         String username = (String)request.getSession().getAttribute("username");

                         resp = newsdao.postnews(title, content, preview, path, fileName,username,token,status);
                         if(resp.getStatusCode().equals("00")){

                           String redirect= "NewsController?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else if(resp.getStatusCode().equals("02")){
                           String redirect= "NewsController?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else{

                         response.sendRedirect("NewsController?go=news_add");
                         }



                     } catch (FileNotFoundException fne) {
                         writer.println("You either did not specify a file to upload or are "
                                 + "trying to upload a file to a protected or nonexistent "
                                 + "location.");
                         writer.println("<br/> ERROR: " + fne.getMessage());

                        Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, "Problems during file upload. Error: {0}", 
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
                   System.out.println("title : " + title);
                   System.out.println("preview :  " + preview );

               

              final Part filePart = request.getPart("file");
              final InputStream filePart2 = filePart.getInputStream();

                 
                  NewsDao newsdao = new NewsDao();
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

                  
                  resp = newsdao.puttnews(id_news,title, content, preview, path, fileName,username,token,status);
                  if(resp.getStatusCode().equals("00")){
                                
                    String redirect= "NewsController?statuserrver={0}&statusdataserver={1}";
                    String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                    response.sendRedirect(alamatparam);
                  
                  }else if(resp.getStatusCode().equals("02")){
                    String redirect= "NewsController?statuserrver={0}&statusdataserver={1}";
                    String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                    response.sendRedirect(alamatparam);
                    
                  }else{
                  
                  response.sendRedirect("NewsController?go=news_add");
                  }
                   
       
                   
              } catch (FileNotFoundException fne) {
//                 
                                 resp = newsdao.putnewsnomedia(id_news,title, content, preview, image ,username,token,status);
                                if(resp.getStatusCode().equals("00")){

                                  String redirect= "NewsController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else if(resp.getStatusCode().equals("02")){
                                  String redirect= "NewsController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else{

                                response.sendRedirect("NewsController?go=news_add");
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
