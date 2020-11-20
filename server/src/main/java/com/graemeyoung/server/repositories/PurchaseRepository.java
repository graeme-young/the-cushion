package com.graemeyoung.server.repositories;

import com.graemeyoung.server.domain.Purchase;

import java.util.List;

public interface PurchaseRepository {

    List<Purchase> findAllByUser(Integer userId);

    Integer create(Integer userId, Integer ItemId, Long timestamp);

    Purchase findById(Integer userId, Integer purchaseId);

}
