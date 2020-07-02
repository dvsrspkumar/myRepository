package com.epam.escqrsdemo.service.event;

import com.epam.escqrsdemo.repository.dto.AccountQueryEntity;

import java.util.List;

/**
 * Service to handle the query layer
 * The query service will just help in fetching a list of events.
 */
public interface AccountQueryService {
    public List<Object> listEventsForAccount(String accountNumber);

    public AccountQueryEntity getAccount(String accountNumber);
}
