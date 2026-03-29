package com.lucie.pharmacare.medecine.mapper;

import com.lucie.pharmacare.medecine.dto.MedicineRequestDTO;
import com.lucie.pharmacare.medecine.dto.MedicineResponseDTO;
import com.lucie.pharmacare.medecine.entity.Medicine;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicineMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Medicine toEntity(MedicineRequestDTO dto);

    MedicineResponseDTO toResponseDTO(Medicine medicine);
}
