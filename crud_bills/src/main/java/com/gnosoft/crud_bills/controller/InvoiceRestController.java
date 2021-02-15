/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.controller;

import com.gnosoft.crud_bills.service.InvoiceService;
import com.gnosoft.crud_bills.vo.Invoice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Manuel Sanchez
 */
@RestController
@RequestMapping(value = "/api/invoice/")
@CrossOrigin("*")
public class InvoiceRestController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "/all")
    public List<Invoice> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Invoice find(@PathVariable Long id) {
        return invoiceService.get(id);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Invoice> save(@RequestBody Invoice invoice) {
        if (invoiceService.get(invoice.getInv_id()) != null) {
            return new ResponseEntity("The invoice number you are entering already exists", HttpStatus.BAD_REQUEST);
        } else {
            invoiceService.save(invoice);
            return new ResponseEntity("The invoice was posted successfully", HttpStatus.OK);
        }
    }
    
    @PostMapping(value = "/update")
    public ResponseEntity<Invoice> update(@RequestBody Invoice invoice){
        invoiceService.save(invoice);
        return new ResponseEntity("The invoice was updated successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Invoice> delete(@PathVariable Long id) {
        Invoice invoice = invoiceService.get(id);
        if (invoice != null) {
            invoiceService.delete(id);
            return new ResponseEntity("The invoice has been successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity("The invoice could be successfully deleted",HttpStatus.NO_CONTENT);
        }
    }

}
