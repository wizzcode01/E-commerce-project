package com.wisdom.Ecom_project.service;

import com.wisdom.Ecom_project.model.Product;
import com.wisdom.Ecom_project.repository.ProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class prodService {

    @Autowired
    ProdRepository repo;

    public void addProduct(Product prod){
        repo.save(prod);
    }
}
