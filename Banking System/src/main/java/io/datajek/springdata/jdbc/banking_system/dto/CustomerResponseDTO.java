package io.datajek.springdata.jdbc.banking_system.dto;

public class CustomerResponseDTO {
    private String message;
    private CustomerIncomingDTO incoming;

    public CustomerResponseDTO(String message, CustomerIncomingDTO incoming) {
        this.message = message;
        this.incoming = incoming;
    }

    public String getMessage() {
        return message;
    }
    public CustomerIncomingDTO getIncoming() {
        return incoming;
    }
    public void setIncoming(CustomerIncomingDTO incoming) {
        this.incoming = incoming;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
