package com.graemeyoung.server.resources;

import com.graemeyoung.server.domain.Item;
import com.graemeyoung.server.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items")
public class ItemResource {

    @Autowired
    ItemService itemService;

    @GetMapping("")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.fetchAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable("itemId") Integer itemId) {
        Item item = itemService.fetchItemById(itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable("category") String category) {
        List<Item> items = itemService.fetchItemsByCategory(category);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Item> addItem(@RequestBody Map<String, Object> itemMap) {
        Integer quantity = (Integer) (itemMap.get("quantity"));
        String imageUri = (String) itemMap.get("imageUri");
        Double price = Double.valueOf(itemMap.get("price").toString());
        String name = (String) itemMap.get("name");
        String description = (String) itemMap.get("description");
        String category = (String) itemMap.get("category");

        Item item = itemService.addItem(quantity, imageUri, price, name, description, category);

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Map<String, Boolean>> updateItem(@PathVariable("itemId") Integer itemId, @RequestBody Item item) {
        itemService.updateItem(itemId, item);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable("itemId") Integer itemId) {
        itemService.removeItemById(itemId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
