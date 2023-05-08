package com.neosoft.app.serviceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.neosoft.app.dto.UserRequestDTO;
import com.neosoft.app.entity.User;
import com.neosoft.app.exception.InvalidInputFoundException;
import com.neosoft.app.exception.UserAlreadyExistsException;
import com.neosoft.app.repository.UserRepository;
import com.neosoft.app.response.ResponseDTO;
import com.neosoft.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Override
	public ResponseDTO saveUser(UserRequestDTO userRequestDTO) {
		validateUser(userRequestDTO.getUsername());
		User user = User.builder().build();
		BeanUtils.copyProperties(userRequestDTO, user);
		userRepository.save(user);
		return new ResponseDTO(user.getId(), "User created successfully..!");
	}

	@Override
	public ResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
		User user = userRepository.findById(userId).orElseThrow(()->new InvalidInputFoundException("User not found with given id :: "+userId));
		BeanUtils.copyProperties(userRequestDTO, user, "password");
		userRepository.save(user);
		
		return new ResponseDTO(user.getId(), "User updated successfully..!");
	}

	private void validateUser(String userName) {
		Optional<User> existsUser = userRepository.findByUsername(userName);
		if (existsUser.isPresent()) {
			throw new UserAlreadyExistsException("User already exist with the same username : " + userName);
		}
	}

}
