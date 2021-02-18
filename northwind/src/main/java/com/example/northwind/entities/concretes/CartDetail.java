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
@Table(name="cart_details")
public class CartDetail implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_details_id")
	private int id;
	@Column(name="cart_id")
	private int cartId;
	@Column(name="product_id")
	private int productId;
	@Column(name="quantity")
	private int quantity;
	@Column(name="unit_price")
	private double unitPrice;
	
}
