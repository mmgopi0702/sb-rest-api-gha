package com.mgk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgk.dto.UserDto;
import com.mgk.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
		name = "CRUD REST APIs For UserResource" ,
		description = "CRUD RestAPI for Create User , Update User,  Get User, get All users and Delete User"
		) 
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	//localhost:8080/CreateUser
	@Operation(
			summary = "Create user Rest API",
			description = "Create user Rest API is used to create new User and store DB"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HttpStatus 201 CREATED"
			)
	@PostMapping("/createUser")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user) {

		UserDto newUser = userService.createUser(user);

		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	//localhost:8080/createUsers
	@Operation(
			summary = "Create users Rest API",
			description = "Create user Rest API is used to create new Users and store DB"
			)
	@ApiResponse(
			responseCode = "201",
			description = "HttpStatus 201 CREATED"
			)
	@PostMapping("/createUsers")
	public ResponseEntity<List<UserDto>> createUsers(@RequestBody List<UserDto> userDtoList){
		
		List<UserDto> users = userService.createUsers(userDtoList);
		
		return new ResponseEntity<>(users,HttpStatus.CREATED);
	}
	
	
	//localhost:8080/getUser/4
	@Operation(
			summary = "Get User Rest API",
			description = "Get user Rest API is used to get User by ID"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HttpStatus 200 SUCCESS"
			)
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long userId) {

		UserDto user = userService.getUserById(userId);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	//localhost:8080/getAllUsers
	@GetMapping("/getAllUsers")
	@Operation(
			summary = "Get All User Rest API",
			description = "Get All user Rest API is used to get all Users in DB"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HttpStatus 200 SUCCESS"
			)
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		List<UserDto> allUsers = userService.getAllUsers();
		
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
	}
	
	//localhost:8080/updateUser/4
	@Operation(
			summary = "Update User Rest API",
			description = "Update user Rest API is used to Update User by ID in DB"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HttpStatus 200 SUCCESS"
			)
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDto> updateUser(
			@PathVariable(value = "id") Long id ,@RequestBody @Valid UserDto user){
		
		user.setId(id);
		UserDto updateUser = userService.updateUser(user);
		
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
		
	}

	//localhost:8080/deleteUser/4
	@Operation(
			summary = "Delete User Rest API",
			description = "Delete user Rest API is used to Delete User by ID from the DB"
			)
	@ApiResponse(
			responseCode = "200",
			description = "HttpStatus 200 SUCCESS"
			)
	@DeleteMapping("deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id){
		
		userService.deleteUser(id);
		
		return new ResponseEntity<>("User Deleted with ID : '"+id+"' Successfylly",HttpStatus.OK);
	}
}