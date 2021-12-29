/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResponseServer;

import Models.Liga;

/**
 *
 * @author teguh
 */
public class ResponseLigaByid {
    private Liga data;
    String status;
    String statusCode;

    public Liga getData() {
        return data;
    }

    public void setData(Liga data) {
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
