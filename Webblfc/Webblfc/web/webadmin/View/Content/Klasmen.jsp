<%-- 
    Document   : klasmen
    Created on : Nov 27, 2021, 7:58:11 AM
    Author     : teguh
--%>

<%@page import="Models.Klasmen"%>
<%@page import="Models.Pemain"%>
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
                  <button type="button" onclick="window.location.href='ControllerKlasmen?go=klasmenadd' "class=" btn btn-primary "> Add</button>
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
                          Gambar
                          </th>
                          <th>
                            Detail
                          </th>
                          
                      
                           <th>
                            Action
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                   <% ArrayList klasmenlist = (ArrayList)request.getAttribute("list"); %>
                   <% String baseurlcms = (String)request.getAttribute("baseurlcms"); %>
                   <% String statusdata = (String)request.getAttribute("statusdata"); %>
                   <% String status = (String)request.getAttribute("status"); %>
               
        
               <!--<div class="col-lg-8 col-sm-8 col-xs-12">-->
               <div class="col-md-12">
              
             
                  <div class="news-post-holder">
 
                       <%
                        int no = 0;
                        int id_klasmen= 0;
                        String namaliga = null;
                        String gambar = null;
                        String detail = null;
                    
                       
                        
                      ConvertDate convert = new ConvertDate();

                        try {
                            
                           
                            
                            List<Klasmen> list;
                          
                           list = klasmenlist;
                            for (Klasmen m : list) {
                                no = no + 1;
                                id_klasmen = m.getId_klasmen();
                                namaliga = m.getNamaliga();
                               
                                detail = m.getDetail();
                                gambar = Constants.BaseUrlcms+ m.getGambar();
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
                            <%= namaliga %>
                          </td>
                          <td>
                            <image src=" <%= gambar %>" >
                             
                            
                            
                          </td>
                          <td>
                              <%= detail %>
                          </td>
                         
                 
                          
                     
                          <td>

                                  <div class="dropdown">
                                   <button class="btn btn-dark dropdown-toggle" type="button" id="dropdownMenuButton7" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    Action
                                  </button>
                                    <ul class="dropdown-menu" role="menu">
                                      <!--<a href="" class="dropdown-item">View</a>-->
                                      <a href="ControllerKlasmen?go=klasmen_edit&amp;id_klasmen=<%=id_klasmen%>"class="dropdown-item"> Edit</a>
                                      <a href="ControllerKlasmen?go=klasmen_delete&amp;id_klasmen=<%=id_klasmen%>" class = "dropdown-item tombol-hapus">Delete</a>
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

