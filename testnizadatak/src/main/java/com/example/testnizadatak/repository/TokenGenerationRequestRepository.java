package com.example.testnizadatak.repository;

import com.example.testnizadatak.models.TokenGenerationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenGenerationRequestRepository extends JpaRepository<TokenGenerationRequest, Long> {

    List<TokenGenerationRequest> findAllByUserId(long userId);

    TokenGenerationRequest findById(long id);
}
