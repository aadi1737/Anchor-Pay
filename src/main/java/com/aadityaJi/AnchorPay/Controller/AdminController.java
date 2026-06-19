package com.aadityaJi.AnchorPay.Controller;

import com.aadityaJi.AnchorPay.Response.ApiResponse;
import com.aadityaJi.AnchorPay.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        ApiResponse<?> response = adminService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/freeze/{userId}")
    public ResponseEntity<?> freezeUser(@PathVariable Long userId){
        ApiResponse<?> response = adminService.freezeUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/unfreeze/{userId}")
    public ResponseEntity<?> unfreezeUser(@PathVariable Long userId){
        ApiResponse<?> response = adminService.unfreezeUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}