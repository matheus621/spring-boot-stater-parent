package com.matheus.springbootstarterparent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.springbootstarterparent.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
