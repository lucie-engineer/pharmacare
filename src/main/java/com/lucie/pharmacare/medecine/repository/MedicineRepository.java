package com.lucie.pharmacare.medecine.repository;

import com.lucie.pharmacare.medecine.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    List<Medicine> findByCategory(String category);
    List<Medicine> findByNameContainingIgnoreCase(String name);
}
