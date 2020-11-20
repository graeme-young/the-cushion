package com.graemeyoung.server.repositories;

import com.graemeyoung.server.domain.WishlistItem;
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
public class WishlistItemRepositoryImpl implements WishlistItemRepository{

    private static final String SQL_CREATE = "INSERT INTO WISHLIST_ITEM (WISHLIST_ITEM_ID, USER_ID, ITEM_ID) VALUES " +
            "(NEXTVAL('WISHLIST_ITEM_SEQ'), ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM WISHLIST_ITEM WHERE USER_ID = ? AND WISHLIST_ITEM_ID = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM WISHLIST_ITEM WHERE USER_ID = ?";
    private static final String SQL_DELETE = "DELETE FROM WISHLIST_ITEM WHERE USER_ID = ? AND WISHLIST_ITEM_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Integer userId, Integer itemId) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setInt(2, itemId);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("WISHLIST_ITEM_ID");
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<WishlistItem> findAll(Integer user_id) {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{user_id}, wishlistItemRowMapper);
    }

    @Override
    public WishlistItem findById(Integer userId, Integer wishlistItemId) throws ResourceNotFoundException {
        try {
          return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, wishlistItemId}, wishlistItemRowMapper);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Wishlist item not found");
        }
    }

    @Override
    public void removeById(Integer userId, Integer wishlistItemId) throws ResourceNotFoundException {
        int count = jdbcTemplate.update(SQL_DELETE, new Object[]{userId, wishlistItemId});
        if (count == 0) {
            throw new ResourceNotFoundException("Wishlist item not found");
        }
    }

    private RowMapper<WishlistItem> wishlistItemRowMapper =((rs, rowNum) -> {
        return new WishlistItem(
                rs.getInt("WISHLIST_ITEM_ID"),
                rs.getInt("USER_ID"),
                rs.getInt("ITEM_ID")
        );
    });
}
