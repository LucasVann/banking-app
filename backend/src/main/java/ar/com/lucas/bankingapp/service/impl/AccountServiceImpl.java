package ar.com.lucas.bankingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.lucas.bankingapp.dto.AccountDto;
import ar.com.lucas.bankingapp.entity.Account;
import ar.com.lucas.bankingapp.mapper.AccountMapper;
import ar.com.lucas.bankingapp.repository.AccountRepositoryLong;
import ar.com.lucas.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepositoryLong accountRepositoryLong;

    public AccountServiceImpl(AccountRepositoryLong accountRepositoryLong) {
        this.accountRepositoryLong = accountRepositoryLong;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saveAccount = accountRepositoryLong.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepositoryLong.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }


    public AccountDto getAccountByfindByAccountHolderName(String name) {
        Account account = accountRepositoryLong.findByAccountHolderName(name);//.orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Long amount) {
        Account account = accountRepositoryLong.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        account.setBalance(account.getBalance() + amount);
        return AccountMapper.mapToAccountDto(accountRepositoryLong.save(account));
    }

    @Override
    public AccountDto withdraw(Long id, Long amount) {
        Account account = accountRepositoryLong.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Error: Insufficient balance");
        } else {
            account.setBalance(account.getBalance() - amount);
        }
        return AccountMapper.mapToAccountDto(accountRepositoryLong.save(account));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepositoryLong.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepositoryLong.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        accountRepositoryLong.deleteById(id);
    }

    @Override
    public AccountDto updateName(Long id, String name) {
        Account account = accountRepositoryLong.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        account.setAccountHolderName(name);
        return AccountMapper.mapToAccountDto(accountRepositoryLong.save(account));

    }

    @Override
    public List<AccountDto> balanceAbove200() {
        List<Account> accounts = accountRepositoryLong.balanceAbove200();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }
}
