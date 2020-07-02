package com.epam.escqrsdemo.model.event;

import com.epam.escqrsdemo.model.Status;

public class AccountActivatedEvent extends BaseEvent<String> {

    public final Status status;

    public AccountActivatedEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
