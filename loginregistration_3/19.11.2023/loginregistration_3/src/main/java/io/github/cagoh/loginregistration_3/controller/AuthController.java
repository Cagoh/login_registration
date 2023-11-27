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
	
	// login authentication
	// get request from react vite
//	@CrossOrigin(origins = "http://localhost:3000")
	
	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
		System.out.println("/api/auth/login");
		
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
	}
	
	// registration authentication
//	@CrossOrigin(origins = "http://localhost:3000")
	@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		System.out.println("/api/auth/register");
		
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
