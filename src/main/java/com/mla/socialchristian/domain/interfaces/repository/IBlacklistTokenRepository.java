package com.mla.socialchristian.domain.interfaces.repository;

import com.mla.socialchristian.domain.entities.BlacklistTokenEntity;
import com.mla.socialchristian.infra.queries.BlacklistTokenQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBlacklistTokenRepository extends JpaRepository<BlacklistTokenEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = BlacklistTokenQuery.UPDATE_TOKEN, nativeQuery = true)
    int update(@Param("token") String token, @Param("timeStampToken") Long timeStampToken, @Param("timeStampNow") Long timeStampNow);

    @Modifying
    @Transactional
    @Query(value = BlacklistTokenQuery.INSERT_TOKEN, nativeQuery = true)
    void insert(@Param("token") String token, @Param("timeStampToken") Long timeStampToken, @Param("timeStampNow") Long timeStampNow);

    boolean existsByToken(String token);
}
