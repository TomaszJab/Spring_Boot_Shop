package com.example.sklep.component;

import com.example.sklep.dao.ProductRepository;
import com.example.sklep.dao.UserRepository;
import com.example.sklep.entity.Product;
import com.example.sklep.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit {

    private ProductRepository productRepository;

    private UserRepository userRepository;

    @Autowired
    public DbInit(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initProduct() {
        List<Product> productList = new ArrayList();
        productList.add(new Product("Pencil",8.5,
                "create marks by physical abrasion, " +
                "leaving a trail of solid core material that adheres " +
                "to a sheet of paper or other surface",10));
        productList.add(new Product("Book",50,
                "Book is a medium for recording information",10));
        productList.add(new Product("Pen",2,
                "an instrument for writing or drawing with ink",10));
        productRepository.saveAll(productList);
    }


}
