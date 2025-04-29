package io.datajek.springdata.jdbc.banking_system.repository;

import io.datajek.springdata.jdbc.banking_system.models.AccountInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.CustomerInfoModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerInfoModel, String> {
    //Optional<CustomerInfoModel> findCustomerInfoModelByCustomerId(String accountNumber);
}
