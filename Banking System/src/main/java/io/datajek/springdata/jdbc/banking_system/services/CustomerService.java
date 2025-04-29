package io.datajek.springdata.jdbc.banking_system.services;

import io.datajek.springdata.jdbc.banking_system.dto.CustomerIncomingDTO;
import io.datajek.springdata.jdbc.banking_system.dto.CustomerResponseDTO;
import io.datajek.springdata.jdbc.banking_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;
@Service
public class CustomerService {
    @Autowired
            private CustomerRepository customerRepository;

    Queue<CustomerIncomingDTO> customerRequestQueue = new LinkedList<>();

    public ResponseEntity<CustomerResponseDTO> createCustomerRequest(CustomerIncomingDTO customerIncomingDTO) {
        if (customerRepository.findById(customerIncomingDTO.getCustomerId()).isPresent()) {
            customerIncomingDTO.setRequestTime(LocalDate.now());
            customerRequestQueue.add(customerIncomingDTO);
            CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO("Service request added to queue.", customerIncomingDTO);
            return ResponseEntity.ok(customerResponseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<CustomerResponseDTO> processRequest() {
        if (customerRequestQueue.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        CustomerIncomingDTO customerIncomingDTO = customerRequestQueue.poll();
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO("Request processed.", customerIncomingDTO);
        return ResponseEntity.ok(customerResponseDTO);
    }
}
