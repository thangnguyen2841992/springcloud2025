package com.thang.account_service.controller;

import com.thang.account_service.client.INotificationService;
import com.thang.account_service.client.IStatisticService;
import com.thang.account_service.model.AccountDTO;
import com.thang.account_service.model.MessageDTO;
import com.thang.account_service.model.StatisticDTO;
import com.thang.account_service.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    IAccountService accountService;

    @Autowired
    IStatisticService statisticService;

    @Autowired
    INotificationService notificationService;

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
        statisticService.createNewStatistic(new StatisticDTO("Account " + accountDTO.getUsername() +  " is created", new Date()));
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("nguyenthang29tbdl@gmail.com");
        messageDTO.setTo(accountDTO.getUsername()); //username is Email
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Chào mừng bạn đến với Thắng đẹp trai");
        messageDTO.setContent("Cho tôi 10 tỷ nhé");

        notificationService.sendNotificationEmail(messageDTO);
        return accountDTO;
    }

    @DeleteMapping("/deleteAccount.do/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        this.accountService.deleteAccount(accountId);
    }

}

