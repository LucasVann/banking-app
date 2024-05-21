package ar.com.lucas.bankingapp.repository;

import ar.com.lucas.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositoryString extends JpaRepository<Account, String> {

}
