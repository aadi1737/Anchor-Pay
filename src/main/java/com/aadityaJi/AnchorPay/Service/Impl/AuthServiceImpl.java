package com.aadityaJi.AnchorPay.Service.Impl;

import com.aadityaJi.AnchorPay.Config.TokenStore;
import com.aadityaJi.AnchorPay.DTOs.*;
import com.aadityaJi.AnchorPay.Entity.UserEntity;
import com.aadityaJi.AnchorPay.Entity.WalletEntity;
import com.aadityaJi.AnchorPay.Exception.AlreadyUsedEmailException;
import com.aadityaJi.AnchorPay.Exception.InvalidLoginCredentialsException;
import com.aadityaJi.AnchorPay.Exception.PasswordMismatchException;
import com.aadityaJi.AnchorPay.Repository.UserRepository;
import com.aadityaJi.AnchorPay.Service.AuthService;
import com.aadityaJi.AnchorPay.Service.WalletService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder  bCryptPasswordEncoder;
    private final WalletService walletService;
    private final TokenStore tokenStore;
    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, WalletService walletService, TokenStore tokenStore){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.walletService = walletService;
        this.tokenStore = tokenStore;
    }


    @Override
    @Transactional
    public RegisterResponseDTO registerUser(RegisterRequestDTO dto) {

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new PasswordMismatchException("Wrong Credentials");
        }

        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new AlreadyUsedEmailException("These mail id is already have been used.");
        }
            UserEntity user = new UserEntity();
        user.setAge(dto.getAge());
        user.setUsername(dto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setRole(UserEntity.Role.USER);

        UserEntity saved = userRepository.save(user);

        WalletEntity walletEntity = walletService.addWallet(WalletRequestDTO.builder().uid(saved.getId()).build());
        saved.setWallet(walletEntity);
        userRepository.save(saved);

        return RegisterResponseDTO.builder().username(saved.getUsername()).build();
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new InvalidLoginCredentialsException("Invalid Login Credentials."));
        if(!bCryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new InvalidLoginCredentialsException("Invalid Login Credentials.");
        }else{
                String jwtToken = tokenStore.generateToken(user.getEmail());
                LoginResponseDTO responseDTO = new LoginResponseDTO();
                responseDTO.setRole(user.getRole());
                responseDTO.setUsername(user.getUsername());
                responseDTO.setJwtToken(jwtToken);
                responseDTO.setWalletID(user.getWallet()!=null? user.getWallet().getWalletId(): null);
                return responseDTO;
        }
    }
}
