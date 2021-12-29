/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResponseServer;

import Models.News;
import java.util.List;

/**
 *
 * @author teguh
 */
public class ResponseNews {
    List<News> listData;
    int total;
    String status;
    String statusCode;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }



    public List<News> getListData() {
        return listData;
    }

    public void setListData(List<News> listData) {
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
