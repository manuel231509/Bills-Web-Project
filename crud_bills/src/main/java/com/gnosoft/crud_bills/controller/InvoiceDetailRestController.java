/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.controller;

import com.gnosoft.crud_bills.service.InvoiceDetailService;
import com.gnosoft.crud_bills.vo.Invoicedetail;
import java.util.ArrayList;
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
@RequestMapping(value = "/api/invoicedetail/")
@CrossOrigin("*")
public class InvoiceDetailRestController {

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    @GetMapping(value = "/all")
    public List<Invoicedetail> getAll() {
        return invoiceDetailService.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Invoicedetail find(@PathVariable Long id) {
        return invoiceDetailService.get(id);
    }

    @GetMapping(value = "/findinvoiceid/{inv_id}")
    public List<Invoicedetail> findInvoiceId(@PathVariable Long inv_id) {
        List<Invoicedetail> listInvDet = invoiceDetailService.getAll();
        List<Invoicedetail> listInvDetAux = new ArrayList<>();
        for (int i = 0; i < listInvDet.size(); i++) {
            if (listInvDet.get(i).getInvdet_inv_id() == inv_id) {
                Invoicedetail invoicedetail = listInvDet.get(i);
                listInvDetAux.add(invoicedetail);
            }
        }
        return listInvDetAux;
    }

    public long incrementInvDetID() {
        List<Invoicedetail> listInvDet = invoiceDetailService.getAll();
        long valueIncrement = 0;

        if (listInvDet == null) {
            valueIncrement = 1;
        } else {
            long mayor = 0;
            for (int i = 0; i < listInvDet.size(); i++) {
                long invdet_id = listInvDet.get(i).getInvdet_id();
                if (mayor < invdet_id) {
                    mayor = invdet_id;
                    valueIncrement = mayor;
                }
            }

            valueIncrement++;
        }
        return valueIncrement;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Invoicedetail> save(@RequestBody List<Invoicedetail> listInvoiceDetail) {

        for (int i = 0; i < listInvoiceDetail.size(); i++) {
            long valueIncrement = incrementInvDetID();
            Invoicedetail invoicedetail = listInvoiceDetail.get(i);
            invoicedetail.setInvdet_id(valueIncrement);
            invoiceDetailService.save(invoicedetail);
        }
        return new ResponseEntity("The invoice was posted successfully", HttpStatus.OK);

    }

    @PostMapping(value = "/update")
    public ResponseEntity<Invoicedetail> update(@RequestBody List<Invoicedetail> listInvoiceDetail) {
        for (int i = 0; i < listInvoiceDetail.size(); i++) {

            long invdet_id = listInvoiceDetail.get(i).getInvdet_id();

            Invoicedetail invoicedetail = null;
            if (invdet_id == 0) {
                long valueIncrement = incrementInvDetID();
                invoicedetail = new Invoicedetail(valueIncrement, listInvoiceDetail.get(i).getInvdet_inv_id(),
                        listInvoiceDetail.get(i).getInvdet_itemid(), listInvoiceDetail.get(i).getInvdet_itemname(),
                        listInvoiceDetail.get(i).getInvdet_quantityitem(),
                        listInvoiceDetail.get(i).getInvdet_itemvalue());

            } else {
                invoicedetail = listInvoiceDetail.get(i);
            }
            invoiceDetailService.save(invoicedetail);
        }
        return new ResponseEntity("The invoice was posted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<Invoicedetail> delete(@PathVariable Long id) {

        Invoicedetail invoiceDetail = invoiceDetailService.get(id);
        if (invoiceDetail != null) {
            invoiceDetailService.delete(id);
            return new ResponseEntity("The invoice detail has been successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity("The invoice detail could be successfully deleted", HttpStatus.NO_CONTENT);
        }
    }
}
