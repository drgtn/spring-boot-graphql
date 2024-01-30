package com.beecode.controller;

import com.beecode.cursor.CursorUtil;
import com.beecode.domain.BankAccount;
import com.beecode.domain.Currency;
import com.beecode.input.BankAccountInput;
import com.beecode.service.BankService;
import graphql.relay.DefaultConnection;
import graphql.relay.DefaultEdge;
import graphql.relay.DefaultPageInfo;
import graphql.relay.Edge;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.util.annotation.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @QueryMapping
    public BankAccount getBankAccount(@Argument UUID id) {
        log.info("Retrieving bank  account id: {}", id);
        return bankService.getBankAccount(id);
    }

    @QueryMapping
    public List<BankAccount> getAllBankAccounts() {
        log.info("Retrieving all bank  accounts");
        return bankService.getAllBankAccounts();
    }


    @MutationMapping
    public BankAccount updateBankAccount(@Argument BankAccountInput input, DataFetchingEnvironment dataFetchingEnvironment) {
        log.info("Updating bank account with id: {}", input.getId());
        return bankService.updateBankAccount(input);
    }

    @SchemaMapping
    public Currency currency(BankAccount bankAccount) {
        log.info("Retrieving currency by bank account");
        return Currency.USD;
    }

    @QueryMapping
    public DefaultConnection getPaginatedBankAccounts(@Argument int first, @Argument @Nullable String after) {
        List<Edge<BankAccount>> edges = getBankAccounts(after).stream()
                .map(bankAccount ->
                        new DefaultEdge<>(bankAccount,
                                CursorUtil.createCursorWith(bankAccount.getId())
                        )).collect(Collectors.toUnmodifiableList());

        DefaultPageInfo pageInfo = new DefaultPageInfo(CursorUtil.getFirstCursorFrom(edges),
                CursorUtil.getLastCursorFrom(edges),
                after != null,
                edges.size() >= first);
        return new DefaultConnection(edges, pageInfo);
    }

    public List<BankAccount> getBankAccounts(String cursor) {
        if (cursor == null) {
            return bankService.getAllBankAccounts();
        }
        return bankService.getBankAccountsAfter(CursorUtil.decodeCursorWith(cursor));
    }
//    /**
//     * 1. Call multiple services
//     * 2. Call another graphql server
//     * 3 call service that return partial response
//     **/
//    @SchemaMapping(typeName = "BankAccount", field = "client")
//    public DataFetcherResult<Client> client1(BankAccount bankAccount) {
//        log.info("Retrieving client by bank account");
//        return DataFetcherResult.<Client>newResult()
//                .data(Client.builder().id(UUID.randomUUID()).firstName("Joe").lastName("Doe").build())
//                .build();
//    }

//    /**
//     * Async call
//     **/
//    @SchemaMapping
//    public CompletableFuture<Client> client(BankAccount bankAccount) {
//        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//
//        log.info("Retrieving client by bank account");
//        return CompletableFuture.supplyAsync(
//                () -> {
//                    return Client.builder().id(UUID.randomUUID()).firstName("Joe2").lastName("Doe2").build();
//                },
//                executorService);
//    }


}
