<%-- 
    Document   : Dashboard
    Created on : Nov 18, 2021, 9:16:56 PM
    Author     : teguh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Skydash Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="webadmin/vendors/feather/feather.css">
  <link rel="stylesheet" href="webadmin/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="webadmin/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="webadmin/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="webadmin/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="text/css" href="webadmin/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="webadmin/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="webadmin/images/favicon.png" />
  <!--bootstrap-wysihtml5-->
  <link rel="stylesheet" href="webadmin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
  
    <!--Sweetalert2-->
  <link rel="stylesheet" href="webadmin/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
  
    <!-- Date Picker -->
  <link rel="stylesheet" href="webadmin/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  
  
    <!-- Bootstrap time Picker -->

  
    <script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
  <script>tinymce.init({selector:'textarea'});</script>
  
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" href="index.html"><img src="webadmin/images/logo.svg" class="mr-2" alt="logo"/></a>
        <a class="navbar-brand brand-logo-mini" href="index.html"><img src="webadmin/images/logo-mini.svg" alt="logo"/></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="icon-menu"></span>
        </button>
        <ul class="navbar-nav mr-lg-2">
          <li class="nav-item nav-search d-none d-lg-block">
            <div class="input-group">
              <div class="input-group-prepend hover-cursor" id="navbar-search-icon">
                <span class="input-group-text" id="search">
             
                </span>
              </div>
             
            </div>
          </li>
        </ul>
        <ul class="navbar-nav navbar-nav-right">
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
              <img src="webadmin/images/faces/face28.jpg" alt="profile"/>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
            
                <a href="logout" class="dropdown-item">
                <i class="ti-power-off text-primary"></i>
                Logout
              </a>
            </div>
          </li>
 
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="icon-menu"></span>
        </button>
      </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_settings-panel.html -->
      <div class="theme-setting-wrapper">
        <div id="settings-trigger"><i class="ti-settings"></i></div>
        <div id="theme-settings" class="settings-panel">
          <i class="settings-close ti-close"></i>
          <p class="settings-heading">SIDEBAR SKINS</p>
          <div class="sidebar-bg-options selected" id="sidebar-light-theme"><div class="img-ss rounded-circle bg-light border mr-3"></div>Light</div>
          <div class="sidebar-bg-options" id="sidebar-dark-theme"><div class="img-ss rounded-circle bg-dark border mr-3"></div>Dark</div>
          <p class="settings-heading mt-2">HEADER SKINS</p>
          <div class="color-tiles mx-0 px-4">
            <div class="tiles success"></div>
            <div class="tiles warning"></div>
            <div class="tiles danger"></div>
            <div class="tiles info"></div>
            <div class="tiles dark"></div>
            <div class="tiles default"></div>
          </div>
        </div>
      </div>
      <div id="right-sidebar" class="settings-panel">
        <i class="settings-close ti-close"></i>
        <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
          <li class="nav-item">
            <a class="nav-link active" id="todo-tab" data-toggle="tab" href="#todo-section" role="tab" aria-controls="todo-section" aria-expanded="true">TO DO LIST</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="chats-tab" data-toggle="tab" href="#chats-section" role="tab" aria-controls="chats-section">CHATS</a>
          </li>
        </ul>
        <div class="tab-content" id="setting-content">
          <div class="tab-pane fade show active scroll-wrapper" id="todo-section" role="tabpanel" aria-labelledby="todo-section">
            <div class="add-items d-flex px-3 mb-0">
              <form class="form w-100">
                <div class="form-group d-flex">
                  <input type="text" class="form-control todo-list-input" placeholder="Add To-do">
                  <button type="submit" class="add btn btn-primary todo-list-add-btn" id="add-task">Add</button>
                </div>
              </form>
            </div>
            <div class="list-wrapper px-3">
              <ul class="d-flex flex-column-reverse todo-list">
                <li>
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="checkbox" type="checkbox">
                      Team review meeting at 3.00 PM
                    </label>
                  </div>
                  <i class="remove ti-close"></i>
                </li>
                <li>
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="checkbox" type="checkbox">
                      Prepare for presentation
                    </label>
                  </div>
                  <i class="remove ti-close"></i>
                </li>
                <li>
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="checkbox" type="checkbox">
                      Resolve all the low priority tickets due today
                    </label>
                  </div>
                  <i class="remove ti-close"></i>
                </li>
                <li class="completed">
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="checkbox" type="checkbox" checked>
                      Schedule meeting for next week
                    </label>
                  </div>
                  <i class="remove ti-close"></i>
                </li>
                <li class="completed">
                  <div class="form-check">
                    <label class="form-check-label">
                      <input class="checkbox" type="checkbox" checked>
                      Project review
                    </label>
                  </div>
                  <i class="remove ti-close"></i>
                </li>
              </ul>
            </div>
            <h4 class="px-3 text-muted mt-5 font-weight-light mb-0">Events</h4>
            <div class="events pt-4 px-3">
              <div class="wrapper d-flex mb-2">
                <i class="ti-control-record text-primary mr-2"></i>
                <span>Feb 11 2018</span>
              </div>
              <p class="mb-0 font-weight-thin text-gray">Creating component page build a js</p>
              <p class="text-gray mb-0">The total number of sessions</p>
            </div>
            <div class="events pt-4 px-3">
              <div class="wrapper d-flex mb-2">
                <i class="ti-control-record text-primary mr-2"></i>
                <span>Feb 7 2018</span>
              </div>
              <p class="mb-0 font-weight-thin text-gray">Meeting with Alisa</p>
              <p class="text-gray mb-0 ">Call Sarah Graves</p>
            </div>
          </div>
          <!-- To do section tab ends -->
          <div class="tab-pane fade" id="chats-section" role="tabpanel" aria-labelledby="chats-section">
            <div class="d-flex align-items-center justify-content-between border-bottom">
              <p class="settings-heading border-top-0 mb-3 pl-3 pt-0 border-bottom-0 pb-0">Friends</p>
              <small class="settings-heading border-top-0 mb-3 pt-0 border-bottom-0 pb-0 pr-3 font-weight-normal">See All</small>
            </div>
            <ul class="chat-list">
              <li class="list active">
                <div class="profile"><img src="webadmin/images/faces/face1.jpg" alt="image"><span class="online"></span></div>
                <div class="info">
                  <p>Thomas Douglas</p>
                  <p>Available</p>
                </div>
                <small class="text-muted my-auto">19 min</small>
              </li>
              <li class="list">
                <div class="profile"><img src="webadmin/images/faces/face2.jpg" alt="image"><span class="offline"></span></div>
                <div class="info">
                  <div class="wrapper d-flex">
                    <p>Catherine</p>
                  </div>
                  <p>Away</p>
                </div>
                <div class="badge badge-success badge-pill my-auto mx-2">4</div>
                <small class="text-muted my-auto">23 min</small>
              </li>
              <li class="list">
                <div class="profile"><img src="images/faces/face3.jpg" alt="image"><span class="online"></span></div>
                <div class="info">
                  <p>Daniel Russell</p>
                  <p>Available</p>
                </div>
                <small class="text-muted my-auto">14 min</small>
              </li>
              <li class="list">
                <div class="profile"><img src="webadmin/images/faces/face4.jpg" alt="image"><span class="offline"></span></div>
                <div class="info">
                  <p>James Richardson</p>
                  <p>Away</p>
                </div>
                <small class="text-muted my-auto">2 min</small>
              </li>
              <li class="list">
                <div class="profile"><img src="webadmin/images/faces/face5.jpg" alt="image"><span class="online"></span></div>
                <div class="info">
                  <p>Madeline Kennedy</p>
                  <p>Available</p>
                </div>
                <small class="text-muted my-auto">5 min</small>
              </li>
              <li class="list">
                <div class="profile"><img src="webadmin/images/faces/face6.jpg" alt="image"><span class="online"></span></div>
                <div class="info">
                  <p>Sarah Graves</p>
                  <p>Available</p>
                </div>
                <small class="text-muted my-auto">47 min</small>
              </li>
            </ul>
          </div>
          <!-- chat tab ends -->
        </div>
      </div>

       <% String statuscodedaata = (String)request.getAttribute("statusserver"); %>
       <% String statusdataserver = (String)request.getAttribute("statusdataserver"); %>
      <!-- partial -->
      <!-- partial:partials/_sidebar.html -->
        <%@ include file="Menu.jsp"%>       
      <!-- partial -->
      <div class="main-panel">
          
                                <%
                String menu = (String)request.getAttribute("halaman"); 
                System.out.print(menu);
             if (menu.equals("Dashboard")) {

            %>
            <%@ include file="Content/Dashboard.jsp"%>

            <%} 
            else if (menu.equals("cmsnews")) {
            %>

            <%@ include file="Content/News.jsp"%>
            <% } 


            else if(menu.equals("newsadd"))  {
            %>

           <%@ include file="Content/Newsadd.jsp"%>
            <% }

            else if(menu.equals("newsedit"))  {
            %>

             <%@ include file="Content/Newsedit.jsp"%>
            <% } 

            else if(menu.equals("Liga"))  {
            %>

            <%@ include file="Content/Liga.jsp"%>
            <% }

            else if(menu.equals("Ligaadd"))  {
            %>

             <%@ include file="Content/Ligaadd.jsp"%>
            <% }


            else if(menu.equals("ligaedit"))  {
            %>

              <%@ include file="Content/Ligaedit.jsp"%>
            <% }

            else if(menu.equals("pemain"))  {
            %>

           <%@ include file="Content/Pemain.jsp"%>
            <% }

            else if(menu.equals("pemaindd"))  {
            %>

            <%@ include file="Content/Pemainadd.jsp"%>
            <% }

            else if(menu.equals("pemainedit"))  {
            %>

               <%@ include file="Content/Pemainedit.jsp"%>
            <% }


            else if(menu.equals("jadwal"))  {
            %>

            <%@ include file="Content/Jadwal.jsp"%>
            <% }

            else if(menu.equals("jadwaladd"))  {
            %>

            <%@ include file="Content/Jadwaladd.jsp"%>
            <% }

                else if(menu.equals("jadwaledit"))  {
            %>

            <%@ include file="Content/Jadwaledit.jsp"%>
            <% }

            else if(menu.equals("klasmen"))  {
            %>

            <%@ include file="Content/Klasmen.jsp"%>
            <% }

            else if(menu.equals("klasmenadd"))  {
            %>

            <%@ include file="Content/Klasmenadd.jsp"%>
            <% }


            else if(menu.equals("klasmenedit"))  {
            %>

            <%@ include file="Content/Klasmenedit.jsp"%>
            <% }




            %>


     
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <footer class="footer">
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright © 2021.  Premium <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap admin template</a> from BootstrapDash. All rights reserved.</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with <i class="ti-heart text-danger ml-1"></i></span>
          </div>
        </footer>
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  
  <script src="webadmin/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="webadmin/vendors/chart.js/Chart.min.js"></script>
  <script src="webadmin/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="webadmin/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="webadmin/js/dataTables.select.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="webadmin/js/off-canvas.js"></script>
  <script src="webadmin/js/hoverable-collapse.js"></script>
  <script src="webadmin/js/template.js"></script>
  <script src="webadmin/js/settings.js"></script>
  <script src="webadmin/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="webadmin/js/dashboard.js"></script>
  <script src="webadmin/js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
  <!--bootstrap-wysihtml5-->
  <script src="webadmin/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
  
  <!-- SweetAlert2 -->
<script src="webadmin/plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="webadmin/plugins/sweetalert2/sweetalert2.all.min.js"></script>


<!-- datepicker -->
<script src="webadmin/plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
  
  <script type="text/javascript">
       $(function () {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
//    CKEDITOR.replace('editor1')
    //bootstrap WYSIHTML5 - text editor
    $('.textarea').wysihtml5()
  })
  
  var statusdata = "<%= statuscodedaata  %>";
  console.log(statusdata);
    if(statusdata == "00"){
        console.log("Berhasil");
        Swal.fire(
              'success',
              'You clicked the button!',
              'success'
            )
        
    }else if(statusdata == "02"){
        
              Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: '<%= statusdataserver  %>' ,
              footer: '<a href= "LoginController" >Page Login</a>'
        })
    }else if(statusdata == "01") {
        
               Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: '<%= statusdataserver  %>',
              footer: '<a href>Why do I have this issue?</a>'
        })
    }
    
    //popup sweetalert
      $('.tombol-hapus').on('click', function(e) {
//            console.log("cobaaaaaaaa");
//
//        window.alert("test");
        e.preventDefault();
        const href = $(this).attr('href');

        Swal.fire({
            title: 'Apakah anda yakin ingin menghapus data ini',
            text: "Data Akan Di Hapus!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Hapus Data!'
        }).then((result) => {
            if (result.value) {

                document.location.href = href;

            }
        })

    });
    
       $('#datepicker1').datepicker({
      
      autoclose: true
    });
//     $('#datepicker2').datepicker({
//    
//      autoclose: true
//    });
//     $('#datepicker3').datepicker({
//      autoclose: true
//    });
//     $('#datepicker4').datepicker({
//      autoclose: true
//    });
//     $('#datepicker5').datepicker({
//      autoclose: true
//    });
//     $('#datepicker6').datepicker({
//      autoclose: true
//    });
    
function validateHhMm(inputField) {
    var isValid = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/.test(inputField.value);

    if (isValid) {
      inputField.style.backgroundColor = '#bfa';
    } else {
    
      inputField.style.backgroundColor = '#fba';
        document.getElementById("jam").value=""
       
    }

    return isValid;
  }
  
  function validatematchday(inputField) {
    var isValid = /^[1-9][0-9]?$|^100$/.test(inputField.value);

    if (isValid) {
      inputField.style.backgroundColor = '#bfa';
    } else {
    
      inputField.style.backgroundColor = '#fba';
        document.getElementById("matchday").value=""
       
    }

    return isValid;
  }
    
  </script>
</body>

</html>
