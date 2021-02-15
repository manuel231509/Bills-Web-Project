/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Manuel Sanchez
 */
@Entity
public class Invoicedetail {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long invdet_id;
    
    @Column
    private long invdet_inv_id;
    
    @Column
    private long invdet_itemid;
    
    @Column
    private String invdet_itemname;
    
    @Column
    private int invdet_quantityitem;
    
    @Column
    private int invdet_itemvalue;

    public Invoicedetail() {
    }

    public Invoicedetail(long invdet_id, long invdet_inv_id, long invdet_itemid, String invdet_itemname, int invdet_quantityitem, int invdet_itemvalue) {
        this.invdet_id = invdet_id;
        this.invdet_inv_id = invdet_inv_id;
        this.invdet_itemid = invdet_itemid;
        this.invdet_itemname = invdet_itemname;
        this.invdet_quantityitem = invdet_quantityitem;
        this.invdet_itemvalue = invdet_itemvalue;
    }
    
    

    public long getInvdet_id() {
        return invdet_id;
    }

    public void setInvdet_id(long invdet_id) {
        this.invdet_id = invdet_id;
    }

    public long getInvdet_inv_id() {
        return invdet_inv_id;
    }

    public void setInvdet_inv_id(long invdet_inv_id) {
        this.invdet_inv_id = invdet_inv_id;
    }

    public long getInvdet_itemid() {
        return invdet_itemid;
    }

    public void setInvdet_itemid(long invdet_itemid) {
        this.invdet_itemid = invdet_itemid;
    }

    public String getInvdet_itemname() {
        return invdet_itemname;
    }

    public void setInvdet_itemname(String invdet_itemname) {
        this.invdet_itemname = invdet_itemname;
    }

    public int getInvdet_quantityitem() {
        return invdet_quantityitem;
    }

    public void setInvdet_quantityitem(int invdet_quantityitem) {
        this.invdet_quantityitem = invdet_quantityitem;
    }

    public int getInvdet_itemvalue() {
        return invdet_itemvalue;
    }

    public void setInvdet_itemvalue(int invdet_itemvalue) {
        this.invdet_itemvalue = invdet_itemvalue;
    }
    
    
    
}
