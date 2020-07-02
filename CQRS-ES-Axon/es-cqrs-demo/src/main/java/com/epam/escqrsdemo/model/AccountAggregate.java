package com.epam.escqrsdemo.model;

import com.epam.escqrsdemo.model.command.CreateAccountCommand;
import com.epam.escqrsdemo.model.command.CreditMoneyCommand;
import com.epam.escqrsdemo.model.command.DebitMoneyCommand;
import com.epam.escqrsdemo.model.event.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * This is an Axon managed Event sourced Entity
 *
 * @Aggregate annotation tells Axon that this entity will be managed by Axon.
 *            Basically, this is similar to @Entity annotation available with JPA.
 *            However, we will be using the Axon recommended annotation.
 * @AggregateIdentifier annotation is used for the identifying a particular instance of the Aggregate.
 *                      In other words, this is similar to JPA’s @Id annotation.
 */
@Aggregate // Equal to JPAs @Entity
public class AccountAggregate {

    @AggregateIdentifier // Equal to JPAs @Id
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

    /**
     * No-Args constructor is mandatory for Axon implementation.
     * Basically, using this no-args constructor, Axon creates an empty instance of the aggregate.
     * Then, it applies the events. If this constructor is not present, it will result in an exception
     */
    public AccountAggregate() {
    }

    /**
     * This method represents commands so annotated with @CommandHandler
     * These type of command methods use AggregateLifecyle.apply() method to register events.
     *
     * @param createAccountCommand
     */
    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance, createAccountCommand.currency));
        }

    /**
     * This method represents events so annotated with @EventSourcingHandler
     *
     * important point to keep in mind is that the Aggregate Identifier must
     * be set in the first method annotated with @EventSourcingHandler.
     * In other words, this will be the creation Event.
     *
     * @param
     */
    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent){
        this.id = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(Status.CREATED);

        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
    }

    @EventSourcingHandler
    protected void on(AccountActivatedEvent accountActivatedEvent){
        this.status = String.valueOf(accountActivatedEvent.status);
    }

    @CommandHandler
    protected void on(CreditMoneyCommand creditMoneyCommand){
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.id, creditMoneyCommand.creditAmount, creditMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent){

        if (this.accountBalance < 0 & (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0){
            AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
        }

        this.accountBalance += moneyCreditedEvent.creditAmount;
    }

    @CommandHandler
    protected void on(DebitMoneyCommand debitMoneyCommand){
        AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.id, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(MoneyDebitedEvent moneyDebitedEvent){

        if (this.accountBalance >= 0 & (this.accountBalance - moneyDebitedEvent.debitAmount) < 0){
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
        }

        this.accountBalance -= moneyDebitedEvent.debitAmount;

    }

    @EventSourcingHandler
    protected void on(AccountHeldEvent accountHeldEvent){
        this.status = String.valueOf(accountHeldEvent.status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
