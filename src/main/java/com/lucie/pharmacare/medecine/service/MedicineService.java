package com.lucie.pharmacare.medecine.service;


import com.lucie.pharmacare.exception.ResourceNotFoundException;
import com.lucie.pharmacare.medecine.dto.MedicineRequestDTO;
import com.lucie.pharmacare.medecine.dto.MedicineResponseDTO;
import com.lucie.pharmacare.medecine.entity.Medicine;
import com.lucie.pharmacare.medecine.mapper.MedicineMapper;
import com.lucie.pharmacare.medecine.repository.MedicineRepository;
//import com.lucie.pharmacare.medicine.dto.MedicineRequestDTO;
//import com.lucie.pharmacare.medicine.dto.MedicineResponseDTO;
//import com.lucie.pharmacare.medicine.entity.Medicine;
//import com.lucie.pharmacare.medicine.mapper.MedicineMapper;
//import com.lucie.pharmacare.medicine.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

    public MedicineResponseDTO addMedicine(MedicineRequestDTO requestDTO) {
        Medicine medicine = medicineMapper.toEntity(requestDTO);
        medicine.setCreatedAt(LocalDateTime.now());
        return medicineMapper.toResponseDTO(medicineRepository.save(medicine));
    }

    public List<MedicineResponseDTO> getAllMedicines() {
        return medicineRepository.findAll()
                .stream()
                .map(medicineMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public MedicineResponseDTO getMedicineById(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Medicine not found with id: " + id));
        return medicineMapper.toResponseDTO(medicine);
    }

    public MedicineResponseDTO updateMedicine(Long id, MedicineRequestDTO requestDTO) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Medicine not found with id: " + id));
        medicine.setName(requestDTO.getName());
        medicine.setDescription(requestDTO.getDescription());
        medicine.setPrice(requestDTO.getPrice());
        medicine.setQuantity(requestDTO.getQuantity());
        medicine.setExpiryDate(requestDTO.getExpiryDate());
        medicine.setCategory(requestDTO.getCategory());
        return medicineMapper.toResponseDTO(medicineRepository.save(medicine));
    }

    public void deleteMedicine(Long id) {
        medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Medicine not found with id: " + id));
        medicineRepository.deleteById(id);
    }

    public List<MedicineResponseDTO> getMedicinesByCategory(String category) {
        return medicineRepository.findByCategory(category)
                .stream()
                .map(medicineMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MedicineResponseDTO> searchMedicines(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(medicineMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
