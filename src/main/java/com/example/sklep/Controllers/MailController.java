package com.example.sklep.Controllers;

import com.example.sklep.bean.CreatePdf;
import com.example.sklep.entity.Product;
import com.example.sklep.service.CartService;
import com.example.sklep.service.MailServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class MailController {

    private MailServiceImpl mailService;
    private CreatePdf createPdf;

    private CartService cartService;
    @Autowired
    public MailController(MailServiceImpl mailService, CartService cartService, CreatePdf createPdf) {
        this.mailService = mailService;
        this.createPdf = createPdf;
        this.cartService = cartService;
    }

    @GetMapping("/sendMail")
    public String sendMail(@RequestParam String adressMail, HttpSession httpSession){
        HashMap<String, Product> hashMap = cartService.getCart(httpSession);
        if(hashMap!=null){
            createPdf.createDocument(hashMap);
        }else{
            return "Pusty koszyk";
        }

        Date actualDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        mailService.sendMail(adressMail, "Zakupy "+ simpleDateFormat.format(actualDate),
                    "Dziekujemy za zakupy. Ponizej przesylamy pdf z podsumowaniem", true);

        return "Mail zostal wyslany";
    }
}
