package com.thang.account_service.service;

import com.thang.account_service.IAccountRepository;
import com.thang.account_service.entity.Account;
import com.thang.account_service.model.AccountDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IAccountService {
    void addAccount(AccountDTO accountDTO);
    void updateAccount(AccountDTO accountDTO);
    void updatePassword(AccountDTO accountDTO);
    void deleteAccount(Long id);
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(Long id);
}
@Transactional
@Service
class AccountServiceImpl implements IAccountService {

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void addAccount(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
//      account.setPassword();
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountRepository.findById(accountDTO.getId());
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            modelMapper.typeMap(AccountDTO.class, Account.class)
                       .addMappings(mapper -> mapper.skip(Account::setPassword)).map(accountDTO, account);
            accountRepository.save(account);
        }
    }

    @Override
    public void updatePassword(AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountRepository.findById(accountDTO.getId());
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
//            account.setPassword(accountDTO.getPassword());
            accountRepository.save(account);
        }
    }

    @Override
    public void deleteAccount(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            accountRepository.deleteById(id);
        }
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        accountRepository.findAll().forEach(account -> accountDTOS.add(modelMapper.map(account, AccountDTO.class)));
        return accountDTOS;
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return modelMapper.map(account, AccountDTO.class);
        }
        return null;
    }
}
