package com.neosoft.app.service;

import com.neosoft.app.dto.TopupDTO;
import com.neosoft.app.response.ResponseDTO;

public interface TopUpService {
	
	ResponseDTO addTopUp(TopupDTO topupDTO);

}
