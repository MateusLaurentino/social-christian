package com.mla.socialchristian.domain.interfaces.repository;

import com.mla.socialchristian.domain.entities.AccountUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<AccountUserEntity, Integer> {
    boolean existsByEmail(String email);
    AccountUserEntity findByEmail(String email);
}
