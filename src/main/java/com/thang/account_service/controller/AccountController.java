package com.thang.account_service.controller;

import com.thang.account_service.client.INotificationService;
import com.thang.account_service.client.IStatisticService;
import com.thang.account_service.model.AccountDTO;
import com.thang.account_service.model.MessageDTO;
import com.thang.account_service.model.StatisticDTO;
import com.thang.account_service.service.IAccountService;
import jakarta.annotation.security.PermitAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    IAccountService accountService;

    @Autowired
    IStatisticService statisticService;

    @Autowired
    INotificationService notificationService;
    Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAllAccount.do")
    public List<AccountDTO> getAllAccount() {
        logger.info("Account Service Controller: get all accounts");
//        statisticService.createNewStatistic(new StatisticDTO("Get all accounts", new Date()));
        return accountService.getAllAccounts();
    }
    //hasAuthority can chu ROLE_
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getAccDetail.do/{accountId}")
    public AccountDTO getAccDetail(@PathVariable("accountId") Long accountId) {
        return this.accountService.getAccountById(accountId);
    }

    @PostMapping("/register.do")
    @PermitAll
    public AccountDTO register(@RequestBody AccountDTO accountDTO, @RequestHeader("Authorization") String bearerToken) {
        accountDTO.setRoles(new HashSet<String>(List.of("ROLE_USER")));
        addNewAcc(accountDTO);
        return accountDTO;
    }

    @PostMapping("/createNewAccount.do")
    @PreAuthorize("hasAnyAuthority('SCOPE_read') && hasRole('ADMIN')")
    public AccountDTO createNewAccount(@RequestBody AccountDTO accountDTO, @RequestHeader("Authorization") String bearerToken) {
        addNewAcc(accountDTO);
        return accountDTO;
    }
//hasRole bo chu ROLE_
    @PreAuthorize("hasAnyAuthority('SCOPE_write') && hasRole('ADMIN')")
    @DeleteMapping("/deleteAccount.do/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        this.accountService.deleteAccount(accountId);
    }
    private void addNewAcc(AccountDTO accountDTO) {
        this.accountService.addAccount(accountDTO);
        statisticService.createNewStatistic(new StatisticDTO("Account " + accountDTO.getUsername() + " is created", new Date()));
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("nguyenthang29tbdl@gmail.com");
        messageDTO.setTo(accountDTO.getUsername()); //username is Email
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Chào mừng bạn đến với Thắng đẹp trai");
        messageDTO.setContent("Cho tôi 10 tỷ nhé");

        notificationService.sendNotificationEmail(messageDTO);
    }

}

