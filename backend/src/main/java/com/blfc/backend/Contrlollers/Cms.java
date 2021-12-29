package com.blfc.backend.Contrlollers;


import com.blfc.backend.Utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@Controller
@RequestMapping("/news")
public class Cms {

    @GetMapping("image/{name}")
    public StreamingResponseBody handleRequest (HttpServletResponse response,
                                                @PathVariable(value = "name") final String id) {

        //Important to set Content type header
        response.setContentType("image/jpeg");

//        String resource = Constants.lokasifoto+id+Constants.formatjpg;
        String resource = Constants.lokasifoto+id;
        System.out.println("ini resource : " + resource);
        return new StreamingResponseBody() {
            @Override
            public void writeTo (OutputStream out) throws IOException {
                File fi = new File(resource);
                try {
                    byte[] fileContent = Files.readAllBytes(fi.toPath());
                    int offset = 0,
                            chunkLength = 10000,
                            fileContentLength = fileContent.length;

                    while (offset+ chunkLength < fileContentLength) {
                        out.write(fileContent,offset, chunkLength );
                        out.flush();
                        offset = offset + chunkLength;

                        if(fileContentLength < offset + chunkLength){
                            chunkLength = fileContentLength - offset;
                        }
                        //Deliberately adding sleep to emulate buffering image
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    out.close();

                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        };
    }
}
