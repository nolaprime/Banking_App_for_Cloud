package io.datajek.springdata.jdbc.banking_system.controllers;

import io.datajek.springdata.jdbc.banking_system.dto.CustomerIncomingDTO;
import io.datajek.springdata.jdbc.banking_system.dto.CustomerResponseDTO;
import io.datajek.springdata.jdbc.banking_system.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class CustomerServiceController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/request")
    public ResponseEntity<CustomerResponseDTO> request(CustomerIncomingDTO customerIncomingDTO) {
        return ResponseEntity.ok(customerService.createCustomerRequest(customerIncomingDTO).getBody());
    }

    @GetMapping("/process")
    public ResponseEntity<CustomerResponseDTO> process() {
        return ResponseEntity.ok(customerService.processRequest().getBody());
    }
}
