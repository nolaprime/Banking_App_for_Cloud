package io.datajek.springdata.jdbc.banking_system.models;

import io.datajek.springdata.jdbc.banking_system.dto.AccountInfoDTO;
import io.datajek.springdata.jdbc.banking_system.dto.CustomerInfoDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CustomerInfoModel {
    @Id
    private String id;
    private String name;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "customer_profile_id")
    private CustomerProfile profile;
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanModel> loans = new ArrayList<>();

    public CustomerInfoModel() {

    }
    public CustomerInfoModel(String id, String name, String email, CustomerProfile profile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public String getCustomerId() {
        return id;
    }
    public CustomerInfoDTO converttoDTO() {
        return new CustomerInfoDTO(this.getCustomerId(), this.getCustomerName(), this.getCustomerEmail(), this.getProfile(), this.getLoanRequests());
    }

    public void setCustomerId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return name;
    }

    public void setCustomerName(String customerName) {
        this.name = customerName;
    }

    public String getCustomerEmail() {
        return email;
    }

    public void setCustomerEmail(String email) {
        this.email = email;
    }

    /*public String getCustomerPhone() {
        return phone;
    }

    public void setCustomerPhone(String phone) {
        this.phone = phone;
    }*/
    public CustomerProfile getProfile() {
        return profile;
    }
    public void setProfile(CustomerProfile profile) {
        this.profile = profile;
    }
    public List<LoanModel> getLoanRequests() {
        return loans;
    }
    public void setLoanRequests(List<LoanModel> loans) {
        this.loans = loans;
    }
}

