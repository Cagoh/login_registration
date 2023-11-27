package io.github.cagoh.loginregistration_3.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.cagoh.loginregistration_3.dto.ManagerDto;
import io.github.cagoh.loginregistration_3.dto.WelcomeDto;
import io.github.cagoh.loginregistration_3.repository.RoleRepository;
import io.github.cagoh.loginregistration_3.repository.UserRepository;
import io.github.cagoh.loginregistration_3.security.JwtAuthenticationFilter;
import io.github.cagoh.loginregistration_3.security.JwtTokenProvider;
import io.github.cagoh.loginregistration_3.service.PageService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PageServiceImpl implements PageService{
	
//	private UserRepository userRepository;
//	private RoleRepository roleRepository;
//	private PasswordEncoder passwordEncoder;
//    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public boolean isManager(ManagerDto managerDto, String token) {
		
		boolean validated = jwtTokenProvider.validateTokenManager(token);
		System.out.println("validated = " + validated);
		
		if (validated) {
			List<String> roles = jwtTokenProvider.getRoles(token);
			System.out.println("roles");
			System.out.println(roles);
			
			// Populate the Map with role information
	        for (String role : roles) {
	        	if (role.equals("ROLE_MANAGER")) {
	        		return true;
	        	}
	        }
		}
		return false;
		
		
		
		//return validated;
	}
	
	public List<String> getWelcomePageContent(WelcomeDto welcomeDto, String token) {
		
		// validate the JWT token
		boolean validated = jwtTokenProvider.validateTokenManager(token);
		System.out.println("validated = " + validated);
		
		List<String> contents = new ArrayList<>();
		
		if (validated) {
			/**
			 * List of String
			 * Name
			 * Username
			 * Roles
			 */
			
			//String username = jwtTokenProvider.getUsername(token);
			
			contents.add(welcomeDto.getAuthenticatedUser());
			contents.add(jwtTokenProvider.getUsername(token));
			
			List<String> roles = jwtTokenProvider.getRoles(token);
			
	        for (String role : roles) {
	        	contents.add(role);
	        }
	        //return contents;
		}
		return contents;
		
	}
}
