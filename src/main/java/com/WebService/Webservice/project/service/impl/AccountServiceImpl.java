//package com.WebService.Webservice.project.service.impl;
//
//
//import com.WebService.Webservice.project.dto.AccountDto;
//import com.WebService.Webservice.project.dto.CustomerDto;
//import com.WebService.Webservice.project.entity.Account;
//import com.WebService.Webservice.project.entity.Customer;
//import com.WebService.Webservice.project.exception.ResourceNotFoundException;
//import com.WebService.Webservice.project.repository.AccountRepository;
//import com.WebService.Webservice.project.service.AccountService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service //To enable this class for component scanning
//public class AccountServiceImpl implements AccountService {
//
//    private AccountRepository AccountRepository;
//
//    public AccountServiceImpl(AccountRepository AccountRepository) {
//        this.AccountRepository = AccountRepository;
//    }
//
//    @Override
//    public AccountDto createAccount(AccountDto AccountDto) {
//
//        // convert DTO to entity
//        Account Account = mapToEntity(AccountDto);
//        Account newAccount = AccountRepository.save(Account);
//
//        // convert entity to DTO
//        AccountDto AccountResponse = mapToDTO(newAccount);
//        return AccountResponse;
//    }
//
//    @Override
//    public List<AccountDto> getAllAccounts() {
//        List<Account> categories = AccountRepository.findAll();
//        return categories.stream().map(Account -> mapToDTO(Account)).collect(Collectors.toList());
//    }
//
//
//
//    @Override
//    public AccountDto getAccountById(long id) {
//        Account Account = AccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
//        return mapToDTO(Account);
//    }
//
//    @Override
//    public AccountDto updateAccount(AccountDto AccountDto, long id) {
//        // get Account by id from the database
//        Account Account = AccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
//        Account.setBalance(AccountDto.getBalance());
//        Account.setType(AccountDto.getType());
//        Account.setCustomer(Account.getCustomer());
//        Account updatedAccount = AccountRepository.save(Account);
//        return mapToDTO(updatedAccount);
//    }
//
//    @Override
//    public void deleteAccountById(long id) {
//        // get Account by id from the database
//        Account Account = AccountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
//        AccountRepository.delete(Account);
//    }
//
//    // convert Entity into DTO
//    private AccountDto mapToDTO(Account Account){
//        AccountDto AccountDto = new AccountDto();
//        AccountDto.setId(Account.getId());
//
//        CustomerDto customerDto =new CustomerDto();
//        customerDto.setAge(Account.getCustomer().getAge());
//        customerDto.setName(Account.getCustomer().getName());
//        customerDto.setId(Account.getCustomer().getId());
//
//        AccountDto.setCustomer(customerDto);
//        AccountDto.setBalance(Account.getBalance());
//        AccountDto.setType(Account.getType());
//        return AccountDto;
//    }
//
//    // convert DTO to entity
//    private Account mapToEntity(AccountDto AccountDto){
//        Account Account = new Account();
//        Account.setBalance(AccountDto.getBalance());
//        Account.setType(AccountDto.getType());
//        Customer customer = new Customer();
//        customer.setId(AccountDto.getCustomer().getId());
//        Account.setCustomer(customer);
//        return Account;
//    }
//}
