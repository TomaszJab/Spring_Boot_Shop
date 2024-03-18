package com.example.sklep.bean;

import com.example.sklep.entity.Product;

import java.util.HashMap;

public interface CreatePdf {
    void createDocument(HashMap<String, Product>  hashMap);
}
