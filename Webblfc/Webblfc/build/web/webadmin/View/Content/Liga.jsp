<%-- 
    Document   : News
    Created on : Nov 27, 2021, 7:58:11 AM
    Author     : teguh
--%>

<%@page import="Utils.Constants"%>
<%@page import="Models.Liga"%>
<%@page import="Utils.Nullconvert"%>
<%@page import="Models.News"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Utils.ConvertDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <div class="content-wrapper">

  
     
       <div class="row">
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <!--<h4 class="card-title"></h4>-->
                 <div class="card-title">
                  <button type="button" onclick="window.location.href='LigaController?go=liga_add' "class=" btn btn-primary "> Add</button>
                 </div>
                  <p class="card-description">
                
                  </p>
                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead>
                        <tr>
                          <th>
                            No
                          </th>
                          <th>
                            Nama Liga
                          </th>
                          <th>
                           Jumlah Team
                          </th>
                          <th>
                            Logo
                          </th>
                      
                   
                           <th>
                            Action
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                   <% ArrayList ligalist = (ArrayList)request.getAttribute("list"); %>
                   <% String baseurlcms = (String)request.getAttribute("baseurlcms"); %>
                   <% String statusdata = (String)request.getAttribute("statusdata"); %>
                   <% String status = (String)request.getAttribute("status"); %>
               
        
               <!--<div class="col-lg-8 col-sm-8 col-xs-12">-->
               <div class="col-md-12">
              
             
                  <div class="news-post-holder">
 
                       <%
                        int no = 0;
                        int idliga= 0;
                        String Namaliga = null;
                        Integer jumlahteam = null;
                        String logo = null;
                    
                       
                        
                      ConvertDate convert = new ConvertDate();

                        try {
                            
                           
                            
                            List<Liga> list;
                          
                           list = ligalist;
                            for (Liga m : list) {
                                no = no + 1;
                                idliga = m.getIdliga();
                                Namaliga = m.getNamaliga();
                                jumlahteam = m.getJumlahteam();
                                logo = Constants.BaseUrlcms+ m.getLogo();
//                                image =baseurlcms+ m.getImage() ;
//                            
//                                datepost = convert.convertlongtodatenews(m.getDatecreated());
//                                statuspublish = m.getStatus();
//                                

                    %>
                          
                        <tr>
                          <td class="py-1">
                           <%= no %>
                          </td>
                          <td>
                            <%= Namaliga %>
                          </td>
                          <td>
                            
                             
                              <%= jumlahteam %>
                            
                          </td>
                          <td>
                              <image src=" <%= logo %>" >
                          </td>
                 
                          
                     
                          <td>

                                  <div class="dropdown">
                                   <button class="btn btn-dark dropdown-toggle" type="button" id="dropdownMenuButton7" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    Action
                                  </button>
                                    <ul class="dropdown-menu" role="menu">
                                      <!--<a href="" class="dropdown-item">View</a>-->
                                      <a href="LigaController?go=liga_edit&amp;id_liga=<%=idliga%>"class="dropdown-item"> Edit</a>
                                      <a href="LigaController?go=liga_delete&amp;id_liga=<%=idliga%>" class = "dropdown-item tombol-hapus">Delete</a>
                                  </div>

                                      
                            </td>                                   
                        </tr>
                        
                     
                         <%
                            }
                        } catch (Exception e) {

                            %>
                            <td colspan="6" > <%=status%> </td>
                            <%
//                            e.printStackTrace();
                        }
                    %>
                      </tbody>
                      
                      
                    </table>
        </div>
                </div>
           

              </div>
                      
                      
            </div>
          </div>
                      
                      
        </div>

