package com.lucie.pharmacare.medecine.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequestDTO {

  @NotBlank(message = "Medicine name is required")
  private String name;

  private String description;

  @NotNull(message = "Price is required")
  @Positive(message = "Price must be positive")
  private Double price;

  @NotNull(message = "Quantity is required")
  @Min(value = 0, message = "Quantity cannot be negative")
  private Integer quantity;

  @NotNull(message = "Expiry date is required")
  @Future(message = "Expiry date must be in the future")
  private LocalDate expiryDate;

  @NotBlank(message = "Category is required")
  private String category;
}
