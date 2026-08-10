package com.wisdom.Ecom_project.controller;

import com.wisdom.Ecom_project.model.Product;
import com.wisdom.Ecom_project.service.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class prodController {

    @Autowired
    private ProdService service;

    @PostMapping("/products")
   public void addProduct(@RequestBody Product prod){
       service.addProducts(prod);
   }

   @GetMapping("/products")
    public List<Product> getAllProduct(){
      return service.getAllProducts();
   }

}
