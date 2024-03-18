package com.example.sklep.Controllers;

import com.example.sklep.entity.Product;
import com.example.sklep.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShopController {

    private ProductService productService;

    public ShopController(ProductService employeeService) {
        this.productService = employeeService;
    }

    @GetMapping("/allProduct")
    public List<Product> findAllProduct() {
        return productService.findAll();
    }

    @GetMapping("/allProduct/{productId}")
    public Optional<Product> findProductById(@PathVariable("productId") int theId) {
        return productService.findById(theId);
    }

    @GetMapping("/allProduct/form/{productId}")
    public Optional<Product> showFormForUpdate(@PathVariable("productId") int theId){
        return productService.findById(theId);
    }

    @PostMapping("/allProduct/form/save")
    public void saveProduct(@RequestBody Product theProduct){
        productService.save(theProduct);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public void deleteProduct(@PathVariable("productId") int theId) {
        productService.deleteById(theId);
    }
}
