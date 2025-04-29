package io.datajek.springdata.jdbc.banking_system.services;

import io.datajek.springdata.jdbc.banking_system.dto.AccountInfoDTO;
import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import io.datajek.springdata.jdbc.banking_system.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountServicesTest {

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void test() {
    when(accountRepository.findById("1"))
            .thenReturn(Optional.of(new AccountInfoModel("1", "10", "Checkings", 0f)));

    System.out.println(accountRepository.findById("1").get().getAccountNumber());

    assert accountRepository.findById("1").get().getAccountNumber().equals("10");
//        AccountInfoDTO accountInfoDTO ;
//        assert accountInfoDTO != null;
    }
}
