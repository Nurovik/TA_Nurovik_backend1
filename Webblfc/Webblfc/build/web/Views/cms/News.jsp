<%-- 
    Document   : News
    Created on : Oct 23, 2021, 7:48:16 PM
    Author     : teguh
--%>

<%@page import="Utils.Nullconvert"%>
<%@page import="java.util.List"%>
<%@page import="Models.News"%>
<%@page import="Utils.ConvertDate"%>
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
<!--                                          <li><a href="news.html">News</a></li>
                                          <li><a href="blog.html">Blog</a></li>
                                          <li><a href="contact.html">contact</a></li>-->
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
         <div class="full-slider">
            <div id="carousel-example-generic" class="carousel slide">
               <!-- Indicators -->
               <ol class="carousel-indicators">
                  <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                  <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                  <li data-target="#carousel-example-generic" data-slide-to="2"></li>
               </ol>
               <!-- Wrapper for slides -->
               <div class="carousel-inner" role="listbox">
                  <!-- First slide -->
                  <div class="item active deepskyblue" data-ride="carousel" data-interval="5000">
                     <div class="carousel-caption">
                        <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12"></div>
                        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                           <div class="slider-contant" data-animation="animated fadeInRight">
                              <h3>If you Don’t Practice<br>You <span class="color-yellow">Don’t Derserve</span><br>to win!</h3>
                              <p>If you use this site regularly and would like to help keep the site on the Internet,<br>
                                 please consider donating a small sum to help pay..
                              </p>
                              <!--<button class="btn btn-primary btn-lg">Read More</button>-->
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- /.item -->
                  <!-- Second slide -->
                  <div class="item skyblue" data-ride="carousel" data-interval="5000">
                     <div class="carousel-caption">
                        <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12"></div>
                        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                           <div class="slider-contant" data-animation="animated fadeInRight">
                              <h3>If you Don’t Practice<br>You <span class="color-yellow">Don’t Derserve</span><br>to win!</h3>
                              <p>You can make a case for several potential winners of<br>the expanded European Championships.</p>
                              <!--<button class="btn btn-primary btn-lg">Button</button>-->
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- /.item -->
                  <!-- Third slide -->
                  <div class="item darkerskyblue" data-ride="carousel" data-interval="5000">
                     <div class="carousel-caption">
                        <div class="col-lg-7 col-md-7 col-sm-12 col-xs-12"></div>
                        <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
                           <div class="slider-contant" data-animation="animated fadeInRight">
                              <h3>If you Don’t Practice<br>You <span class="color-yellow">Don’t Derserve</span><br>to win!</h3>
                              <p>You can make a case for several potential winners of<br>the expanded European Championships.</p>
                              <!--<button class="btn btn-primary btn-lg">Button</button>-->
                           </div>
                        </div>
                     </div>
                  </div>
                  <!-- /.item -->
               </div>
               <!-- /.carousel-inner -->
               <!-- Controls -->
               <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
               <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
               <span class="sr-only">Previous</span>
               </a>
               <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
               <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
               <span class="sr-only">Next</span>
               </a>
            </div>
            <!-- /.carousel -->
<!--            <div class="news">
               <div class="container">
                  <div class="heading-slider">
                     <p class="headline"><i class="fa fa-star" aria-hidden="true"></i> Top Headlines :</p>
                     made by vipul mirajkar thevipulm.appspot.com
                     <h1>
                         
                         top headlines
                     <a href="" class="typewrite" data-period="2000" data-type='[ "Contrary to popular belief, Lorem Ipsum is not simply random text.", "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."]'>
                     <span class="wrap"></span>
                     </a>
                     </h1	   
                     <span class="wrap"></span>
                     </a>
                  </div>
               </div>
            </div>-->
         </div>
      </section>
<!--      <div class="matchs-info">
         <div class="col-md-6 col-sm-6 col-xs-12">
            <div class="row">
               <div class="full">
                  <div class="matchs-vs">
                     <div class="vs-team">
                        <div class="team-btw-match">
                           <ul>
                              <li>
                                 <img src="images/img-03.png" alt="">
                                 <span>Footbal Team</span>
                              </li>
                              <li class="vs"><span>vs</span></li>
                              <li>
                                 <img src="images/img-04.png" alt="">
                                 <span>Super Team Club</span>
                              </li>
                           </ul>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-md-6 col-sm-6 col-xs-12">
            <div class="row">
               <div class="full">
                  <div class="right-match-time">
                     <h2>Next Match</h2>
                     <ul id="countdown-1" class="countdown">
                        <li><span class="days">15 </span>Day </li>
                        <li><span class="hours">5 </span>Hours </li>
                        <li><span class="minutes">25 </span>Minutes </li>
                        <li><span class="seconds">10 </span>Seconds </li>
                     </ul>
                     <span>12/02/2017 /19:00pm</span>
                  </div>
               </div>
            </div>
         </div>
      </div>-->
      <section id="contant" class="contant">
         <div class="container">
            <div class="row">
               <!--<div class="col-lg-4 col-sm-4 col-xs-12">-->
<!--               <div class="col-md-12">
                  <h4>Points Table</h4>
                  <aside id="sidebar" class="left-bar">
                     <div class="feature-matchs">
                        <table class="table table-bordered table-hover">
                           <thead>
                              <tr>
                                 <th>#</th>
                                 <th>Team</th>
                                 <th>P</th>
                                 <th>W</th>
                                 <th>L</th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr>
                                 <td>1</td>
                                 <td><img src="images/img-01_004.png" alt="">Liverpool</td>
                                 <td>10</td>
                                 <td>12</td>
                                 <td>20</td>
                              </tr>
                              <tr>
                                 <td>2</td>
                                 <td><img src="images/img-02_002.png" alt="">Chelsea</td>
                                 <td>10</td>
                                 <td>12</td>
                                 <td>20</td>
                              </tr>
                              <tr>
                                 <td>3</td>
                                 <td><img src="images/img-03_003.png" alt="">Norwich City</td>
                                 <td>20</td>
                                 <td>15</td>
                                 <td>20</td>
                              </tr>
                              <tr>
                                 <td>4</td>
                                 <td><img src="images/img-04_002.png" alt="">West Brom</td>
                                 <td>60</td>
                                 <td>10</td>
                                 <td>60</td>
                              </tr>
                              <tr>
                                 <td>5</td>
                                 <td><img src="images/img-05.png" alt="">sunderland</td>
                                 <td>30</td>
                                 <td>06</td>
                                 <td>30</td>
                              </tr>
                              <tr>
                                 <td>1</td>
                                 <td><img src="images/img-01_004.png" alt="">Liverpool</td>
                                 <td>10</td>
                                 <td>12</td>
                                 <td>20</td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </aside>
                  <div class="content-widget top-story" style="background: url(images/top-story-bg.jpg);">
                     <div class="top-stroy-header">
                        <h2>Top Soccer Headlines Story <a href="#" class="fa fa-fa fa-angle-right"></a></h2>
                        <span class="date">July 05, 2017</span>
                        <h2>Other Headlines</h2>
                     </div>
                     <ul class="other-stroies">
                        <li><a href="#">Wenger Vardy won't start</a></li>
                        <li><a href="#">Evans: Vardy just</a></li>
                        <li><a href="#">Pires and Murray </a></li>
                        <li><a href="#">Okazaki backing</a></li>
                        <li><a href="#">Wolfsburg's Rodriguez</a></li>
                        <li><a href="#">Jamie Vardy compared</a></li>
                        <li><a href="#">Arsenal target Mkhitaryan</a></li>
                        <li><a href="#">Messi wins libel case.</a></li>
                     </ul>
                  </div>
               </div>-->
                  <% ArrayList newslist = (ArrayList)request.getAttribute("list"); %>
                   <% String baseurlcms = (String)request.getAttribute("baseurlcms"); %>
                   <% String statusdata = (String)request.getAttribute("statusdata"); %>
                   <% String status = (String)request.getAttribute("status"); %>
               
               <% if(statusdata.equals("00")){%>
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
                                

                    %>
                  <div class="feature-post small-blog">
                     <div class="col-md-5">
                        <div class="feature-img">
                            <img src=<%=image %>  class="img-responsive" alt="#" />
                        </div>
                     </div>
                     <div class="col-md-7">
                        <div class="feature-cont">
                           <div class="post-info">
                              <img src="images/profile-img.png" alt="#" />
                              <span>
                                  <h4><%=userpost %> </h4>
                                 <h5><%=datepost %></h5>
                              </span>
                           </div>
                           <div class="post-heading">
                              <h3><%=title%></h3>
                              <p><%=priview%></p>
                              <div class="full">
                                 <a class="btn" href="CmsNewsController?news_id=<%=news_id%>">Read More</a>
                              </div>
                           </div>
                        </div>
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
                                                <h2>No News Found</h2>
                                                
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
               </div>
            </div>
         </div>
      </section>
                  
                              <%
                                   Nullconvert convertstring = new Nullconvert();
             int page1 = convertstring.nullIntconvert(request.getParameter("page1"));// = IPAGE NO INISIAL PAGE NO 1
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
      <section>
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="full">
                        <div class="main-heading sytle-2">
           <div class="box-footer clearfix">
                <ul class="pagination pagination-sm no-margin pull-right">      
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
           
            <li><a href="CmsNewsController?page1=<%=(cPage * totalRecords) - (totalRecords) %>&cPageNo=<%=(cPage * totalRecords) - (totalRecords)%>" class="numbersFont">Previous| <%= (cPage * totalRecords) - (totalRecords - 1)-1 %></a></li>
            <%
                }
                for (i = ((cPage * totalRecords) - (totalRecords - 1)); i <= (cPage * totalRecords); i++) {
                    if (i == ((page1  / showRows) + 1)) {%>
            <li><a href="CmsNewsController?page1=<%=i%>" class="numbersFont"class="numbersFont"><%=i%></a></li>
                <%
                } else if (i <= totalNews) {
                %>
            <li><a href="CmsNewsController?page1=<%=i%>" class="numbersFont" class="numbersFont"><%=i%></a></li>
                <%
                        }
                    }
            System.out.println("ini i :" + i);
            System.out.println("ini total news :" + totalNews);
                    if (totalNews > totalRecords && i <= totalNews) {
                %>
            <li><a href="CmsNewsController?page1=<%=i%>&cPageNo=<%=i%>" class="numbersFont">Next | <%= i %></a></li>
            <%
                
                    }
                }
               
            %>
                </ul> 
            </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
                                    <%} else{%>
                                    
                                     <div class="container">
                                    <div class="row">
                                       <div class="col-md-12">
                                          <div class="full">
                                             <div class="main-heading sytle-2">
                                                <h2><%=status%> </h2>
                                                
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                 </div>
                    
                    <%}%>
                  
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
