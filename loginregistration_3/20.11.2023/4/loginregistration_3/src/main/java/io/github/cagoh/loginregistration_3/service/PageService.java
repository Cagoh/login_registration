package io.github.cagoh.loginregistration_3.service;

import io.github.cagoh.loginregistration_3.dto.ManagerDto;


public interface PageService {
	boolean isManager(ManagerDto managerDto, String token);
}
	

