<%-- 
    Document   : Klasmen
    Created on : Nov 8, 2021, 8:30:25 PM
    Author     : teguh
--%>

<%@page import="Utils.Constants"%>
<%@page import="org.eclipse.jdt.internal.compiler.impl.Constant"%>
<%@page import="Utils.ConvertDate"%>
<%@page import="Models.Jadwal"%>
<%@page import="Models.Liga"%>
<%@page import="java.util.List"%>
<%@page import="Models.Klasmen"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
   <!-- Basic -->
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <!-- Mobile Metas -->
   <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
   <!-- Site Metas -->
   <title>Game Info</title>
   <meta name="keywords" content="">
   <meta name="description" content="">
   <meta name="author" content="">
   <!-- Site Icons -->
   <link rel="shortcut icon" href="" type="image/x-icon" />
   <link rel="apple-touch-icon" href="">
   <!-- Bootstrap CSS -->
   <link rel="stylesheet" href="css/bootstrap.min.css">
   <!-- Site CSS -->
   <link rel="stylesheet" href="css/style.css">
   <!-- Colors CSS -->
   <link rel="stylesheet" href="css/colors.css">
   <!-- ALL VERSION CSS -->	
   <link rel="stylesheet" href="css/versions.css">
   <!-- Responsive CSS -->
   <link rel="stylesheet" href="css/responsive.css">
   <!-- Custom CSS -->
   <link rel="stylesheet" href="css/custom.css">
   <!-- font family -->
   <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
   <!-- end font family -->
   <link rel="stylesheet" href="css/3dslider.css" />
   <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
   <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
   <script src="js/3dslider.js"></script>
   </head>
   <body class="game_info" data-spy="scroll" data-target=".header">
      <!-- LOADER -->
      <div id="preloader">
         <img class="preloader" src="images/loading-img.gif" alt="">
      </div>
      <!-- END LOADER -->
      <section id="top">
         <header>
            <div class="container">
               <div class="header-top">
                  <div class="row">
                     <div class="col-md-6">
                        <div class="full">
                           <div class="logo">
                              <a href="index.html"><img src="images/logo.png" alt="#" /></a>
                           </div>
                        </div>
                     </div>
                     <div class="col-md-6">
                        <div class="right_top_section">
                           <!-- social icon -->
<!--                           <ul class="social-icons pull-left">
                              <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                              <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                              <li><a class="youtube" href="#"><i class="fa fa-youtube-play"></i></a></li>
                              <li><a class="pinterest" href="#"><i class="fa fa-pinterest-p"></i></a></li>
                           </ul>-->
                           <!-- end social icon -->
                           <!-- button section -->
                           <ul class="login">
                              <li class="login-modal">
                                 <a href="LoginController" class="login"><i class="fa fa-user"></i>Login</a>
                              </li>
<!--                              <li>
                                 <div class="cart-option">
                                    <a href="#"><i class="fa fa-shopping-cart"></i>Register</a>
                                 </div>
                              </li>-->
                           </ul>
                           <!-- end button section -->
                        </div>
                     </div>
                  </div>
               </div>
               <div class="header-bottom">
                  <div class="row">
                     <div class="col-md-12">
                        <div class="full">
                           <div class="main-menu-section">
                              <div class="menu">
                                 <nav class="navbar navbar-inverse">
                                    <div class="navbar-header">
                                       <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
                                       <span class="sr-only">Toggle navigation</span>
                                       <span class="icon-bar"></span>
                                       <span class="icon-bar"></span>
                                       <span class="icon-bar"></span>
                                       </button>
                                       <a class="navbar-brand" href="#">Menu</a>
                                    </div>
                                    <div class="collapse navbar-collapse js-navbar-collapse">
                                       <ul class="nav navbar-nav">
                                         <li class="active"><a href="CmsNewsController">News</a></li>
                                          <li><a href="CmsPemainController">Team</a></li>
                                          <li><a href="KlasmenController">Competition</a></li>
                                          <!--<li><a href="about.html">Galery</a></li>-->
                                     
                                       </ul>
                                    </div>
                                    <!-- /.nav-collapse -->
                                 </nav>
<!--                                 <div class="search-bar">
                                    <div id="imaginary_container">
                                       <div class="input-group stylish-input-group">
                                          <input type="text" class="form-control"  placeholder="Search" >
                                          <span class="input-group-addon">
                                          <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>  
                                          </span>
                                       </div>
                                    </div>
                                 </div>-->
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </header>
         <div class="inner-page-banner">
            <div class="container">
            </div>
         </div>
         <div class="inner-information-text">
            <div class="container">
               <h3>Competition</h3>
             
            </div>
         </div>
      </section>
      <section id="contant" class="contant main-heading team">
         <div class="row">
            <div class="container">
                 <% ArrayList klasmenlist = (ArrayList)request.getAttribute("listklasmen"); %>
                   <% String baseurlcms = (String)request.getAttribute("baseurlcms"); %>
                   <% ArrayList listjadwal = (ArrayList)request.getAttribute("listjadwal"); %>
                 <div class="col-md-12">
              
             
                  <div class="news-post-holder">
 
                       <%
                        int no = 0;
                        int klasmen_id= 0;
                        String namaliga = null;
                        String detail = null;
                        String gambar = null;
                   
                   
                       
                        
                      ConvertDate convert = new ConvertDate();

                        try {
                            
                           
                            
                            List<Klasmen> list;
                          
                           list = klasmenlist;
                            for (Klasmen m : list) {
                                no = no + 1;
                                klasmen_id = m.getId_klasmen();
                                namaliga = m.getNamaliga();
                               
                                detail = m.getDetail();
                                gambar =baseurlcms+ m.getGambar();
                            
                            
                                

                    %>
             <div class="news-post-widget">
                        <!--<img class="img-responsive" src="images/img-01_002.jpg" alt="">-->
                        <img class="img-responsive" src=<%= gambar%> alt="">
                        <div class="news-post-detail">
                           <span class="date"></span>
                           <h2><%= detail%>   | <%= namaliga%></h2>
                           
                        </div>
                     </div>
                     
                         <%
                            }
                        } catch (Exception e) {

                            %>
                                     <div class="container">
                                    <div class="row">
                                       <div class="col-md-12">
                                          <div class="full">
                                             <div class="main-heading sytle-2">
                                                <h2>No Klasmen Found</h2>
                                                
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                            <%
//                            e.printStackTrace();
                        }
                    %>
              
                      
                        
              
            </div>
                         
                <div class="container">
                    
                    
                    <%  try {
                     
                                int matchday;
                                String team1 = null;
                                String team2 = null;
                                Integer goal1 = 0;
                                Integer goal2 = 0;
                                String status = null;
                                String datepost = null;
                                
                           
                            
                            List<Jadwal> list;
                          
                           list = listjadwal;
                            for (Jadwal m : list) {
                                 no = no +1;
                                 matchday = m.getMatchday();
                                team1  = m.getTeam1();
                                team2  = m.getTeam2();
                                goal1  = m.getGoal1();
                                goal2  = m.getGoal2();
                                status = m.getStatus();
                                datepost = convert.convertlongtodatenews(m.getHari());
                            
                               

                    %>
                  <div class="row">
                    <div class="col-sm">

                      <div class="card" style="border: none">
                          <div class="row" style="border: 1px solid grey;">
                              <div class="col-md-2">
                                  <div class="text-center">Matchday Ke - <%= matchday %></div> 
                              </div>
                              <div class="col-md-8" style="border-left: 1px solid grey;border-right: 1px solid grey;">
                                  <br> <%= team1 %>   
                                  <br> VS   
                                  <br><%= team2 %> 
                                  <br><%= goal1 %> - <%= goal2 %>
<!--                                  <br> Untuk keungulan team 1-->
                              </div>
                              <div class="col-md-2">
                               <%= datepost %>
                               <br><% if (status.equals("FT")){%>
                                   
                                  <h3 style="color: red"> <%=status%> </h3> 
                               
                              <% }else{%>
                               
                               
                             <h3 style="color: grey"> <%=status%> </h3> 
                               
                               <%}%>
                              </div>
                          </div>
                      </div>
                    </div>
                  </div>
                  
                                    <%
                            }
                        } catch (Exception e) {
                               %>
                           
                         
                                   
                                   <td colspan="10"> NO Jadwal Found</td>
                              
                            <%
                        }
                    %>
                </div>
             
         </div>
                                       
                         
      </section>
      <footer id="footer" class="footer">
         <div class="container">
            <div class="row">
               <div class="col-md-4">
                  <div class="full">
                     <div class="footer-widget">
                        <div class="footer-logo">
                           <a href="#"><img src="images/footer-logo.png" alt="#" /></a>
                        </div>
                        <p>Most of our events have hard and easy route choices as we are always keen to encourage new riders.</p>
                        <ul class="social-icons style-4 pull-left">
                           <li><a class="facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                           <li><a class="twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                           <li><a class="youtube" href="#"><i class="fa fa-youtube-play"></i></a></li>
                           <li><a class="pinterest" href="#"><i class="fa fa-pinterest-p"></i></a></li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="col-md-2">
                  <div class="full">
                     <div class="footer-widget">
                        <h3>Menu</h3>
                        <ul class="footer-menu">
                           <li><a href="about.html">About Us</a></li>
                           <li><a href="team.html">Our Team</a></li>
                           <li><a href="news.html">Latest News</a></li>
                           <li><a href="matche.html">Recent Matchs</a></li>
                           <li><a href="blog.html">Our Blog</a></li>
                           <li><a href="contact.html">Contact Us</a></li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="col-md-3">
                  <div class="full">
                     <div class="footer-widget">
                        <h3>Contact us</h3>
                        <ul class="address-list">
                           <li><i class="fa fa-map-marker"></i> Lorem Ipsum is simply dummy text of the printing..</li>
                           <li><i class="fa fa-phone"></i> 123 456 7890</li>
                           <li><i style="font-size:20px;top:5px;" class="fa fa-envelope"></i> demo@gmail.com</li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="col-md-3">
                  <div class="full">
                     <div class="contact-footer">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d120615.72236587871!2d73.07890527988283!3d19.140910987164396!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sin!4v1527759905404" width="600" height="350" frameborder="0" style="border:0" allowfullscreen></iframe>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="footer-bottom">
            <div class="container">
               <p>Copyright © 2018 GameInfo.All rights reserved.</p>
            </div>
         </div>
      </footer>
      <a href="#home" data-scroll class="dmtop global-radius"><i class="fa fa-angle-up"></i></a>
      <!-- ALL JS FILES -->
      <script src="js/all.js"></script>
      <!-- ALL PLUGINS -->
      <script src="js/custom.js"></script>
      

   </body>
</html>
