package com.ezpay.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.ezpay.exceptions.UserHasAccountException;
import com.ezpay.exceptions.UserNotFoundException;
import com.ezpay.model.entity.Account;
import com.ezpay.model.entity.User;
import com.ezpay.model.enums.AccountType;
import com.ezpay.repository.AccountRepository;
import com.ezpay.utils.dto.Account.AccountCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AccountServiceImplTest {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountServiceImpl accountService;

    private AccountCreateDto accountCreateDto;

    @BeforeEach
    public void setUp() {
        accountCreateDto = new AccountCreateDto(AccountType.PERSONAL,1);
    }

    @Test
    @Transactional
    public void testNewAccount_UserNotFound() throws Exception {
        // Simula que el usuario no existe
        when(userService.findUserById(accountCreateDto.userId())).thenReturn(Optional.empty());

        // Verifica que se lance la excepción correcta
        assertThrows(UserNotFoundException.class, () -> {
            accountService.newAccount(accountCreateDto);
        });

        // Verifica que el método de repositorio no se haya llamado
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @Transactional
    @DisplayName("Should pass if user has not an account alredy created.")
    public void testNewAccount_UserHasAccount() {
        // Simula que el usuario ya tiene una cuenta
        when(accountRepository.findByUserId(accountCreateDto.userId())).thenReturn(Optional.of(new Account()));
        //when(accountRepository.findByUserId(accountCreateDto.userId())).thenReturn(Optional.empty());

        // Verifica que se lance la excepción correcta
        assertThrows(UserHasAccountException.class, () -> {
            accountService.newAccount(accountCreateDto);
        });

        // Verifica que el método de repositorio no se haya llamado
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @Transactional
    @DisplayName("Should pass if the account was created.")
    public void testNewAccount_Success() throws Exception {
        // Simula que el usuario existe y no tiene una cuenta
        when(userService.findUserById(accountCreateDto.userId())).thenReturn(Optional.of(new User()));
        when(accountRepository.findByUserId(accountCreateDto.userId())).thenReturn(Optional.empty());

        // Verifica que se guarde una cuenta nueva
        Account account = new Account();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.newAccount(accountCreateDto);

        // Verifica que la cuenta se haya creado correctamente
        assertNotNull(createdAccount);
        verify(accountRepository, times(1)).save(any(Account.class));

    }
}
