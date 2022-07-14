package com.eraine.ulamrandomizer.controller;

import static net.logstash.logback.argument.StructuredArguments.kv;

import com.eraine.ulamrandomizer.dto.IngredientRequest;
import com.eraine.ulamrandomizer.dto.IngredientResponse;
import com.eraine.ulamrandomizer.service.IngredientService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredients")
@AllArgsConstructor
@Slf4j
public class IngredientController {

    private final IngredientService service;

    @PostMapping
    public IngredientResponse save(@RequestBody IngredientRequest request) {
        log.info("Received request to save ingredient. {}", kv("request", request));
        return service.save(request);
    }

    @RequestMapping
    public List<IngredientResponse> findAll() {
        log.info("Received request to list all ingredients.");
        return service.findAll();
    }

}
