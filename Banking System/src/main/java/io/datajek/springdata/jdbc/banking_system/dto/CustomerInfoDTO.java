package io.datajek.springdata.jdbc.banking_system.dto;
import io.datajek.springdata.jdbc.banking_system.models.CustomerInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.CustomerProfile;
import io.datajek.springdata.jdbc.banking_system.models.LoanModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CustomerInfoDTO {
    private String id;
    @NotEmpty
    private String name;
    @Email
    @NotEmpty
    private String email;

    private CustomerProfile profile;
    private List<LoanModel> loans;

    public CustomerInfoDTO() {

    }
    public CustomerInfoDTO(String id, String name, String email, CustomerProfile profile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }

    public CustomerInfoDTO(String customerId, String customerName, String customerEmail, CustomerProfile profile, List<LoanModel> loanRequests) {
        this(customerId, customerName, customerEmail, profile);
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @Email @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty String email) {
        this.email = email;
    }

    public CustomerProfile getProfile() {
        return profile;
    }
    public void setProfile(CustomerProfile profile) {
        this.profile = profile;
    }
    public CustomerInfoModel converttoModel() {
        return new CustomerInfoModel(id, name, email, profile);
    }
}
