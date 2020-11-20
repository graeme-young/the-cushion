package com.graemeyoung.server.repositories;

import com.graemeyoung.server.domain.Item;
import com.graemeyoung.server.exceptions.BadRequestException;
import com.graemeyoung.server.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ItemRepository {

    Integer create(Integer quantity, String imageUri, Double price, String name, String description, String category) throws BadRequestException;

    List<Item> findAll() throws ResourceNotFoundException;

    Item findById(Integer itemId) throws ResourceNotFoundException;

    List<Item> findByCategory(String category) throws ResourceNotFoundException;

    void update(Integer itemId, Item item) throws BadRequestException;

    void removeById(Integer itemId) throws BadRequestException;

}
