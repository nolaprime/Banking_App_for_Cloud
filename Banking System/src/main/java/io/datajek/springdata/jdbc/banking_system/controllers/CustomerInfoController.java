package io.datajek.springdata.jdbc.banking_system.controllers;

import io.datajek.springdata.jdbc.banking_system.dto.CustomerInfoDTO;
import io.datajek.springdata.jdbc.banking_system.models.CustomerInfoModel;
import io.datajek.springdata.jdbc.banking_system.models.CustomerProfile;
import io.datajek.springdata.jdbc.banking_system.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerInfoController {

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerInfoController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @PostMapping()
    public ResponseEntity<CustomerInfoDTO> createCustomer(@RequestBody @Valid CustomerInfoDTO customerInfoDTO){
        customerInfoDTO.setId(UUID.randomUUID().toString());
        CustomerProfile customerProfile = customerInfoDTO.getProfile();
        customerProfile.setId(UUID.randomUUID().toString());
        customerInfoDTO.setProfile(customerProfile);
        CustomerInfoModel customerInfoModel = customerInfoDTO.converttoModel();
        customerRepository.save(customerInfoModel);
        return ResponseEntity.ok(customerInfoDTO);
    }
    @GetMapping()
    public ResponseEntity<List<CustomerInfoDTO>> getAllCustomers() {
        List<CustomerInfoModel> customerInfoModels = new ArrayList<>();
        customerInfoModels = customerRepository.findAll();
        List<CustomerInfoDTO> customerInfoDTOS = new ArrayList<>();
        for (CustomerInfoModel customerInfoModel : customerInfoModels) {
            CustomerInfoDTO customerInfoDTO = customerInfoModel.converttoDTO();
            customerInfoDTOS.add(customerInfoDTO);
        }
        return ResponseEntity.ok(customerInfoDTOS);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerInfoDTO> getCustomerById(@PathVariable("customerId") String customerId){
        Optional<CustomerInfoModel> customerInfoModel = customerRepository.findById(customerId);
        if (customerInfoModel.isPresent()) {
            CustomerInfoDTO customerInfoDTO = customerInfoModel.get().converttoDTO();
            return ResponseEntity.ok(customerInfoDTO);
        }
        return ResponseEntity.notFound().build();
    }
//    @PostMapping("/customerprofile")
//    public ResponseEntity<CustomerInfoDTO> createCustomerProfile(@RequestBody CustomerInfoDTO customerInfoDTO){
//
//    }

}
