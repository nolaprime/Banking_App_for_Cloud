package io.datajek.springdata.jdbc.banking_system.models;

import io.datajek.springdata.jdbc.banking_system.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class AcccountInfoModelTest {


    @Test
    public void testAcccountInfoModel_inital_values() {
        AccountInfoModel accountInfoModel = new AccountInfoModel("1", "10", "Checkings", 0f);

        assert accountInfoModel.getCustomerId().equals("1");
        assert accountInfoModel.getAccountNumber().equals("10");
        assert accountInfoModel.getBalance() == 0f;
        assert accountInfoModel.getAccountType().equals("Checkings");
    }
}
