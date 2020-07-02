package com.epam.escqrsdemo.model.event;

import com.epam.escqrsdemo.model.Status;

public class AccountHeldEvent extends BaseEvent<String> {

    public final Status status;

    public AccountHeldEvent(String id, Status status) {
        super(id);
        this.status = status;
    }
}
