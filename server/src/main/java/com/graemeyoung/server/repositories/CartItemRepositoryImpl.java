package com.graemeyoung.server.repositories;

import com.graemeyoung.server.domain.CartItem;
import com.graemeyoung.server.exceptions.BadRequestException;
import com.graemeyoung.server.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CartItemRepositoryImpl implements CartItemRepository{

    private static final String SQL_CREATE = "INSERT INTO CART_ITEM (CART_ITEM_ID, USER_ID, ITEM_ID, QUANTITY) " +
            "VALUES(NEXTVAL('cart_ITEM_SEQ'), ?, ?, ?)";
    private static final String SQL_FIND_ALL = "SELECT * FROM CART_ITEM WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM CART_ITEM WHERE USER_ID = ? AND CART_ITEM_ID = ?";
    private static final String SQL_UPDATE = "UPDATE CART_ITEM SET QUANTITY = ? WHERE USER_ID = ? AND CART_ITEM_ID = ?";
    private static final String SQL_DELETE = "DELETE FROM CART_ITEM WHERE USER_ID = ? AND CART_ITEM_ID = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer create(Integer userId, Integer itemId, Integer quantity) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setInt(2, itemId);
                ps.setInt(3, quantity);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("CART_ITEM_ID");
        } catch (DataAccessException e) {
            throw new BadRequestException("Invalid cart item details");
        }
    }

    @Override
    public List<CartItem> findAll(Integer user_id) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{user_id}, cartItemRowMapper);
    }

    @Override
    public CartItem findById(Integer userId, Integer cartItemId) throws ResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, cartItemId}, cartItemRowMapper);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cart item not found");
        }
    }


    @Override
    public void update(Integer userId, Integer cartItemId, CartItem cartItem) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{cartItem.getQuantity(), userId, cartItemId});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BadRequestException("Invalid cart item details");
        }
    }

    @Override
    public void removeById(Integer userId, Integer cartItemId) throws ResourceNotFoundException {
        int count = jdbcTemplate.update(SQL_DELETE, new Object[]{userId, cartItemId});
        if (count == 0) {
            throw new ResourceNotFoundException("cart item not found");
        }
    }

    private RowMapper<CartItem> cartItemRowMapper =((rs, rowNum) -> {
        return new CartItem(
                rs.getInt("CART_ITEM_ID"),
                rs.getInt("USER_ID"),
                rs.getInt("ITEM_ID"),
                rs.getInt("QUANTITY")
        );
    });

}
