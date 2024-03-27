package com.example.autopark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//инициализация и запуск приложения
@SpringBootApplication
public class CargoManagerApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(CargoManagerApplication.class,args);
    }
}
