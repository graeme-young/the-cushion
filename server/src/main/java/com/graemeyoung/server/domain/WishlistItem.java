package com.graemeyoung.server.domain;

public class WishlistItem {
    private Integer wishlistItemId;
    private Integer userId;
    private Integer itemId;

    public WishlistItem(Integer wishlistItemId, Integer userId, Integer itemId) {
        this.wishlistItemId = wishlistItemId;
        this.userId = userId;
        this.itemId = itemId;
    }

    public Integer getWishlistItemId() {
        return wishlistItemId;
    }

    public void setWishlistItemId(Integer wishlistItemId) {
        this.wishlistItemId = wishlistItemId;
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
}
