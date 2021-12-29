/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.JadwalDao;
import DAO.LigaDao;
import Models.Jadwal;
import ResponseServer.ResponseJadwal;
import ResponseServer.ResponseLiga;
import ResponseServer.ResponseStatus;
import ResponseServer.ResponsejadwalByid;
import Utils.ConvertDate;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
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
public class JadwalController extends HttpServlet {

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
        RequestDispatcher rd;
        PrintWriter out = response.getWriter();
  try  {
            
            /* TODO output your page here. You may use following sample code. */
            
             ResponseJadwal RESPONSE = new ResponseJadwal();
             JadwalDao jadwaldao= new JadwalDao();
             Jadwal ns = new Jadwal();
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
            RESPONSE = jadwaldao.GetAllpage(Integer.parseInt(page1), "idjadwal-desc","");
              
              if(RESPONSE.getStatusCode().equals("01")){
                  
              request.setAttribute("status", RESPONSE.getStatus());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              request.setAttribute("statusdata", RESPONSE.getStatusCode());
              request.setAttribute("halaman", "jadwal");
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
              request.setAttribute("totajadwal", RESPONSE.getTotal());
              request.setAttribute("startResult", startResult);
              request.setAttribute("endResult", endResult);
              request.setAttribute("list", RESPONSE.getListData());
              request.setAttribute("halaman", "jadwal");
              System.out.println( "ini status server : " + statusserver);
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              }
            }else if(go.equals("jadwal_add")){
                
            ResponseLiga RESPONSELIGA = new ResponseLiga();
            LigaDao mligaDao = new LigaDao();
            RESPONSELIGA = mligaDao.GetLiga();
                  
                request.setAttribute("listliga", RESPONSELIGA.getListData());
                request.setAttribute("halaman", "jadwaladd");
                rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
                rd.forward(request, response);
                
            }else if(go.equals("jadwal_save")){
                
                String team1 = "1";
                String team2 = request.getParameter("Team2");  
                String hari = request.getParameter("hari");
                String pukul = request.getParameter("pukul");
                String tempat = request.getParameter("tempat");
                String status = request.getParameter("status");
                String liga = request.getParameter("liga");
                String matchday = request.getParameter("matchday");
                
      
                            ResponseStatus resp =  new ResponseStatus();
                         String token = (String)request.getSession().getAttribute("token");
                         String username = (String)request.getSession().getAttribute("username");

                         resp = jadwaldao.savejadwal(team1, team2, liga,  hari, pukul, tempat, status, matchday, username, token);
                         if(resp.getStatusCode().equals("00")){

                           String redirect= "JadwalController?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else if(resp.getStatusCode().equals("02")){
                           String redirect= "JadwalController?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else{

                         response.sendRedirect("JadwalController?go=jadwal_add");
                         }
            
            }else if(go.equals("jadwal_delete")){
            String id_jadwal = request.getParameter("jadwal_id");
            ResponseStatus resp =  new ResponseStatus();
             String token = (String)request.getSession().getAttribute("token");
            String username = (String)request.getSession().getAttribute("username");
              resp = jadwaldao.Delete(Integer.parseInt(id_jadwal),username,token);
                                if(resp.getStatusCode().equals("00")){

                                  String redirect= "JadwalController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }else if(resp.getStatusCode().equals("02")){
                                  String redirect= "JadwalController?statuserrver={0}&statusdataserver={1}";
                                  String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                                  response.sendRedirect(alamatparam);

                                }
            
            }else if(go.equals("jadwal_edit")){
                
               String id_jadwal= request.getParameter("jadwal_id");
               ResponsejadwalByid resp = new ResponsejadwalByid();
                    String token = (String)request.getSession().getAttribute("token");
            String username = (String)request.getSession().getAttribute("username");
               resp =jadwaldao.GetJadwalByid(Integer.parseInt(id_jadwal),username,token);
              
              if(resp.getStatusCode().equals("01")){
                  
              request.setAttribute("status", resp.getStatus());
              request.setAttribute("statusdata", resp.getStatusCode());
              request.setAttribute("statusdata", resp.getStatusCode());
              request.setAttribute("halaman", "jadwal");
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");
              rd.forward(request, response);
              
              }else if(resp.getStatusCode().equals("02")){
                  
              request.setAttribute("status", resp.getStatus());
              rd = request.getRequestDispatcher("Views/servernotconnect.jsp");
              rd.forward(request, response);
              
              }else{
                  ConvertDate conv = new  ConvertDate();
                  
                  String date = conv.convertlongtodate(resp.getData().getHari());
                  String[] splited = date.split("\\s+");
                  String pukul = "";
                  String hari = "";
                  for(int i=0; i<splited.length; i++){
                      hari = splited[0];
                      pukul = splited[1];
                  }
                              ResponseLiga RESPONSELIGA = new ResponseLiga();
            LigaDao mligaDao = new LigaDao();
            RESPONSELIGA = mligaDao.GetLiga();
                  
              request.setAttribute("listliga", RESPONSELIGA.getListData());
              request.setAttribute("id", resp.getData().getId());
              request.setAttribute("team1", resp.getData().getTeam1());
              request.setAttribute("hari", hari);
              request.setAttribute("tempat", resp.getData().getTempat());
              request.setAttribute("status", resp.getData().getStatus());
              request.setAttribute("namaliga", resp.getData().getNamaliga());
              request.setAttribute("matchday", resp.getData().getMatchday());
              request.setAttribute("pukul", pukul);
//              request.setAttribute("baseurlcms", Constants.BaseUrlcms);

//                  System.out.println(hari);
//                  System.out.println(pukul);
                
   

//              request.setAttribute("list", RESPONSE.getListData());
                request.setAttribute("halaman", "jadwaledit");
              rd = request.getRequestDispatcher("webadmin/View/Main.jsp");;
              rd.forward(request, response);
              }
            
            
            
            
            }else if(go.equals("jadwal_editsave")){
                String id =  request.getParameter("id_jadwal"); 
                String team1 = "Universitas Budi Luhur";
                String team2 = request.getParameter("Team2");  
                String hari = request.getParameter("hari");
                String pukul = request.getParameter("pukul");
                String tempat = request.getParameter("tempat");
                String status = request.getParameter("status");
                String liga = request.getParameter("liga");
                String matchday = request.getParameter("matchday");
                
                  System.out.println(id);
                  System.out.println(team1);
                  System.out.println(team2);
                  System.out.println(hari);
                  System.out.println(pukul);
                  System.out.println(tempat);
                  System.out.println(status);
                  System.out.println(liga);
                  System.out.println(matchday);
                  
                   ResponseStatus resp =  new ResponseStatus();
                         String token = (String)request.getSession().getAttribute("token");
                         String username = (String)request.getSession().getAttribute("username");

                         resp = jadwaldao.editadwal(id,team1, team2, liga,  hari, pukul, tempat, status, matchday, username, token);
                         if(resp.getStatusCode().equals("00")){

                           String redirect= "JadwalController?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else if(resp.getStatusCode().equals("02")){
                           String redirect= "JadwalController?statuserrver={0}&statusdataserver={1}";
                           String alamatparam = MessageFormat.format(redirect, new Object[]{resp.getStatusCode(),resp.getStatus()});
                           response.sendRedirect(alamatparam);

                         }else{

                         response.sendRedirect("JadwalController?go=jadwal_add");
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
