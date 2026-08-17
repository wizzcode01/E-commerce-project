package com.wisdom.Ecom_project.service;

import com.wisdom.Ecom_project.model.Product;
import com.wisdom.Ecom_project.repository.ProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProdService {

    @Autowired
    ProdRepository repo;

    public Product addProducts(Product prod, MultipartFile imageFile) throws IOException {
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType(imageFile.getContentType());
        prod.setImageDate(imageFile.getBytes());
        return repo.save(prod);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int prodId) {
        return repo.findById(prodId).orElse(new Product());
    }

    public Product updateProduct(int id, Product prod, MultipartFile imageFile) throws IOException {

       Product existingProduct = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id " + id));

       existingProduct.setName(prod.getName());
       existingProduct.setDescription(prod.getDescription());
        existingProduct.setBrandName(prod.getBrandName());
        existingProduct.setPrice(prod.getPrice());
        existingProduct.setCategory(prod.getCategory());
        existingProduct.setQuantity(prod.getQuantity());
        existingProduct.setAvailable(prod.isAvailable());
        existingProduct.setReleasedDate(prod.getReleasedDate());

        if(imageFile != null && !imageFile.isEmpty()){
            existingProduct.setImageDate(imageFile.getBytes());
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
        }

        return repo.save(existingProduct);
    }

        public void deleteProduct(int prodId){

            repo.deleteById(prodId);
        }

}
