package io.github.cagoh.loginregistration_3.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.cagoh.loginregistration_3.dto.ManagerDto;
import io.github.cagoh.loginregistration_3.repository.RoleRepository;
import io.github.cagoh.loginregistration_3.repository.UserRepository;
import io.github.cagoh.loginregistration_3.security.JwtTokenProvider;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PageServiceImpl {
	
	private JwtTokenProvider jwtTokenProvider;
	
	boolean isManager(ManagerDto managerDto, String token) {
		
		boolean validated = jwtTokenProvider.validateToken(token);
		
		
		return validated;
	}
}
