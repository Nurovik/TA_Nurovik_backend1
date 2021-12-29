<%-- 
    Document   : News
    Created on : Nov 27, 2021, 7:58:11 AM
    Author     : teguh
--%>

<%@page import="Utils.Nullconvert"%>
<%@page import="Models.News"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Utils.ConvertDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <div class="content-wrapper">
     <%
     Nullconvert convertstring = new Nullconvert();
             int page1 = convertstring.nullIntconvert(request.getParameter("page1"));// = IPAGE NO INISIAL PAGE NO 1
     %>
  
     
       <div class="row">
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <!--<h4 class="card-title"></h4>-->
                 <div class="card-title">
                  <button type="button" onclick="window.location.href='NewsController?go=news_add' "class=" btn btn-primary "> Add</button>
                 </div>
                  <p class="card-description">
                   PAGE <code><%
                       if(page1 ==0){
                       page1 = 1;
                       }
                          
                          
                   %><%=page1%> </code>
                  </p>
                  <div class="table-responsive">
                    <table class="table table-hover">
                      <thead>
                        <tr>
                          <th>
                            No
                          </th>
                          <th>
                            Title
                          </th>
                          <th>
                            Privew
                          </th>
                          <th>
                            Content
                          </th>
                          <th>
                            Date Created
                          </th>
                          <th>
                           StatusPublish
                          </th>
                           <th>
                            Action
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                   <% ArrayList newslist = (ArrayList)request.getAttribute("list"); %>
                   <% String baseurlcms = (String)request.getAttribute("baseurlcms"); %>
                   <% String statusdata = (String)request.getAttribute("statusdata"); %>
                   <% String status = (String)request.getAttribute("status"); %>
               
        
               <!--<div class="col-lg-8 col-sm-8 col-xs-12">-->
               <div class="col-md-12">
              
             
                  <div class="news-post-holder">
 
                       <%
                        int no = 0;
                        int news_id= 0;
                        String title = null;
                        String priview = null;
                        String image = null;
                        String userpost = null;
                        String datepost;
                        int statuspublish;
                       
                        
                      ConvertDate convert = new ConvertDate();

                        try {
                            
                           
                            
                            List<News> list;
                          
                           list = newslist;
                            for (News m : list) {
                                no = no + 1;
                                news_id = m.getId_news();
                                title = m.getTitle();
                                userpost = m.getUser();
                                priview = m.getPriview();
                                image =baseurlcms+ m.getImage() ;
                            
                                datepost = convert.convertlongtodatenews(m.getDatecreated());
                                statuspublish = m.getStatus();
                                

                    %>
                          
                        <tr>
                          <td class="py-1">
                           <%= no %>
                          </td>
                          <td>
                            <%= title %>
                          </td>
                          <td>
                            
                             
                              <%= priview %>
                            
                          </td>
                          <td>
                            <%= userpost %>
                          </td>
                          <td>
                               <%= datepost %>
                          </td>
                          
                           <td>
                            <%
                                if(statuspublish >0){
                                 
                                 
                                 %>
                                 <p class="text-success">Publish </p>
                                 
                                  <%}else{%>
                                   <p class="text-danger"> Not Publish </p>
                                  <%}%>
                          </td>
                          <td>

                                  <div class="dropdown">
                                   <button class="btn btn-dark dropdown-toggle" type="button" id="dropdownMenuButton7" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    Action
                                  </button>
                                    <ul class="dropdown-menu" role="menu">
                                      <!--<a href="" class="dropdown-item">View</a>-->
                                      <a href="NewsController?go=news_edit&amp;news_id=<%=news_id%>&page2=<%=page1 %>"class="dropdown-item"> Edit</a>
                                      <a href="NewsController?go=news_delete&amp;news_id=<%=news_id%>&page2=<%=page1 %>" class = "dropdown-item tombol-hapus">Delete</a>
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
  <%
      try{
             
             int totalNews= (Integer)request.getAttribute("totalnews");
             int showRows = 3 ; //(Integer)request.getAttribute("showRows"); 
             // int recordsPerpage = (Integer)request.getAttribute("recordsPerpage");
             int startResult = (Integer)request.getAttribute("startResult"); 
             int endResult = (Integer)request.getAttribute("endResult"); 
             
              int totalRecords = 3 ; 
//             if((totalNews %2) == 0){
//                totalRecords=5;
//             }else{
//                totalRecords=4;
//             }
//            harus showrows/2
            
            System.out.println(totalRecords);
//            System.out.println(totalNews %2);
            
             
            
    
    int cPageNo = convertstring.nullIntconvert(request.getParameter("cPageNo"));
    
                 
                 
             if (page1 == 0) {
                    page1 = 0;
                } else {
                    page1 = Math.abs((page1 - 1) * showRows);
                }
            
            %>
                <ul class="pagination pagination-sm no-margin pull-left">      
                          <!-- /.box-body -->
           <%
                try {
                    if (totalNews < (page1 + showRows)) {
                        endResult = totalNews ;
                    } else {
                        endResult = (page1 + showRows);
                    }
                    startResult = (page1 + 1);
                    totalNews = ((int) (Math.ceil((double) totalNews / showRows)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int i = 0;
                int cPage = 0;
                if (totalNews != 0) {
                    cPage = ((int) (Math.ceil((double) endResult / (totalRecords * showRows))));

                    int prePageNo = (cPage * totalRecords) - ((totalRecords - 1) + totalRecords);
                    if ((cPage * totalRecords) - (totalRecords) > 0) {
            %> 
           
            <li><a href="NewsController?page1=<%=(cPage * totalRecords) - (totalRecords) %>&cPageNo=<%=(cPage * totalRecords) - (totalRecords)%>" class="btn btn-outline-secondary">Previous| <%= (cPage * totalRecords) - (totalRecords - 1)-1 %></a></li>
            <%
                }
                for (i = ((cPage * totalRecords) - (totalRecords - 1)); i <= (cPage * totalRecords); i++) {
                    if (i == ((page1  / showRows) + 1)) {%>
            <li><a href="NewsController?page1=<%=i%>" class="btn btn-outline-secondary"><%=i%></a></li>
                <%
                } else if (i <= totalNews) {
                %>
            <li><a href="NewsController?page1=<%=i%>" class="btn btn-outline-secondary"><%=i%></a></li>
                <%
                        }
                    }
                    if (totalNews > totalRecords && i <= totalNews) {
                %>
            <li><a href="NewsController?page1=<%=i%>&cPageNo=<%=i%>" class="btn btn-outline-secondary">Next | <%= i %></a></li>
            <%
                
                    }
                }
}catch(Exception e){

}
               
            %>
            
                </ul> 
                  </div>
                </div>
           

              </div>
                      
                      
            </div>
          </div>
                      
                      
        </div>

