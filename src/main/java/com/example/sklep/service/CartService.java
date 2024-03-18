package com.example.sklep.service;

import com.example.sklep.entity.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

public interface CartService {

    HashMap getCart(HttpSession session);
    void changeProductQuantity(Product product, HttpSession session);
}
