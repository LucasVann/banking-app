package ar.com.lucas.bankingapp.controller;

import ar.com.lucas.bankingapp.dto.AccountDto;
import ar.com.lucas.bankingapp.entity.Account;
import ar.com.lucas.bankingapp.mapper.AccountMapper;
import ar.com.lucas.bankingapp.repository.AccountRepositoryLong;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/api/accounts")
public class IndexController {

    private AccountRepositoryLong accountRepositoryLong;

    public IndexController(AccountRepositoryLong accountRepositoryLong) {
        this.accountRepositoryLong = accountRepositoryLong;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String indexName() {
        return "index";
    }

    @GetMapping("/homePage")
    public String homePage() {
        return "home_page";
    }

    @GetMapping("/sing_up")
    public String singUp() {
        return "sing_up";
    }

    @GetMapping("/sing_in")
    public String singIn() {
        return "sing_in";
    }

    @GetMapping("/buscar")
    public String buscarCuentaPorId(@RequestParam("name") String name, Model model) {
        Account account = accountRepositoryLong.findByAccountHolderName(name);
        AccountDto accountDto = AccountMapper.mapToAccountDto(account);
        model.addAttribute("accountDto", accountDto);
        return "resultado_busqueda";
    }

    @PostMapping("/create_user")
    public String createUserForm(AccountDto accountDto, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/accounts/create";
        restTemplate.postForObject(url, accountDto,String.class);
        model.addAttribute("mensaje", "Usuario creado exitosamente");
        return "confirm_sing_up";
    }
}
