/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnosoft.crud_bills.commons;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Manuel Sanchez
 */
public interface GenericService<T, ID extends Serializable>{
    T save(T entity);
    void delete(ID id);
    T get(ID id);
    List<T> getAll();
}
