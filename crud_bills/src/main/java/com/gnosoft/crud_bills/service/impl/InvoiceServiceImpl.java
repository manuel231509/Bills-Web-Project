/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.service.impl;

import com.gnosoft.crud_bills.commons.GenericServiceImpl;
import com.gnosoft.crud_bills.dao.InvoiceDAO;
import com.gnosoft.crud_bills.service.InvoiceService;
import com.gnosoft.crud_bills.vo.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel Sanchez
 */
@Service
public class InvoiceServiceImpl extends GenericServiceImpl<Invoice, Long> implements InvoiceService{
    @Autowired
    private InvoiceDAO invoiceDAO;
    
    @Override
    public CrudRepository<Invoice,Long> getDAO(){
        return invoiceDAO;
    }
}
