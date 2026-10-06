package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable Long id) {
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public Account save(@PathVariable Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        return accountService.save(account);
    }

    @PutMapping("/{customerId}")
    public Account update(@PathVariable Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);

        Account existingAccount = accountService.find(account.getId());

        if (existingAccount != null) {
            existingAccount.setAccountName(account.getAccountName());
            existingAccount.setMoneyAmount(account.getMoneyAmount());
            existingAccount.setCustomer(customer);

            return accountService.save(existingAccount);
        }

        account.setCustomer(customer);
        return accountService.save(account);
    }

    @DeleteMapping("/{id}")
    public Account remove(@PathVariable Long id) {
        accountService.find(id);
        return accountService.delete(id);
    }
}