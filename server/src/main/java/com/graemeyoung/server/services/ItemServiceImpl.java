package com.graemeyoung.server.services;

import com.graemeyoung.server.domain.Item;
import com.graemeyoung.server.exceptions.BadRequestException;
import com.graemeyoung.server.exceptions.ResourceNotFoundException;
import com.graemeyoung.server.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> fetchAllItems(){
        return itemRepository.findAll();
    }

    @Override
    public List<Item> fetchItemsByCategory(String category) {
        return itemRepository.findByCategory(category);
    }

    @Override
    public Item fetchItemById(Integer itemId) throws ResourceNotFoundException{
        return itemRepository.findById(itemId);
    }

    @Override
    public Item addItem(Integer quantity, String imageUri, Double price, String name, String description, String category) {
        category = category.toLowerCase();
        int itemId = itemRepository.create(quantity, imageUri, price, name, description, category);
        return itemRepository.findById(itemId);
    }

    @Override
    public void updateItem(Integer itemId, Item item) throws BadRequestException {
        item.setCategory(item.getCategory().toLowerCase());
        itemRepository.update(itemId, item);
    }

    @Override
    public void removeItemById(Integer itemId) throws ResourceNotFoundException {
        this.fetchItemById(itemId);
        itemRepository.removeById(itemId);
    }
}
