package com.example.sklep.service;

import com.example.sklep.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	List<Product> findAll();

	public Optional<Product> findById(int theId);
	void deleteById(int theId);

	void save(Product product);
}
