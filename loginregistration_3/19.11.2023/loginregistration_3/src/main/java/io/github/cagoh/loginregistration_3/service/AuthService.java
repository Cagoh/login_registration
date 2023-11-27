package io.github.cagoh.loginregistration_3.service;

import io.github.cagoh.loginregistration_3.dto.LoginDto;
import io.github.cagoh.loginregistration_3.dto.RegisterDto;

public interface AuthService {
	
	String register(RegisterDto registerDto);
	String login(LoginDto loginDto);
	
	
}
