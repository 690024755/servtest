package com.galaxyeye.websocket.infrastructure.repository.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class ArticleStatistics implements Serializable {
    private static final long serialVersionUID = 3554874467542161807L;
    private Long articleId;

    private Integer pushNum;

    private Integer readNum;

    private Integer favorNum;

    private Integer disfavorNum;

    private Integer shareNum;

    private Integer sharePersonNum;

    private Integer collectNum;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getPushNum() {
        return pushNum;
    }

    public void setPushNum(Integer pushNum) {
        this.pushNum = pushNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(Integer favorNum) {
        this.favorNum = favorNum;
    }

    public Integer getDisfavorNum() {
        return disfavorNum;
    }

    public void setDisfavorNum(Integer disfavorNum) {
        this.disfavorNum = disfavorNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public Integer getSharePersonNum() {
        return sharePersonNum;
    }

    public void setSharePersonNum(Integer sharePersonNum) {
        this.sharePersonNum = sharePersonNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}