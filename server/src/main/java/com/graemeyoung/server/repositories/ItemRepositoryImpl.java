package com.graemeyoung.server.repositories;

import com.graemeyoung.server.domain.Item;
import com.graemeyoung.server.exceptions.BadRequestException;
import com.graemeyoung.server.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository{

    private static final String SQL_CREATE = "INSERT INTO ITEM (ITEM_ID, QUANTITY, IMAGE_URI, PRICE, NAME, DESCRIPTION, CATEGORY)" +
            " VALUES (NEXTVAL('ITEM_SEQ'), ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_ALL = "SELECT ITEM_ID, QUANTITY, IMAGE_URI, PRICE, NAME, DESCRIPTION, CATEGORY FROM ITEM";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM ITEM WHERE ITEM_ID = ?";
    private static final String SQL_FIND_BY_CATEGORY = "SELECT * FROM ITEM WHERE CATEGORY = ?";
    private static final String SQL_UPDATE = "UPDATE ITEM SET QUANTITY = ?, IMAGE_URI = ?, PRICE = ?, NAME = ?, DESCRIPTION = ?, CATEGORY = ? " +
            "WHERE ITEM_ID = ?";
    private static final String SQL_DELETE_ITEM = "DELETE FROM ITEM WHERE ITEM_ID = ?";
    private static final String SQL_DELETE_ALL_CART_ITEMS = "DELETE FROM CART_ITEM WHERE ITEM_ID = ?";
    private static final String SQL_DELETE_ALL_WISHLIST_ITEMS = "DELETE FROM WISHLIST_ITEM WHERE ITEM_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Integer quantity, String imageUri, Double price, String name, String description, String category) throws BadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, quantity);
                ps.setString(2, imageUri);
                ps.setDouble(3, price);
                ps.setString(4, name);
                ps.setString(5, description);
                ps.setString(6, category);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ITEM_ID");
        } catch (Exception e) {
            throw new BadRequestException("Invalid details");
        }
    }

    @Override
    public List<Item> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{}, itemRowMapper);
    }

    @Override
    public Item findById(Integer itemId) throws ResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{itemId}, itemRowMapper);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Invalid item ID");
        }
    }

    @Override
    public List<Item> findByCategory(String category) {
        return jdbcTemplate.query(SQL_FIND_BY_CATEGORY, new Object[]{category}, itemRowMapper);
    }

    @Override
    public void update(Integer itemId, Item item) throws BadRequestException {
        try {
            jdbcTemplate.update(
                    SQL_UPDATE,
                    new Object[]{
                            item.getQuantity(),
                            item.getImageUri(),
                            item.getPrice(),
                            item.getName(),
                            item.getDescription(),
                            item.getCategory(),
                            itemId
                    },
                    itemRowMapper
            );
        } catch (Exception e) {
            throw new BadRequestException("Invalid details");
        }

    }

    @Override
    public void removeById(Integer itemId) throws ResourceNotFoundException {
        try {
            this.removeAllCartItems(itemId);
            this.removeAllWishlistItems(itemId);
            jdbcTemplate.update(SQL_DELETE_ITEM, new Object[]{itemId});
        } catch (Exception e) {
            throw new ResourceNotFoundException("Incorrect Item ID");
        }

    }

    private void removeAllCartItems(Integer itemId) {
        jdbcTemplate.update(SQL_DELETE_ALL_CART_ITEMS, new Object[]{itemId});
    }

    private void removeAllWishlistItems(Integer itemId) {
        jdbcTemplate.update(SQL_DELETE_ALL_WISHLIST_ITEMS, new Object[]{itemId});
    }

    private RowMapper<Item> itemRowMapper = ((rs, rowNum) -> new Item(
            rs.getInt("ITEM_ID"),
            rs.getInt("QUANTITY"),
            rs.getString("IMAGE_URI"),
            rs.getDouble("PRICE"),
            rs.getString("NAME"),
            rs.getString("DESCRIPTION"),
            rs.getString("CATEGORY")
    ));
}
