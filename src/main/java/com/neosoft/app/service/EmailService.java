package com.neosoft.app.service;

import com.neosoft.app.dto.EmailDetailsDTO;

public interface EmailService {
	
	String sendSimpleMail(EmailDetailsDTO details);

}
