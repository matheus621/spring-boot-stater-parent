package com.matheus.springbootstarterparent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.springbootstarterparent.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
