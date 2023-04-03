package com.cmlteam.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cmlteam.web")
public class TestsApp {

  public static void main(String[] args) {
    SpringApplication.run(TestsApp.class, args);
  }
}
