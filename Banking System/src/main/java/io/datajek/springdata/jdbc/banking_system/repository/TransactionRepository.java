package io.datajek.springdata.jdbc.banking_system.repository;

import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.TransactionModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionModel, String> {
//    Optional<List<TransactionModel>> findByAccountNumber(String accountNumber);
}
