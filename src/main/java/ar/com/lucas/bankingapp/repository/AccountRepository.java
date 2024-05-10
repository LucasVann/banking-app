package ar.com.lucas.bankingapp.repository;

import ar.com.lucas.bankingapp.dto.AccountDto;
import ar.com.lucas.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountHolderName(String AccountHolderName);
}
