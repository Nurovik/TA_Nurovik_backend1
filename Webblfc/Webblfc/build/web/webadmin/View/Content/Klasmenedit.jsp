<%-- 
    Document   : klasmendadd
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
                  <h4 class="card-title">Add klasmen Baru</h4>
<!--                  <p class="card-description">
                    Basic form elements
                  </p>-->
                  <form class="forms-sample"  method = "post" action="ControllerKlasmen" enctype="multipart/form-data">
                  <!--<form class="forms-sample"  method = "post" action="fileuploadservlet" enctype="multipart/form-data">-->
                 
                  <input type="hidden"  name="idklasmen" value="${id_klasmen}"/>
                  <input type="hidden"  name="gambar" value="${gambar}"/>
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

                           <option value="<%=Nama_liga %>"><%= Nama_liga%></option>

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
                        <div class="form-group">
                      <label>File upload</label>
                          <br>
                      <label style="color: #DC143C ">Note Nama Foto max 10 character tidak boleh pakai spasi max 10 MB</label>
<!--                      <input type="file" name="file" class="file-upload-default">
                      <div class="input-group col-xs-12">
                        <input type="text" class="form-control file-upload-info" disabled placeholder="Upload Image">
                        <span class="input-group-append">
                          <button class="file-upload-browse btn btn-primary" type="button">Upload</button>
                        </span>
                      </div>-->
                        <br>
                        <input type = "file" name = "file" size = "50" onchange="loadFile(event)"  />
                        <br>
                         <label>Foto Lama</label>
                        <img src="${fullimage}" alt="your image" width="100" height="100" />
                        <label>Foto baru</label>
                        <img id="output" src="#" alt="your image" width="100" height="100" />
                    </div>
                           <div class="form-group">
                      <label for="exampleInputName1">Detail</label>
                      <input type="text" class="form-control" name="Detail" id="exampleInputName1" placeholder="Detail" required="" value="${detail}">
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

