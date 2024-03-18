package com.example.sklep.Controllers;

import com.example.sklep.entity.Product;
import com.example.sklep.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public HashMap getCart(HttpSession httpSession){
        return cartService.getCart(httpSession);
    }

    @GetMapping("/addProductToCart")
    public void addProductToCart(@RequestBody Product product, HttpSession httpSession){
        cartService.changeProductQuantity(product,httpSession);
    }

}
