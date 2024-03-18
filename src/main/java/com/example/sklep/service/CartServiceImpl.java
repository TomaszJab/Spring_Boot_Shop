package com.example.sklep.service;

import com.example.sklep.entity.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CartServiceImpl implements CartService{

    @Override
    public HashMap getCart(HttpSession session) {
        if(session.getAttribute("cart")!=null){
            return (HashMap) session.getAttribute("cart");
        }
        return null;
    }

    @Override
    public void changeProductQuantity(Product product, HttpSession session) {
        HashMap<String,Product> cart;

        if(session.getAttribute("cart")==null){
            cart = new HashMap<>();
            cart.put(product.getName(),product);

            session.setAttribute("cart",cart);
        }else{
            cart = (HashMap) session.getAttribute("cart");
            if(cart.containsKey(product.getName())){
                Product product1 = cart.get(product.getName());
                product1.changeQuantity(product.getQuantity());
            }else{
                cart.put(product.getName(),product);
            }
        }
    }
}
