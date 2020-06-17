package com.epam.da.auction.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuctionItem {

    @Id
    private String id;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AuctionItem{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
