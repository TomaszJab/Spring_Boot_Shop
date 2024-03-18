package com.example.sklep.service;

import com.example.sklep.dao.ProductRepository;
import com.example.sklep.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository theEmployeeRepository) {
		productRepository = theEmployeeRepository;
	}
	
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(int theId) {
		return productRepository.findById(theId);
	}
	@Override
	public void deleteById(int theId) {
		productRepository.deleteById(theId);
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

}






