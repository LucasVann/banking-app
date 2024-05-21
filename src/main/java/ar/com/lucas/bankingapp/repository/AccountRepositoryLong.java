package ar.com.lucas.bankingapp.repository;

import ar.com.lucas.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositoryLong extends JpaRepository<Account, Long> {

    Account findByAccountHolderName(String AccountHolderName);

    @Query(
            value = "SELECT * FROM ACCOUNTS a WHERE a.balance > 200;",
            nativeQuery = true)
    List<Account> balanceAbove200();
}
