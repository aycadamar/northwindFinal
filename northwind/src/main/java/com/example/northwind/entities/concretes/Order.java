package com.example.northwind.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.northwind.entities.abstracts.IEntity;

import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Order implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private int id;
	@Column(name="customer_id")
	private String customerId;
	@Column(name="employee_id")
	private int employeeId;
}
