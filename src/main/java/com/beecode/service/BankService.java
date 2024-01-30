package com.beecode.service;

import com.beecode.domain.BankAccount;
import com.beecode.domain.Client;
import com.beecode.domain.Currency;
import com.beecode.input.BankAccountInput;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BankService {
    Map<String, BankAccount> bankAccountMap = new LinkedHashMap<>();

    {
        bankAccountMap.put("16344720-7908-4b29-ab4f-0b0c323efc5d", BankAccount.builder()
                .id(UUID.fromString("16344720-7908-4b29-ab4f-0b0c323efc5d"))
                .currency(Currency.USD)
                .client(Client.builder()
                        .id(UUID.fromString("16344720-7908-4b29-ab4f-0b0c323efc5d"))
                        .firstName("Joe")
                        .lastName("Doe")
                        .build())
                .createdOn(ZonedDateTime.now())
                .createdAt(ZonedDateTime.now())
                .build());
        bankAccountMap.put("2ca16dec-6750-4a33-98a4-3900b592feaf", BankAccount.builder()
                .id(UUID.fromString("2ca16dec-6750-4a33-98a4-3900b592feaf"))
                .currency(Currency.USD)
                .client(Client.builder()
                        .id(UUID.fromString("2ca16dec-6750-4a33-98a4-3900b592feaf"))
                        .firstName("Jack")
                        .lastName("Black")
                        .build())
                .createdOn(ZonedDateTime.now())
                .createdAt(ZonedDateTime.now())
                .build());
        bankAccountMap.put("31351bea-91cd-454d-8339-7afc8634a6a4", BankAccount.builder()
                .id(UUID.fromString("31351bea-91cd-454d-8339-7afc8634a6a4"))
                .currency(Currency.USD)
                .client(Client.builder()
                        .id(UUID.fromString("31351bea-91cd-454d-8339-7afc8634a6a4"))
                        .firstName("Jack")
                        .lastName("Black")
                        .build())
                .createdOn(ZonedDateTime.now())
                .createdAt(ZonedDateTime.now())
                .build());
    }

    public BankAccount updateBankAccount(BankAccountInput input) {
        BankAccount bankAccount = bankAccountMap.get(input.getId().toString());
        bankAccount.getClient().setLastName(input.getLastName());
        return bankAccount;
    }

    public BankAccount getBankAccount(UUID id) {
        return bankAccountMap.get(id.toString());
    }

    public List<BankAccount> getAllBankAccounts() {
        return new ArrayList<>(bankAccountMap.values());
    }

    public List<BankAccount> getBankAccountsAfter(UUID id) {
        return bankAccountMap.values().stream().dropWhile(bankAccount -> bankAccount.getId().compareTo(id) != 1)
                .collect(Collectors.toUnmodifiableList());
    }
}
