/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResponseServer;

import Models.News;

/**
 *
 * @author teguh
 */
public class ResponseNewsById {
    
    private News data;
    String status;
    String statusCode;

    public News getData() {
        return data;
    }

    public void setData(News data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    
}
