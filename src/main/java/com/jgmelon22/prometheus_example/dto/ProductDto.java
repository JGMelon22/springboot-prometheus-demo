package com.jgmelon22.prometheus_example.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductDto(
        @NotBlank(message = "Product Name is a required field!") @Size(min = 2, max = 100, message = "Product must be between 2 and 100 characters long") String name,

        @NotBlank(message = "Product Description is a required field!") @Size(min = 2, max = 1000, message = "Product Description be between 2 and 1000 characters long") String description,

        @NotNull(message = "Product Price cannot be null") @DecimalMin(value = "0.01", message = "Product Price must be greater than 0") @DecimalMax(value = "10000.00", message = "Product Price cannot exceed 10000.00") @Digits(integer = 5, fraction = 2, message = "Price must have at most 5 integral and 2 fractional digits") BigDecimal price) {
}
