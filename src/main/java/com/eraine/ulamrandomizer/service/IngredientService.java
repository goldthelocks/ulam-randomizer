package com.eraine.ulamrandomizer.service;

import static net.logstash.logback.argument.StructuredArguments.kv;

import com.eraine.ulamrandomizer.dto.IngredientRequest;
import com.eraine.ulamrandomizer.dto.IngredientResponse;
import com.eraine.ulamrandomizer.entity.Ingredient;
import com.eraine.ulamrandomizer.repository.IngredientRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class IngredientService {

    private final IngredientRepository repository;

    public IngredientResponse save(IngredientRequest request) {
        log.info("Creating new ingredient. {}", kv("request", request));

        Ingredient entity = new Ingredient();
        entity.setName(request.getName());

        repository.save(entity);

        return mapToResponse(entity);
    }

    public List<IngredientResponse> findAll() {
        log.info("Finding all ingredients.");

        List<IngredientResponse> items = repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toUnmodifiableList());

        log.info("Found {} ingredients.", kv("size", items.size()));

        return items;
    }

    private IngredientResponse mapToResponse(Ingredient ingredient) {
        return IngredientResponse.builder()
            .id(ingredient.getId())
            .name(ingredient.getName())
            .build();
    }

}
