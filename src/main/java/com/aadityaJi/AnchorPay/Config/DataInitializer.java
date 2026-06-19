package com.aadityaJi.AnchorPay.Config;

import com.aadityaJi.AnchorPay.Entity.UserEntity;
import com.aadityaJi.AnchorPay.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByEmail("admin@anchorpay.com").isEmpty()){
            UserEntity admin = UserEntity.builder()
                    .username("admin")
                    .email("admin@anchorpay.com")
                    .password(passwordEncoder.encode("admin"))
                    .role(UserEntity.Role.ADMIN)
                    .isActive(true)
                    .age(25)
                    .build();
            userRepository.save(admin);
        }
    }
}