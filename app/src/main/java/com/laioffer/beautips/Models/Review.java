package com.laioffer.beautips.Models;

public class Review {

        String ReviewId;
        String commentorId;
        String stylistId;
        String commentDetail;
        String createDate;
        String reply;

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public Review(String commentorId, String stylistId, String commentDetail) {
        this.commentorId = commentorId;
        this.stylistId = stylistId;
        this.commentDetail = commentDetail;
    }

    public String getReviewId() {
        return ReviewId;
    }

    public String getCommentorId() {
        return commentorId;
    }

    public String getStylistId() {
        return stylistId;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setReviewId(String reviewId) {
        ReviewId = reviewId;
    }

    public void setCommentorId(String commentorId) {
        this.commentorId = commentorId;
    }

    public void setStylistId(String stylistId) {
        this.stylistId = stylistId;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

}
