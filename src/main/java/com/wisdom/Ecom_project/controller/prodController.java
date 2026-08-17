package com.wisdom.Ecom_project.controller;

import com.wisdom.Ecom_project.model.Product;
import com.wisdom.Ecom_project.service.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

   @GetMapping("/products/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageDate();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
   }

   @PutMapping("/products/{id}")
   public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart Product prod,
                                               @RequestPart(required = false) MultipartFile imageFile){
       Product product2 = null;
       try {
           product2 = service.updateProduct(id, prod, imageFile);
       } catch (IOException e) {
           return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
       }
       if(product2 != null)
          return new ResponseEntity<>(product2, HttpStatus.OK);
      else
          return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
   }

   @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product != null) {
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
   }

   @GetMapping("/products/search")
   public ResponseEntity<List<Product>> searchProduct(String keyword){
      List<Product> products = service.searchProducts(keyword);
      return new ResponseEntity<>(products, HttpStatus.OK);
   }

}
