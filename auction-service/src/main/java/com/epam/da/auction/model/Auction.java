package com.epam.da.auction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Auction {

    @Id
    private String id;

    private String name;

    private String description;

    private String type;

    private String status;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "completion_time")
    private Timestamp completionTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Timestamp completionTime) {
        this.completionTime = completionTime;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", completionTime=" + completionTime +
                '}';
    }
}
