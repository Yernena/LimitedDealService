package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Deal {
    private String id;
    private String itemName;
    private double price;
    private int maxQuantity;
    private int claimedQuantity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<String> claimedUserIds = new HashSet<>();

    public Deal(String id, String itemName, double price, int maxQuantity, int claimedQuantity, LocalDateTime startTime,
            LocalDateTime endTime, Set<String> claimedUserIds) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.maxQuantity = maxQuantity;
        this.claimedQuantity = claimedQuantity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.claimedUserIds = claimedUserIds;
    }

    public Deal(String id, String itemName, double price, int maxQuantity, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.maxQuantity = maxQuantity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getClaimedQuantity() {
        return claimedQuantity;
    }

    public void setClaimedQuantity(int claimedQuantity) {
        this.claimedQuantity = claimedQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Set<String> getClaimedUserIds() {
        return claimedUserIds;
    }

    public void setClaimedUserIds(Set<String> claimedUserIds) {
        this.claimedUserIds = claimedUserIds;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Deal{");
        sb.append("id='").append(id).append('\'');
        sb.append(", itemName='").append(itemName).append('\'');
        sb.append(", price=").append(price);
        sb.append(", maxQuantity=").append(maxQuantity);
        sb.append(", claimedQuantity=").append(claimedQuantity);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", claimedUserIds=").append(claimedUserIds);
        sb.append('}');
        return sb.toString();
    }
}
