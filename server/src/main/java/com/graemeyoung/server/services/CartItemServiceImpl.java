package com.graemeyoung.server.services;

import com.graemeyoung.server.domain.CartItem;
import com.graemeyoung.server.exceptions.BadRequestException;
import com.graemeyoung.server.exceptions.ResourceNotFoundException;
import com.graemeyoung.server.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> fetchAllCartItems(Integer userId) {
        return cartItemRepository.findAll(userId);
    }

    @Override
    public CartItem fetchCartItemById(Integer userId, Integer cartItemId) {
        try {
            return cartItemRepository.findById(userId, cartItemId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cart item not found");
        }
    }

    @Override
    public CartItem addCartItem(Integer userId, Integer itemId, Integer quantity) {
        try {
            int cartItemId = cartItemRepository.create(userId, itemId, quantity);
            return cartItemRepository.findById(userId, cartItemId);
        } catch (Exception e) {
            throw new BadRequestException("Invalid cart item details");
        }
    }

    @Override
    public void update(Integer userId, Integer cartItemId, CartItem cartItem) {
        try {
            cartItemRepository.update(userId, cartItemId, cartItem);
        } catch (Exception e) {
            throw new BadRequestException("Invalid cart item details");
        }
    }

    @Override
    public void removeCartItem(Integer userId, Integer cartItemId) {
        cartItemRepository.removeById(userId, cartItemId);
    }
}
