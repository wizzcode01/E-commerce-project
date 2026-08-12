package com.wisdom.Ecom_project.controller;

import com.wisdom.Ecom_project.model.Product;
import com.wisdom.Ecom_project.service.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class prodController {

    @Autowired
    private ProdService service;

    @PostMapping("/products")
   public ResponseEntity<?> addProduct(@RequestPart Product prod,
                                       @RequestPart MultipartFile imageFile){
       try{
           Product product1 = service.addProducts(prod, imageFile);
           return new ResponseEntity<>(product1, HttpStatus.CREATED);
       }
       catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

       }

   }

   @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
      return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
   }

   @GetMapping("/products/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId){

        Product product = service.getProductById(prodId);

        if(product != null)
          return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @PutMapping("/products")
    public void updateProduct(Product prod){
        service.updateProduct(prod);
   }

   @DeleteMapping("/products/{prodId}")
    public void deleteProduct(@PathVariable int prodId)
   {
      service.deleteProduct(prodId);
   }

}
