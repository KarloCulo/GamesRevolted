package com.example.testnizadatak.repository;

import com.example.testnizadatak.models.Token;
import com.example.testnizadatak.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long>{

    Token findById(long id);

    @Query("FROM Token WHERE mark = ?1")
    Token findByMark(String mark);

    List<Token> findAll();

    @Query("FROM Token WHERE owner = ?1")
    List<Token> findByOwner(UserAccount userAccount);
}
