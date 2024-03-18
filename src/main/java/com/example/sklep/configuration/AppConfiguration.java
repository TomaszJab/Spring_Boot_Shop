package com.example.sklep.configuration;

import com.example.sklep.bean.CreatePdf;
import com.example.sklep.bean.CreatePdfImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public CreatePdf createPdf(){
        return new CreatePdfImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
