package com.mla.socialchristian.domain.entities;

import com.mla.socialchristian.domain.DTOs.inputmodels.PublishDetailInputModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "publish")
public class PublishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, name = "bookreference")
    private String bookReference;

    @Column(nullable = false, length = 1000, name = "chaptermessage")
    private String chapterMessage;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 5000)
    private String conclusion;

    @Column(nullable = false, name = "idaccount")
    private Integer idAccount;

    @OneToMany(mappedBy = "publish", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<LikePublishEntity> likePublish = new LinkedList<>();

    public PublishEntity(){}

    public PublishEntity(PublishDetailInputModel input, Integer idAccount) {
        this.bookReference = input.bookReference;
        this.chapterMessage = input.chapterMessage;
        this.title = input.title;
        this.conclusion = input.conclusion;
        this.idAccount = idAccount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBookReference(String bookReference) {
        this.bookReference = bookReference;
    }

    public void setChapterMessage(String chapterMessage) {
        this.chapterMessage = chapterMessage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public void addLike(LikePublishEntity like) {
        this.likePublish.add(like);
    }

    public void removeLike(LikePublishEntity like) {
        this.likePublish.remove(like);
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public String getBookReference() {
        return bookReference;
    }

    public String getChapterMessage() {
        return chapterMessage;
    }

    public String getTitle() {
        return title;
    }

    public String getConclusion() {
        return conclusion;
    }

    public List<LikePublishEntity> getLikes() {
        return likePublish;
    }
}
