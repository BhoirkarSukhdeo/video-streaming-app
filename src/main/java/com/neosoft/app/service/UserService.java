package com.neosoft.app.service;

import com.neosoft.app.dto.UserRequestDTO;
import com.neosoft.app.response.ResponseDTO;

public interface UserService {

	ResponseDTO saveUser(UserRequestDTO userRequestDTO);

	ResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

}
