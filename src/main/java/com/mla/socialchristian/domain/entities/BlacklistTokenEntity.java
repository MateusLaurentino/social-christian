package com.mla.socialchristian.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "blacklisttoken",
    indexes = {
        @Index(name = "idx_blacklist_token", columnList = "token")
    }
)
public class BlacklistTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 1000)
    private String token;

    @Column(nullable = false, name = "timestamp")
    private Long timeStamp;
}
