package ar.com.lucas.bankingapp.service.impl;

import ar.com.lucas.bankingapp.dto.AccountDto;
import ar.com.lucas.bankingapp.entity.Account;
import ar.com.lucas.bankingapp.mapper.AccountMapper;
import ar.com.lucas.bankingapp.repository.AccountRepository;
import ar.com.lucas.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }


    public AccountDto getAccountByfindByAccountHolderName(String name) {
        Account account = accountRepository.findByAccountHolderName(name);//.orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Long amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        account.setBalance(account.getBalance() + amount);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDto withdraw(Long id, Long amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Error: Insufficient balance");
        } else {
            account.setBalance(account.getBalance() - amount);
        }
        return AccountMapper.mapToAccountDto(accountRepository.save(account));
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto updateName(Long id, String name) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Error: Account does not exist"));
        account.setAccountHolderName(name);
        return AccountMapper.mapToAccountDto(accountRepository.save(account));

    }
}
