/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResponseServer;

import Models.Pemain;
import java.util.List;

/**
 *
 * @author teguh
 */
public class ResponsePemain {
    List<Pemain> listData;
    String status;
    String statusCode;

    public List<Pemain> getListData() {
        return listData;
    }

    public void setListData(List<Pemain> listData) {
        this.listData = listData;
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
