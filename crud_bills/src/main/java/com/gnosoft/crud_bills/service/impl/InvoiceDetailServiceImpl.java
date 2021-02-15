/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.service.impl;

import com.gnosoft.crud_bills.commons.GenericServiceImpl;
import com.gnosoft.crud_bills.dao.InvoiceDetailDAO;
import com.gnosoft.crud_bills.service.InvoiceDetailService;
import com.gnosoft.crud_bills.vo.Invoicedetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Manuel Sanchez
 */
@Service
public class InvoiceDetailServiceImpl extends GenericServiceImpl<Invoicedetail, Long> implements InvoiceDetailService {
    @Autowired
    private InvoiceDetailDAO invoiceDetailDAO;
    
    @Override
    public CrudRepository<Invoicedetail,Long> getDAO(){
        return invoiceDetailDAO;
    }
}
