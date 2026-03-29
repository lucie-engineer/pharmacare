package com.lucie.pharmacare.medecine.controller;




import com.lucie.pharmacare.medecine.dto.MedicineRequestDTO;
import com.lucie.pharmacare.medecine.dto.MedicineResponseDTO;
import com.lucie.pharmacare.medecine.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    // ✅ Anyone authenticated can view medicines
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<MedicineResponseDTO>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    // ✅ Anyone authenticated can view one medicine
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<MedicineResponseDTO> getMedicineById(
            @PathVariable Long id) {
        return ResponseEntity.ok(medicineService.getMedicineById(id));
    }

    // ✅ Only ADMIN can add medicines
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicineResponseDTO> addMedicine(
            @Valid @RequestBody MedicineRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(medicineService.addMedicine(requestDTO));
    }

    // ✅ Only ADMIN can update medicines
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicineResponseDTO> updateMedicine(
            @PathVariable Long id,
            @Valid @RequestBody MedicineRequestDTO requestDTO) {
        return ResponseEntity.ok(
                medicineService.updateMedicine(id, requestDTO));
    }

    // ✅ Only ADMIN can delete medicines
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMedicine(
            @PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok("Medicine deleted successfully!");
    }

    // ✅ Anyone authenticated can search
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<MedicineResponseDTO>> searchMedicines(
            @RequestParam String name) {
        return ResponseEntity.ok(
                medicineService.searchMedicines(name));
    }

    // ✅ Anyone authenticated can filter by category
    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<MedicineResponseDTO>> getByCategory(
            @PathVariable String category) {
        return ResponseEntity.ok(
                medicineService.getMedicinesByCategory(category));
    }
}