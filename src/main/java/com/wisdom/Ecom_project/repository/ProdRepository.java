package com.wisdom.Ecom_project.repository;

import com.wisdom.Ecom_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdRepository extends JpaRepository<Product, Integer>{

}
