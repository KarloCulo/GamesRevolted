package com.example.testnizadatak.repository;

import com.example.testnizadatak.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findById(long userId);
}
