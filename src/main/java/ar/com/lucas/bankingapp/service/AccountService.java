package ar.com.lucas.bankingapp.service;

import ar.com.lucas.bankingapp.dto.AccountDto;
import ar.com.lucas.bankingapp.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, Long amount);

    AccountDto withdraw(Long id, Long amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    AccountDto updateName(Long id, String name);

    List<AccountDto> balanceAbove200();
}
