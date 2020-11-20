package com.graemeyoung.server.resources;

import com.graemeyoung.server.domain.CartItem;
import com.graemeyoung.server.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartResource {

    @Autowired
    CartItemService cartItemService;

    @GetMapping("")
    public ResponseEntity<List<CartItem>> getAllCartItems(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<CartItem> cartItems = cartItemService.fetchAllCartItems(userId);

        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItem> getCartItemById(HttpServletRequest request, @PathVariable("cartItemId") Integer cartItemId) {
        int userId = (Integer) request.getAttribute("userId");
        CartItem cartItem = cartItemService.fetchCartItemById(userId, cartItemId);

        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CartItem> addCartItem(HttpServletRequest request, @RequestBody Map<String, Object> cartItemMap) {
        int userId = (Integer) request.getAttribute("userId");
        Integer itemId = (Integer) cartItemMap.get("itemId");
        Integer quantity = (Integer) cartItemMap.get("quantity");
        CartItem cartItem = cartItemService.addCartItem(userId, itemId, quantity);

        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<Map<String, Boolean>> updateCartItem(HttpServletRequest request,
                                                               @PathVariable("cartItemId") Integer cartItemId,
                                                               @RequestBody CartItem cartItem) {
        int userId = (Integer) request.getAttribute("userId");
        cartItemService.update(userId, cartItemId, cartItem);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Map<String, Boolean>> deleteCartItem(HttpServletRequest request,
                                                               @PathVariable("cartItemId") Integer cartItemId) {
        int userId = (Integer) request.getAttribute("userId");
        cartItemService.removeCartItem(userId, cartItemId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
