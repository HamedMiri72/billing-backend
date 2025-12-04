package com.hamedTech.billing.controller;


import com.hamedTech.billing.dto.AuthRequest;
import com.hamedTech.billing.dto.AuthResponse;
import com.hamedTech.billing.service.serviceImpl.UserDetailsServiceImpl;
import com.hamedTech.billing.service.serviceImpl.UserServiceImpl;
import com.hamedTech.billing.utility.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {


    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtUtility jwtUtility;
    private final UserServiceImpl userService;



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) throws Exception {
        authenticate(authRequest.getEmail(), authRequest.getPassword());

        final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequest.getEmail());
        final String token = jwtUtility.generateToken(userDetails);

        String role = userService.getUserRole(authRequest.getEmail());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setEmail(authRequest.getEmail());
        response.setRole(role);

        return ResponseEntity.status(HttpStatus.OK).body(response);


    }

    private void authenticate(String email, String password) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        }catch (DisabledException ex){

           throw new Exception("user account is disabeled");

        }catch (BadCredentialsException ex){
            throw new Exception("password or email are invalid");
        }
    }


    @PostMapping("/encode")
    public String encodePassword(@RequestBody Map<String, String> request){
        return passwordEncoder.encode(request.get("password"));
    }
}
