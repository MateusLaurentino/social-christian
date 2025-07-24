package com.mla.socialchristian.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "likepublish")
public class LikePublishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "idpublish")
    private Integer idPublish;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idpublish", insertable = false, updatable = false)
    private PublishEntity publish;

    @Column(nullable = false, name = "idaccount")
    private Integer idAccount;

    public LikePublishEntity(){}

    public LikePublishEntity(Integer idPublish, Integer idAccount) {
        this.idPublish = idPublish;
        this.idAccount = idAccount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdAccount() {
        return idAccount;
    }
}
