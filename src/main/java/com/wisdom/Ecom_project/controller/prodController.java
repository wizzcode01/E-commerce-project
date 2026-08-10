package com.wisdom.Ecom_project.controller;

import com.wisdom.Ecom_project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class prodController {

    @Autowired


    @PostMapping("/products")
   public void addProduct(@RequestBody Product prod){
       service.addProduct(prod);
   }
}
