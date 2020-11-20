package com.graemeyoung.server.resources;

import com.graemeyoung.server.domain.WishlistItem;
import com.graemeyoung.server.services.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistResource {

    @Autowired
    WishlistItemService wishlistItemService;

    @GetMapping("")
    public ResponseEntity<List<WishlistItem>> getAllWishlistItems(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<WishlistItem> wishlistItems = wishlistItemService.fetchAllWishlistItems(userId);

        return new ResponseEntity<>(wishlistItems, HttpStatus.OK);
    }

    @GetMapping("/{wishlistItemId}")
    public ResponseEntity<WishlistItem> getWishlistItem(HttpServletRequest request,
                                                        @PathVariable("wishlistItemId") Integer wishlistItemId) {
        int userId = (Integer) request.getAttribute("userId");
        WishlistItem wishlistItem = wishlistItemService.fetchWishlistItemById(userId, wishlistItemId);

        return new ResponseEntity<>(wishlistItem, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<WishlistItem> addWishlistItem(HttpServletRequest request,
                                                        @RequestBody Map<String, Object> wishlistItemMap) {
        int userId = (Integer) request.getAttribute("userId");
        Integer itemId = (Integer) wishlistItemMap.get("itemId");
        WishlistItem wishlistItem = wishlistItemService.addWishlistItem(userId, itemId);

        return new ResponseEntity<>(wishlistItem, HttpStatus.OK);
    }

    @DeleteMapping("/{wishlistItemId}")
    public ResponseEntity<Map<String, Boolean>> deleteWishlistItem(HttpServletRequest request,
                                                                   @PathVariable("wishlistItemId") Integer wishlistItemId) {
        int userId = (Integer) request.getAttribute("userId");
        wishlistItemService.removeWishlistItem(userId, wishlistItemId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
