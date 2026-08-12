package com.wisdom.Ecom_project.service;

import com.wisdom.Ecom_project.model.Product;
import com.wisdom.Ecom_project.repository.ProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdService {

    @Autowired
    ProdRepository repo;

    public void addProducts(Product prod){
        repo.save(prod);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int prodId) {
        return repo.findById(prodId).orElse(new Product());
    }

    public void updateProduct(Product prod){
        repo.save(prod);
    }

    public void deleteProduct(int prodId){
        repo.deleteById(prodId);
    }
}
