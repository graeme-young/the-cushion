package com.graemeyoung.server.repositories;

import com.graemeyoung.server.domain.CartItem;

import java.util.List;

public interface CartItemRepository {

    Integer create(Integer userId, Integer itemId, Integer quantity);

    CartItem findById(Integer userId, Integer cartItemId);

    List<CartItem> findAll(Integer userId);

    void update(Integer userId, Integer cartItemId, CartItem cartItem);

    void removeById(Integer userId, Integer cartItemId);

}
