package com.epam.escqrsdemo.service.event;

import com.epam.escqrsdemo.repository.dto.AccountQueryEntity;
import com.epam.escqrsdemo.repository.repo.AccountRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This implementation of Query service is not mandatory but created for testing the events.
 */
@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    /**
     * This is the Axon Event Store
     * EventStore provides a method to read events for a particular AggregateId
     */
    private final EventStore eventStore;

    private final AccountRepository accountRepository;

    public AccountQueryServiceImpl(EventStore eventStore, AccountRepository accountRepository) {

        this.eventStore = eventStore;
        this.accountRepository = accountRepository;
    }

    /**
     *  we call the readEvents() method with the AggregateId (or Account#) as input.
     *  Then, we collect the output stream and transform it to a list
     * @param accountNumber
     * @return
     */
    @Override
    public List<Object> listEventsForAccount(String accountNumber) {
        return eventStore.readEvents(accountNumber).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
    }

    @Override
    public AccountQueryEntity getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).get();
    }
}
