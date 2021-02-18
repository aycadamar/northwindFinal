package com.example.northwind.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northwind.entities.concretes.CartDetail;

public interface CartDetailRepository extends JpaRepository<CartDetail,Integer>{

}
