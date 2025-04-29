package io.datajek.springdata.jdbc.banking_system.services;

import io.datajek.springdata.jdbc.banking_system.models.UserModel;
import io.datajek.springdata.jdbc.banking_system.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public boolean signup(String email, String plainPassword) {
        if(userRepository.findByEmail(email) != null) {
            return false;
        }

        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

        UserModel user = new UserModel(email, hashed, "ROLE_USER");
        userRepository.save(user);
        return true;
    }

    public boolean login(String email, String plainPassword) {
        UserModel user = userRepository.findByEmail(email);
        if(user == null) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, user.getHashedPassword());
    }
}
