/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.dao;

import com.gnosoft.crud_bills.vo.Invoicedetail;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Manuel Sanchez
 */
public interface InvoiceDetailDAO extends CrudRepository<Invoicedetail, Long>{
    
}
