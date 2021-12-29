<%-- 
    Document   : Newsadd
    Created on : Nov 27, 2021, 10:15:36 AM
    Author     : teguh
--%>

<%@page import="java.util.List"%>
<%@page import="Models.Liga"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="content-wrapper">
             <div class="col-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Add Jadwal Baru</h4>
<!--                  <p class="card-description">
                    Basic form elements
                  </p>-->
                  <form class="forms-sample"  method = "post" action="JadwalController?go=jadwal_save" >
                  <!--<form class="forms-sample"  method = "post" action="fileuploadservlet" enctype="multipart/form-data">-->
                    <div class="form-group">
                      <label for="exampleInputName1">Team 1</label>
                      <input type="text" class="form-control" name="" id="exampleInputName1" placeholder="Universitas Budi Luhur" readonly="" >
                    </div>
                     <div class="form-group">
                      <label for="exampleSelectGender">Team 2 </label>
                        <select class="form-control" id="exampleSelectGender" name="Team2">

                          <option value="2">Universitas Muhammadiyah Jakarta</option>
                          <option value="3">Perbanas Institute</option>
                          <option value="4">Universitas Negeri Jakarta</option>
                          <option value="5">Universitas Islam Negeri Jakarta</option>
                          <option value="6">Universitas Trisakti</option>
                          <option value="7">Bina Sarana Informatika</option>
                          <option value="8">IISIP Jakarta</option>
                             
                        </select>
                      </div>
                  
                     <div class="form-group">
                      <label for="exampleInputName1">Hari / Tanggal</label>
                    <input type="text" name="hari" class="form-control pull-right" id="datepicker1" required="">
                    </div>
                   <div class="form-group row">
                      
                      <div class="col-md-2">
                        <label>pukul:</label>
                         <br>
                      <label style="color: #DC143C ">Format hh:mm ex: 12:30</label>
                        <input type="text" class="form-control" name="pukul" onchange="validateHhMm(this);" id="jam" placeholder="pukul" required="">
                      </div>
                    </div>
                  
                      <div class="form-group">
                      <label for="exampleInputName1">Tempat</label>
                      <input type="text" class="form-control" name="tempat" id="exampleInputName1" placeholder="Tempat " required="">
                    </div>
                  
                       <div class="form-group">
                      <label for="exampleSelectGender">Status</label>
                        <select class="form-control" id="exampleSelectGender" name="status">

                          <option value="HF">HF</option>
                          <option value="FT">FT</option>
                          <option value="Next">Next</option>
          
                        </select>
                      </div>
                     <div class="form-group">
                      <% ArrayList listliga = (ArrayList)request.getAttribute("listliga"); %>
                      <label for="exampleSelectGender">Liga</label>
                        <select class="form-control" id="exampleSelectGender" name="liga">

                           <%     
                            List<Liga> list3;
                            list3 = listliga;
                            
                            try {

                            for (Liga lig: list3) {

                                     Integer  id_liga = lig.getIdliga();
                                     String  Nama_liga = lig.getNamaliga();

                           %>

                           <option value="<%=id_liga %>"><%= Nama_liga%></option>

                                   <%
                                   }
                       }catch(Exception e){
                            e.printStackTrace();
                               %>

                           <option value="">nama liga kosong</option>

                                   <%


                           }
                           %>
          
                        </select>
                      </div>
                      <div class="form-group row">
                      
                      <div class="col-md-2">
                        <label>Matchday:</label>
                         <br>
                      <label style="color: #DC143C ">matchday ke-</label>
                        <input type="number" class="form-control" name="matchday" id="matchday" onchange="validatematchday(this);" placeholder="matcday" required="">
                      </div>
                    </div>
                  
                  
              
                   
             
                    
               
                    <button type="submit" class="btn btn-primary mr-2">Submit</button>
                    <!--<a href="NewsController" class="btn btn-light">Cancel</a>-->
                  </form>
                    
                </div>
              </div>
            </div>   
    
</div>

<script type="text/javascript">
  var loadFile = function(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('output');
      output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
  };
  
     $(function () {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.
//    CKEDITOR.replace('editor1')
    //bootstrap WYSIHTML5 - text editor
    $('.textarea').wysihtml5()
  })
</script>

