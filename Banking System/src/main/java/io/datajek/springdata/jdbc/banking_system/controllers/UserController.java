package io.datajek.springdata.jdbc.banking_system.controllers;

import io.datajek.springdata.jdbc.banking_system.dto.UserDTO;
import io.datajek.springdata.jdbc.banking_system.models.UserModel;
import io.datajek.springdata.jdbc.banking_system.repository.UserRepository;
import io.datajek.springdata.jdbc.banking_system.services.JwtUtil;
import io.datajek.springdata.jdbc.banking_system.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @Autowired private UserRepository userRepository;
    @Autowired JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid UserDTO userDTO){
        boolean success = userServices.signup(userDTO.getEmail(), userDTO.getPlainPassword());
        if (success) {
            return ResponseEntity.ok("Signup successful");
        }
        return ResponseEntity.badRequest().body("Username already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        boolean success = userServices.login(userDTO.getEmail(), userDTO.getPlainPassword());
        if(success){
                UserModel userModel = userRepository.findByEmail(userDTO.getEmail());
                String role = userModel.getRole();
                String token = jwtUtil.generateToken(userDTO.getEmail(), role);

                return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}
