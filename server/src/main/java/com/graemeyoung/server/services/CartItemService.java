package com.graemeyoung.server.services;

import com.graemeyoung.server.domain.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> fetchAllCartItems(Integer userId);

    CartItem fetchCartItemById(Integer userId, Integer cartItemId);

    CartItem addCartItem(Integer userId, Integer itemId, Integer quantity);

    void update(Integer userId, Integer cartItemId, CartItem cartItem);

    void removeCartItem(Integer userId, Integer cartItemId);

}
