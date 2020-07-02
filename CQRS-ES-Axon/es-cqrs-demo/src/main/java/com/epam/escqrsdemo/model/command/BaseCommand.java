package com.epam.escqrsdemo.model.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {

    // this annotation is required for Axon to determine the instance of the Aggregate that should handle the command
    @TargetAggregateIdentifier
    public final T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
