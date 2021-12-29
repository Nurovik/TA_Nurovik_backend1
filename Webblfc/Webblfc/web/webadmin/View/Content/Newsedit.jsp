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
                  <h4 class="card-title">EDIT Berita</h4>
<!--                  <p class="card-description">
                    Basic form elements
                  </p>-->
                  <form class="forms-sample"  method = "post" action="NewsController" enctype="multipart/form-data">
                  <!--<form class="forms-sample"  method = "post" action="fileuploadservlet" enctype="multipart/form-data">-->
                  
                  <input type="hidden"  name="id_news" value="${id_news}"/>
                  <input type="hidden"  name="image" value="${image}"/>
                    <div class="form-group">
                      <label for="exampleInputName1">Title</label>
                      <input type="text" class="form-control" name="title" id="exampleInputName1" placeholder="Title" value="${title}">
                    </div>
                    <div class="form-group">
                      <label for="exampleTextarea1">Preview</label>
                      <textarea class="form-control" id="exampleTextarea1" name="preview" rows="4"> ${preview}</textarea>
                    </div>
                    <div class="form-group">
                      <label for="exampleTextarea1">Content</label>
                      <textarea class="form-control" id="exampleTextarea1" name="content" rows="4"> ${content}</textarea>
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
                      <label for="exampleSelectGender">Publish</label>
                        <select class="form-control" id="exampleSelectGender" name="status">
                          <option value="1">Publish</option>
                          <option value="0">Not Publish</option>
                             
                        </select>
                      </div>
               
                    <button type="submit" class="btn btn-primary mr-2">Submit</button>
                  
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

