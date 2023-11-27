package io.github.cagoh.loginregistration_3.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.cagoh.loginregistration_3.dto.LoginDto;
import io.github.cagoh.loginregistration_3.dto.RegisterDto;
import io.github.cagoh.loginregistration_3.entity.Role;
import io.github.cagoh.loginregistration_3.entity.User;
import io.github.cagoh.loginregistration_3.exception.RegisterException;
import io.github.cagoh.loginregistration_3.repository.RoleRepository;
import io.github.cagoh.loginregistration_3.repository.UserRepository;
import io.github.cagoh.loginregistration_3.security.JwtTokenProvider;
import io.github.cagoh.loginregistration_3.service.AuthService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
	
	public String register(RegisterDto registerDto) {
		// TODO Auto-generated method stub
		
		// check whether username or email exists in databse
		if (userRepository.findByUsernameOrEmail(registerDto.getUsername(), registerDto.getConfirmPassword()).isPresent()) {
			throw new RegisterException("Username or Email already exists!");
		}
		
		// Create User entity to store to database
		User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        
        // Create set of roles entity to store to database
        Set<Role> roles = new HashSet<>();
        
        // default set the user role to "ROLE_USER" when registering
        Role userRole = roleRepository.findByName(registerDto.getRole());
        roles.add(userRole);
        
        user.setRoles(roles);
        
        userRepository.save(user);
        
        return "User Registered Successfully!.";
        
	}

	@Override
	public String login(LoginDto loginDto) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
		System.out.println("After Authentication");
		System.out.println("authentication");
		System.out.println(authentication);
		
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
//        System.out.println("token");
//        System.out.println(token);

        return token;
	}
}
