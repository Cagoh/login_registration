package io.github.cagoh.loginregistration_3.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.github.cagoh.loginregistration_3.dto.WelcomeDto;
import io.github.cagoh.loginregistration_3.security.JwtTokenProvider;
import io.github.cagoh.loginregistration_3.service.AuthService;
import io.github.cagoh.loginregistration_3.service.PageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
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
	private PageService pageService;
	
	// Constructor injection
//	@Autowired
	public PageController(JwtTokenProvider jwtTokenProvider, PageService pageService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.pageService = pageService;
    }
	
		@CrossOrigin(origins = "http://localhost:3000")
		@PostMapping("/welcome")
//		public ResponseEntity<String> welcome(@RequestHeader("authenticatedUser") String authenticatedUser) {
		public ResponseEntity<List<String>> welcome(@RequestBody WelcomeDto welcomeDto, @RequestHeader("token") String token) {
			System.out.println("/api/page/welcome");
			System.out.println("");
			
			// check for user credential and respective roles
			System.out.println("managerDto.authenticatedUser = " + welcomeDto.getAuthenticatedUser());
			System.out.println("token = " + token);
	        
			List<String> contents = pageService.getWelcomePageContent(welcomeDto, token);
			
			if (!contents.isEmpty()) {
				return new ResponseEntity<>(contents, HttpStatus.OK);
			} else {
		        // Return an empty list or handle the case where contents is empty
		        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
			}
		}
	
	// get request from react vite
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/manager")
//	public ResponseEntity<String> welcome(@RequestHeader("authenticatedUser") String authenticatedUser) {
	public ResponseEntity<String> manager(@RequestBody ManagerDto managerDto, @RequestHeader("token") String token) {
		System.out.println("/api/page/manager");
		System.out.println("");
		
		// check for user credential and respective roles
		System.out.println("managerDto.authenticatedUser = " + managerDto.getAuthenticatedUser());
		System.out.println("token = " + token);
		
		if (pageService.isManager(managerDto, token)) {
//			String jsxContent = "<div><h1>You're a Manager!</h1></div>";
			String jsxContent = "<div><div>You're a Manager!</div></div>";
			return new ResponseEntity<>(jsxContent, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Not a manager", HttpStatus.UNAUTHORIZED);
		}
        
	}
	
}
