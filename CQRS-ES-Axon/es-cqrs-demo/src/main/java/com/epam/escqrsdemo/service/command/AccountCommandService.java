package com.epam.escqrsdemo.service.command;

import com.epam.escqrsdemo.repository.dto.AccountCreateDTO;
import com.epam.escqrsdemo.repository.dto.MoneyCreditDTO;
import com.epam.escqrsdemo.repository.dto.MoneyDebitDTO;

import java.util.concurrent.CompletableFuture;

/**
 * Service to handle commands
 */
public interface AccountCommandService {

    public CompletableFuture<String> createAccount(AccountCreateDTO accountCreateDTO);
    public CompletableFuture<String> creditMoneyToAccount(String accountNumber, MoneyCreditDTO moneyCreditDTO);
    public CompletableFuture<String> debitMoneyFromAccount(String accountNumber, MoneyDebitDTO moneyDebitDTO);
}
