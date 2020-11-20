package com.graemeyoung.server.domain;

public class CartItem {
    private Integer cartItemId;
    private Integer userId;
    private Integer itemId;
    private Integer quantity;

    public CartItem(Integer cartItemId, Integer userId, Integer itemId, Integer quantity) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
