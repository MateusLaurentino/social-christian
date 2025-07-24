package com.mla.socialchristian.domain.DTOs.outputmodels;

public class PublishDetailOutputModel {
    public Integer id;
    public String bookReference;
    public String chapterMessage;
    public String title;
    public String conclusion;
    public Integer likes;
    public Boolean liked;
    public Boolean isMine;

    public PublishDetailOutputModel(Integer id, String bookReference, String chapterMessage, String title, String conclusion, Integer likes, Boolean liked, Boolean isMine) {
        this.id = id;
        this.bookReference = bookReference;
        this.chapterMessage = chapterMessage;
        this.title = title;
        this.conclusion = conclusion;
        this.likes = likes;
        this.liked = liked;
        this.isMine = isMine;
    }
}
