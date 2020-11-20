package com.graemeyoung.server.repositories;

import com.graemeyoung.server.domain.WishlistItem;

import java.util.List;

public interface WishlistItemRepository {

    Integer create(Integer userId, Integer itemId);

    List<WishlistItem> findAll(Integer userId);

    WishlistItem findById(Integer userId, Integer wishlistItemId);

    void removeById(Integer userId, Integer wishlistItemId);

}
