package service;

import models.Deal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DealManagerService {

    private ConcurrentHashMap<String, Deal> deals = new ConcurrentHashMap<>();

    public Deal createDeal(String itemName, double price, int maxQuantity, LocalDateTime startTime, LocalDateTime endTime) {
        String id = UUID.randomUUID().toString();
        Deal deal = new Deal(id, itemName, price, maxQuantity, startTime, endTime);
        deals.put(id, deal);
        return deal;
    }

    public Deal endDeal(String dealId) throws Exception {
        Deal deal = deals.get(dealId);
        if(Objects.isNull(deal)) {
            throw new Exception("no such deal exists");
        }
        deal.setEndTime(LocalDateTime.now());
        return deal;
    }

    public Deal updateDeal(String dealId, int maxQuantity, LocalDateTime endTime) throws Exception {
        Deal deal = deals.get(dealId);
        if(Objects.isNull(deal)) {
            throw new Exception("no such deal exists");
        }
        if(LocalDateTime.now().isAfter(endTime)) {
            throw new Exception("deal has already ended at " + deal.getEndTime());
        }
        deal.setMaxQuantity(maxQuantity);
        deal.setEndTime(endTime);
        return deal;
    }

    public Deal claimDeal(String dealId, String userId) throws Exception {

        Deal deal =  deals.get(dealId);
        if(LocalDateTime.now().isAfter(deal.getEndTime())) {
            throw new Exception("Deal has ended");
        }
        if(deal.getClaimedQuantity()>=deal.getMaxQuantity()) {
            throw new Exception("stock not available");
        }
        if(deal.getClaimedUserIds().contains(userId)) {
            throw new Exception("user has already claimed");
        }
        deal.getClaimedUserIds().add(userId);
        deal.setClaimedQuantity(deal.getClaimedQuantity() + 1);
        return deal;
    }


}
