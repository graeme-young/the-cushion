package com.graemeyoung.server.services;

import com.graemeyoung.server.domain.WishlistItem;

import java.util.List;

public interface WishlistItemService {

    List<WishlistItem> fetchAllWishlistItems(Integer userId);

    WishlistItem fetchWishlistItemById(Integer userId, Integer wishlistItem);

    WishlistItem addWishlistItem(Integer userId, Integer itemId);

    void removeWishlistItem(Integer userId, Integer wishlistItemId);

}
