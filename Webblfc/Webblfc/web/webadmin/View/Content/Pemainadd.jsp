<%-- 
    Document   : Newsadd
    Created on : Nov 27, 2021, 10:15:36 AM
    Author     : teguh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="content-wrapper">
             <div class="col-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Add Pemain Baru</h4>
<!--                  <p class="card-description">
                    Basic form elements
                  </p>-->
                  <form class="forms-sample"  method = "post" action="PemainController" enctype="multipart/form-data">
                  <!--<form class="forms-sample"  method = "post" action="fileuploadservlet" enctype="multipart/form-data">-->
                    <div class="form-group">
                      <label for="exampleInputName1">Nama Pemain</label>
                      <input type="text" class="form-control" name="namapemain" id="exampleInputName1" placeholder="Nama Pemain" required="">
                    </div>
                     <div class="form-group">
                      <label for="exampleSelectGender">Fakultas </label>
                        <select class="form-control" id="exampleSelectGender" name="fakultas">
                          <option value="FAKULTAS TEKNOLOGI INFORMASI">FAKULTAS TEKNOLOGI INFORMASI</option>
                          <option value="FAKULTAS ILMU KOMUNIKASI">FAKULTAS ILMU KOMUNIKASI</option>
                          <option value="FAKULTAS EKONOMI & BISNIS">FAKULTAS EKONOMI & BISNIS</option>
                          <option value="FAKULTAS ILMU SOSIAL & POLITIK">FAKULTAS ILMU SOSIAL & POLITIK</option>
                          <option value="FAKULTAS TEKNIK">FAKULTAS TEKNIK</option>
                             
                        </select>
                      </div>
                  
                    <div class="form-group">
                      <label for="exampleTextarea1">No Punggung</label>
                       <input type="number" class="form-control" name="nopunggung" id="exampleInputName1" placeholder="No Pungung" required="">
                    </div>
                  
              
                  
                    <div class="form-group">
                      <label>Foto upload</label>
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
                        <input type = "file" name = "file" size = "50" onchange="loadFile(event)" required="" />
                        <br>
                        <img id="output" src="#" alt="your image" width="100" height="100" />
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

