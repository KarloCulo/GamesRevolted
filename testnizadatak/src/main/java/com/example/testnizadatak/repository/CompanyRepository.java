package com.example.testnizadatak.repository;

import com.example.testnizadatak.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
