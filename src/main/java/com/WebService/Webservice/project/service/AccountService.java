package com.WebService.Webservice.project.service;

import com.WebService.Webservice.project.dto.AccountDto;

import java.util.List;

/**
 * Why do we need interface in service layer?
 * Implementing an interface allows a class to become more formal about the behavior it promises to provide.
 * Interfaces form a contract between the class and the outside world,
 * and this contract is enforced at build time by the compiler.
 * In case you have multiple implementations you need to use qualifier annotation to specify kind of implementation you need to inject.
 * Or you can create object by yourself using class names
 *
 * In general:
 * Summarizing:
 *
 * When we talk about abstract classes we are defining characteristics of an object type; specifying what an object is.
 *
 * When we talk about an interface and define capabilities that we promise to provide, we are talking about establishing a contract about what the object can do.
 * Author: Mohammed Kharma
 */
public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    List<AccountDto> getAllAccounts();

    AccountDto getAccountById(long id);

    AccountDto updateAccount(AccountDto AccountDto, long id);

    void deleteAccountById(long id);
}
