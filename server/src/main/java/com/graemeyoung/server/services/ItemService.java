package com.graemeyoung.server.services;


import com.graemeyoung.server.domain.Item;
import com.graemeyoung.server.exceptions.BadRequestException;
import com.graemeyoung.server.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ItemService {

    List<Item> fetchAllItems();

    List<Item> fetchItemsByCategory(String category);

    Item fetchItemById(Integer itemId) throws ResourceNotFoundException;

    Item addItem(Integer quantity, String imageUri, Double price, String name, String description, String category);

    void updateItem(Integer itemId, Item item) throws BadRequestException;

    void removeItemById(Integer itemId) throws ResourceNotFoundException;

}
