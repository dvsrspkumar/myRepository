package com.epam.escqrsdemo.service.command;

import com.epam.escqrsdemo.model.command.CreateAccountCommand;
import com.epam.escqrsdemo.model.command.CreditMoneyCommand;
import com.epam.escqrsdemo.model.command.DebitMoneyCommand;
import com.epam.escqrsdemo.repository.dto.AccountCreateDTO;
import com.epam.escqrsdemo.repository.dto.MoneyCreditDTO;
import com.epam.escqrsdemo.repository.dto.MoneyDebitDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    /**
     * Basically, this is a convenience interface provided by Axon.
     * In other words, you can use this interface to dispatch commands.
     * When you wire up the CommandGateway as below, Axon will actually provide the DefaultCommandGateway implementation
     */
    private final CommandGateway commandGateway;

    public AccountCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO) {
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(),
                accountCreateDTO.getStartingBalance(), accountCreateDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO) {
        return commandGateway.send(new CreditMoneyCommand(accountNumber,
                moneyCreditDTO.getCreditAmount(), moneyCreditDTO.getCurrency()));
    }

    @Override
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO) {
        return commandGateway.send(new DebitMoneyCommand(accountNumber,
                moneyDebitDTO.getDebitAmount(), moneyDebitDTO.getCurrency()));
    }
}
