package com.example.northwind.dataaccess.concretes;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.northwind.entities.concretes.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer>{

}
