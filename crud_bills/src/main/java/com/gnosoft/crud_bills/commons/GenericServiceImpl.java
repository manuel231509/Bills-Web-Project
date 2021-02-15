/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.commons;

import com.gnosoft.crud_bills.vo.Invoice;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Manuel Sanchez
 */
public abstract class GenericServiceImpl <T, ID extends Serializable> implements GenericService<T, ID>{

    @Override
    public T save(T entity) {
        return getDAO().save(entity);
    }

    @Override
    public void delete(ID id) {
        getDAO().deleteById(id);
    }

    @Override
    public T get(ID id) {
        Optional<T> obj = getDAO().findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> returnList = new LinkedList<>();
        getDAO().findAll().forEach(obj -> returnList.add(obj));
        return returnList;
    }
    
    public abstract CrudRepository<T,ID> getDAO();
}
