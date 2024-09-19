package com.mgk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgk.dto.UserDto;
import com.mgk.entity.User;
import com.mgk.exception.EmailAlreadyExist;
import com.mgk.exception.ResourceNotFoundException;
import com.mgk.mapper.UserMapper;
import com.mgk.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	public UserDto createUser(UserDto userDto) {
		
		
		Optional<User> optional = userRepo.findByEmail(userDto.getEmail());
		
		if(optional.isPresent()) {
			
			throw new EmailAlreadyExist("Email Already Exist...!");
		}

		User user = UserMapper.mapToUser(userDto);

		User savedUser = userRepo.save(user);

		// UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

		return savedUserDto;
	}


	@Override
	public List<UserDto> createUsers(List<UserDto> userDtoList) {

		// List<User> userList = userDtoList.stream()
		// .map(UserMapper::mapToUser).collect(Collectors.toList());
		

		List<User> userList = userDtoList.stream().map(userDto -> modelMapper.map(userDto, User.class))
				.collect(Collectors.toList());

		List<User> users = userRepo.saveAll(userList);

		// List<UserDto> usersDto = users.stream()
		// .map(UserMapper::mapToUserDto).collect(Collectors.toList());

		List<UserDto> usersDto = users.stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());

		return usersDto;
	}

	
	@Override
	public UserDto getUserById(Long id) {

		User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));

		return modelMapper.map(user, UserDto.class);
		/*
		 * Optional<User> optional = userRepo.findById(id);
		 * 
		 * if(optional.isPresent()) {
		 * 
		 * User user = optional.get();
		 * 
		 * return modelMapper.map(user, UserDto.class); }else {
		 * 
		 * return null;
		 * 
		 * 
		 * }
		 * 
		 * 
		 */
		// return UserMapper.mapToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> allUsers = userRepo.findAll();

		// List<UserDto> listUserDto = allUsers.stream()
		// .map(UserMapper::mapToUserDto).collect(Collectors.toList());

		List<UserDto> listUserDto = allUsers.stream().map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());

		return listUserDto;
	}

	@Override
	public UserDto updateUser(UserDto user) {

		// User existingUser = userRepo.findById(user.getId()).get();

		User existingUser = userRepo.findById(user.getId()).orElseThrow(

				() -> new ResourceNotFoundException("User", "ID", user.getId()));

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());

		User updatedUser = userRepo.save(existingUser);

		return modelMapper.map(updatedUser, UserDto.class);

		// return UserMapper.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		
		User user = userRepo.findById(id).orElseThrow(
				
				() -> new ResourceNotFoundException("User", "ID", id)
				);

		userRepo.deleteById(id);
		
		
		/*
		Optional<User> optional = userRepo.findById(id);
		
		if(optional.isPresent()) {
			
			userRepo.deleteById(id);
			
			return "User deleted with Id " +id+" Successfully";
			
		}else {
			
			return "User not exist with Id "+id;
		}*/
		
	}
	
	
}
