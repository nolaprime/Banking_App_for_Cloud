package io.datajek.springdata.jdbc.banking_system.controllers;

import io.datajek.springdata.jdbc.banking_system.repository.UserRepository;
import io.datajek.springdata.jdbc.banking_system.services.JwtUtil;
import io.datajek.springdata.jdbc.banking_system.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private UserServices userServices;
    @Autowired JwtUtil jwtUtil;
    @Autowired private UserRepository userRepository;

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        userServices.login(userDTO.getEmail(), userDTO.getPlainPassword());

        UserModel userModel = userRepository.findByEmail(userDTO.getEmail());
        String role = userModel.getRole();
        String token = jwtUtil.generateToken(userDTO.getEmail(), role);

        return ResponseEntity.ok(new AuthResponse(token));
    }*/
}

class AuthRequest {
    private String email;
    private String plainPassword;
    public AuthRequest(String email, String plainPassword) {
        this.email = email;
        this.plainPassword = plainPassword;
    }

    public String getEmail() {
        return email;
    }
    public String getPlainPassword() {
        return plainPassword;
    }
    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

class AuthResponse {
    private String jwtToken;
    public AuthResponse(String jwtToken) { this.jwtToken = jwtToken; }

    public String getJwtToken() {
        return jwtToken;
    }

}
