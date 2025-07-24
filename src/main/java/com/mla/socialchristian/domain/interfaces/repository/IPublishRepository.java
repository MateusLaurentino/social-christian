package com.mla.socialchristian.domain.interfaces.repository;

import com.mla.socialchristian.domain.entities.PublishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IPublishRepository extends JpaRepository<PublishEntity, Integer> {
    List<PublishEntity> findByIdAccount(Integer idAccount);
    Optional<PublishEntity> findByIdAndIdAccount(Integer id, Integer idAccount);
    void deleteByIdAccount(Integer idAccount);
}
