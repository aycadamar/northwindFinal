package com.example.northwind.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northwind.entities.concretes.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{

}
