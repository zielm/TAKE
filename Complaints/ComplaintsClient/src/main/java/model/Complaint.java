/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

public class Complaint {

    private String author;
    private String complaintDate;
    private String complaintText;
    private Integer id;
    private String status;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    @Override
    public String toString() {
        return "[" + id + "] \"" + complaintText + "\" from " + author + ", "
                + complaintDate + ", status: " + status;
    }

}
