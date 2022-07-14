package com.example.movieservice.validation;

import com.example.movieservice.configuration.ApiKeyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@RequiredArgsConstructor
public class ApiValidator implements ConstraintValidator<ApiValidation, String> {


    private final ApiKeyConfig apiKeyConfig;

    @Override
    public boolean isValid(String apiKey, ConstraintValidatorContext context) {
        log.info("set api key {}", apiKey);
        apiKeyConfig.setApiKey(apiKey);
        return true;
    }
}
