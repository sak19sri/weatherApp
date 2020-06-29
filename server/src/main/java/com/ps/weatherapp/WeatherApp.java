package com.ps.weatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class WeatherApp {

    public static void main(String[] args){
        SpringApplication.run(WeatherApp.class);
    }
}
