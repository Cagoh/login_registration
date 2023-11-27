package io.github.cagoh.loginregistration_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cagoh.loginregistration_3.dto.JwtAuthResponse;
import io.github.cagoh.loginregistration_3.dto.LoginDto;
import io.github.cagoh.loginregistration_3.dto.RegisterDto;
import io.github.cagoh.loginregistration_3.service.AuthService;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authService;
	
	// Constructor injection
	public AuthController(AuthService authService) {
        this.authService = authService;
    }
	
	// login authentication
	// get request from react vite
	@CrossOrigin(origins = "http://localhost:3000")
//	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
//	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
		System.out.println("/api/auth/login");
		
		System.out.println("loginDto.getUsernameOrEmail");
		System.out.println(loginDto.getUsernameOrEmail());
		System.out.println("loginDto.getPassword()");
		System.out.println(loginDto.getPassword());
		
//		System.out.println("String token");
        String token = authService.login(loginDto);
        System.out.println("token");
        System.out.println(token);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        
        System.out.println("After jwtAuthResponse");
//        System.out.println(jwtAuthResponse);
//        System.out.println(jwtAuthResponse.getTokenType());
//        System.out.println(jwtAuthResponse.getAccessToken());


        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
//        return new ResponseEntity<>("Nice", HttpStatus.OK);
	}
	
	// registration authentication
	@CrossOrigin(origins = "http://localhost:3000")
//	@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		System.out.println("/api/auth/register");
		
        String response = authService.register(registerDto);
        if (response.equals("User Registered Successfully!.")) {
        	return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else if (response.equals("Username or Email already exists!")) {
        	return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        
	}
}
