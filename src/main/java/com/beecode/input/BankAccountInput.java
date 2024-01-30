package com.beecode.input;

import lombok.Data;

import java.util.UUID;

@Data
public class BankAccountInput {
    UUID id;
    String lastName;
    int age;
}
