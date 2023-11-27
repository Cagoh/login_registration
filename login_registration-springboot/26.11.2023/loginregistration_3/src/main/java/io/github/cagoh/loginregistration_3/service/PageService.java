package io.github.cagoh.loginregistration_3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.cagoh.loginregistration_3.dto.ManagerDto;
import io.github.cagoh.loginregistration_3.dto.WelcomeDto;

public interface PageService {
	boolean isManager(ManagerDto managerDto, String token);
	List<String> getWelcomePageContent(WelcomeDto welcomeDto, String token);
}
	

