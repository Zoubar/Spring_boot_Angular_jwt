package com.example.Jwt_spring_security.services;

import com.example.Jwt_spring_security.dto.SignupRequest;
import com.example.Jwt_spring_security.entities.Customer;

public interface AuthService {


    Customer createCustomer(SignupRequest signupRequest);

}
