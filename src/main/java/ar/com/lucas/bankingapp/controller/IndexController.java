package ar.com.lucas.bankingapp.controller;

import ar.com.lucas.bankingapp.dto.AccountDto;
import ar.com.lucas.bankingapp.entity.Account;
import ar.com.lucas.bankingapp.mapper.AccountMapper;
import ar.com.lucas.bankingapp.repository.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api/accounts")
public class IndexController {

    private AccountRepository accountRepository;

    public IndexController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/index")
    public String homePageIndex() {
        return "index";
    }

    @GetMapping("/buscar")
    public String buscarCuentaPorId(@RequestParam("name") String name, Model model) {
        Account account = accountRepository.findByAccountHolderName(name);
        AccountDto accountDto = AccountMapper.mapToAccountDto(account);
        model.addAttribute("accountDto", accountDto);
        return "resultado_busqueda";
    }
}
