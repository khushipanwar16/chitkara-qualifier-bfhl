package com.chitkara.bfhl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chitkara.bfhl.model.ApiResponse;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ApiResponse healthCheck() {

        ApiResponse response = new ApiResponse();
        response.setIs_success(true);
        response.setOfficial_email("khushi1956.be23@chitkara.edu.in");

        return response;
    }
}
