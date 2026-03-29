package com.lucie.pharmacare.medecine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private LocalDate expiryDate;
    private String category;
    private LocalDateTime createdAt;
}
