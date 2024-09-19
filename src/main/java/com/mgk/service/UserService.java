package com.mgk.service;

import java.util.List;

import com.mgk.dto.UserDto;
import com.mgk.entity.User;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	
	public List<UserDto> createUsers(List<UserDto> userDtoList);
	
	public UserDto getUserById(Long id);
	
	public List<UserDto> getAllUsers();
	
	public UserDto updateUser(UserDto user);
	
	public void deleteUser(Long id);

}
