package com.WebService.Webservice.project.controller;


import com.WebService.Webservice.project.dto.AccountDto;
import com.WebService.Webservice.project.exception.BadRequestException;
import com.WebService.Webservice.project.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/account")
public class AccountResource {
    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

//    @Autowired //@Autowired annotation is used for dependency injection.In spring boot application, all loaded beans are eligible for auto wiring to another bean. The annotation @Autowired in spring boot is used to auto-wire a bean into another bean.
    private AccountService accountService; //the use of interface rather than class is important for loose coupling

// Constructor based  injection
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllCategories() {
        return ResponseEntity.ok().body(accountService.getAllAccounts()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }


    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
        if (accountDto.getId() != null) {
            log.error("Cannot have an ID {}", accountDto);
            throw new BadRequestException(AccountResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@Valid @RequestBody AccountDto accountDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(accountService.updateAccount(accountDto, id), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable(name = "id") long id) {
        accountService.deleteAccountById(id);
//        return ResponseEntity.ok().headers(<add warnings....>).build();
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
