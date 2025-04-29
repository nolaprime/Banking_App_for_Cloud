package io.datajek.springdata.jdbc.banking_system.repository;

import io.datajek.springdata.jdbc.banking_system.dto.AccountInfoDTO;
import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountInfoModel, String> {

    Optional<AccountInfoModel> findAccountInfoModelByAccountNumber(String accountNumber);

}
