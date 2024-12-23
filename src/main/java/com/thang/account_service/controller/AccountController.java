package com.thang.account_service.controller;

import com.thang.account_service.model.AccountDTO;
import com.thang.account_service.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    IAccountService accountService;

    @GetMapping("/getAllAccount.do")
    public List<AccountDTO> getAllAccount() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/getAccDetail.do/{accountId}")
    public AccountDTO getAccDetail(@PathVariable("accountId") Long accountId) {
        return this.accountService.getAccountById(accountId);
    }

    @PostMapping("/createNewAccount.do")
    public AccountDTO createNewAccount(@RequestBody AccountDTO accountDTO) {
        this.accountService.addAccount(accountDTO);
        return accountDTO;
    }

    @DeleteMapping("/deleteAccount.do/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        this.accountService.deleteAccount(accountId);
    }

}

