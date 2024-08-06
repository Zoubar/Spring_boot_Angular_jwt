package com.example.Jwt_spring_security.services;


import com.example.Jwt_spring_security.dto.SignupRequest;
import com.example.Jwt_spring_security.entities.Customer;
import com.example.Jwt_spring_security.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private  final CustomerRepository customerRepository;
    private  final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer createCustomer(SignupRequest signupRequest) {

        if (customerRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(signupRequest,customer);

        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        customer.setPassword(hashPassword);
        Customer createdCustomer = customerRepository.save(customer);
        customer.setId(createdCustomer.getId());
        return customer;

        }

}
