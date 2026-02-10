package com.chitkara.bfhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.chitkara.bfhl")
public class BfhlQualifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(BfhlQualifierApplication.class, args);
    }
}
