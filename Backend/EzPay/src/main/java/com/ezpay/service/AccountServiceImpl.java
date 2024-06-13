
package com.ezpay.service;

import com.ezpay.exceptions.UserHasAccountException;
import com.ezpay.exceptions.UserNotFoundException;
import com.ezpay.model.entity.Account;
import com.ezpay.model.enums.AccountType;
import com.ezpay.repository.AccountRepository;
import com.ezpay.repository.UserRepository;
import com.ezpay.utils.AccountUtilities;
import com.ezpay.utils.dto.Account.AccountCreateDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static com.ezpay.utils.AccountUtilities.formatDate;

@Service
public class AccountServiceImpl implements AccountService
{

    private final AccountRepository accountRepository;
    private final UserService userService;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Transactional
    public Account newAccount(AccountCreateDto data)  throws UserHasAccountException{
        /*si el usuario no existe */
               userService.findUserById(data.userId()).orElseThrow(() -> new UserNotFoundException(data.userId()));
        /*Si el usuario ya tiene una cuenta creada se dispara una excepcion*/
        if (existByUserId(data.userId())
                .isPresent()) { throw new UserHasAccountException(data.userId());}
          //existByUserId(data.userId()).ifPresentOrElse(mensaje -> new UserHasAccountException(mensaje.getUserId()),()-> System.out.println("presente"));
//        String fecha = usuario.get().getBirthDate().toString();
//        // Definir un formateador para el formato de entrada
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        // Convertir la cadena a LocalDateTime
//        LocalDateTime dateTime = LocalDateTime.parse(fecha, dateTimeFormatter);
//        // Extraer solo la fecha
//        LocalDate date = dateTime.toLocalDate();
//        // Definir un formateador para el formato de salida
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        // Formatear la fecha al formato deseado
//        String formattedDate = date.format(dateFormatter);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate birthDate = LocalDate.parse(formattedDate, formatter);
//        AccountUtilities.Age age = calculateAge(birthDate);
//        if(age.getYears()<18) {throw new UnderAgeException(age.getYears());}
        Account account = Account.builder()
                .accountId(AccountUtilities.generateSecureAccountNumber())
                .accountType(AccountType.valueOf(data.accountType().getDescription().toUpperCase()))
                .cvu(AccountUtilities.generateCvuNumber())
                .dailyLimit(AccountUtilities.setLimitByAccountType(AccountType.valueOf(data.accountType().getDescription().toUpperCase())))
                .isActive(true)
                .balance(0D)
                .userId(data.userId())
                .build();

        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> existByUserId(Integer userId) {
        return accountRepository.findByUserId(userId);
    }

    //TODO: Verificar que el usuario tenga completado su perfil antes de crear una cuenta

}
