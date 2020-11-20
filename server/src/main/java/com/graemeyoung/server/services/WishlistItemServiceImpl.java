package com.graemeyoung.server.services;

import com.graemeyoung.server.domain.WishlistItem;
import com.graemeyoung.server.repositories.WishlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WishlistItemServiceImpl implements WishlistItemService{

    @Autowired
    WishlistItemRepository wishlistItemRepository;

    @Override
    public List<WishlistItem> fetchAllWishlistItems(Integer userId) {
        return wishlistItemRepository.findAll(userId);
    }

    @Override
    public WishlistItem fetchWishlistItemById(Integer userId, Integer wishlistItemId) {
        return wishlistItemRepository.findById(userId, wishlistItemId);
    }

    @Override
    public WishlistItem addWishlistItem(Integer userId, Integer itemId) {
        int wishlistItemId = wishlistItemRepository.create(userId, itemId);
        return wishlistItemRepository.findById(userId, wishlistItemId);
    }

    @Override
    public void removeWishlistItem(Integer userId, Integer wishlistItemId) {
        this.fetchWishlistItemById(userId, wishlistItemId);
        wishlistItemRepository.removeById(userId, wishlistItemId);
    }
}
