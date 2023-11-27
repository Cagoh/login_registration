package io.github.cagoh.loginregistration_3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cagoh.loginregistration_3.dto.JwtAuthResponse;
import io.github.cagoh.loginregistration_3.dto.LoginDto;
import io.github.cagoh.loginregistration_3.dto.ManagerDto;
import io.github.cagoh.loginregistration_3.dto.RegisterDto;
import io.github.cagoh.loginregistration_3.security.JwtTokenProvider;
import io.github.cagoh.loginregistration_3.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class PageController {

//	private PageService pageService;
//	
//
//	// Constructor injection
//	public PageController(PageService pageService) {
//        this.pageService = pageService;
//    }
	
	private JwtTokenProvider jwtTokenProvider;
	
	// get request from react vite
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/manager")
//	public ResponseEntity<String> welcome(@RequestHeader("authenticatedUser") String authenticatedUser) {
	public ResponseEntity<String> welcome(@RequestBody ManagerDto managerDto, @RequestHeader("token") String token) {
		System.out.println("/api/page/manager");
		System.out.println("");
		
		// check for user credential and respective roles
		System.out.println("managerDto.authenticatedUser = " + managerDto.getAuthenticatedUser());
		System.out.println("token = " + token);
		
		
        return new ResponseEntity<>("Manager", HttpStatus.UNAUTHORIZED);
	}
	
}
