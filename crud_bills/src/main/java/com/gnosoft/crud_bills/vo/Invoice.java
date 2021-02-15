/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.vo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Manuel Sanchez
 */
@Entity
public class Invoice {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inv_id;
    
    @Column
    private String inv_nameclient;
    
    @Column
    private Date inv_date;
    
    @Column
    private int inv_subtotal;
    
    @Column
    private int inv_vat;
    
    @Column
    private int inv_total;

    public long getInv_id() {
        return inv_id;
    }

    public void setInv_id(long inv_id) {
        this.inv_id = inv_id;
    }

    public String getInv_nameclient() {
        return inv_nameclient;
    }

    public void setInv_nameclient(String inv_nameclient) {
        this.inv_nameclient = inv_nameclient;
    }

    public Date getInv_date() {
        return inv_date;
    }

    public void setInv_date(Date inv_date) {
        this.inv_date = inv_date;
    }

    public int getInv_subtotal() {
        return inv_subtotal;
    }

    public void setInv_subtotal(int inv_subtotal) {
        this.inv_subtotal = inv_subtotal;
    }
    
    public int getInv_vat() {
        return inv_vat;
    }

    public void setInv_vat(int inv_vat) {
        this.inv_vat = inv_vat;
    }

    public int getInv_total() {
        return inv_total;
    }

    public void setInv_total(int inv_total) {
        this.inv_total = inv_total;
    }

    
    
}
