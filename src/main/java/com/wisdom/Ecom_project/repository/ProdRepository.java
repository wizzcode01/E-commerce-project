package com.wisdom.Ecom_project.repository;

import com.wisdom.Ecom_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdRepository extends JpaRepository<Product, Integer>{

    @Query()
    List<Product> searchProducts(String keyword);
}
