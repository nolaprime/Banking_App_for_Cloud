package io.datajek.springdata.jdbc.banking_system.dto;

import java.time.LocalDate;

public class CustomerIncomingDTO {
    private String customerId;
    private String issueDescription;
    private LocalDate requestTime;

    public CustomerIncomingDTO() {
    }
    public CustomerIncomingDTO(String customerId, String issueDescription) {
        this.customerId = customerId;
        this.issueDescription = issueDescription;
    }
    public CustomerIncomingDTO(String customerId, String issueDescription, LocalDate requestTime) {
        this.customerId = customerId;
        this.issueDescription = issueDescription;
        this.requestTime = requestTime;
    }

    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getIssueDescription() {
        return issueDescription;
    }
    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }
    public LocalDate getRequestTime() {
        return requestTime;
    }
    public void setRequestTime(LocalDate requestTime) {
        this.requestTime = requestTime;
    }
}
