package io.datajek.springdata.jdbc.banking_system.repository;

import io.datajek.springdata.jdbc.banking_system.models.LoanModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<LoanModel, String>{

}

