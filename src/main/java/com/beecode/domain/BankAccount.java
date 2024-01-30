package com.beecode.domain;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Value
public class BankAccount {
    UUID id;
    Client client;
    Currency currency;
    ZonedDateTime createdOn;
    ZonedDateTime createdAt;
}
