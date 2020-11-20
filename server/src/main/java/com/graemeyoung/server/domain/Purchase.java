package com.graemeyoung.server.domain;

public class Purchase {
    private Integer purchaseId;
    private Integer userId;
    private Integer itemId;
    private Long timestamp;

    public Purchase(Integer purchaseId, Integer userId, Integer itemId, Long timestamp) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.itemId = itemId;
        this.timestamp = timestamp;
    }

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
