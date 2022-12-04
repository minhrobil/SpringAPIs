package com.lecongtuan.service;

import java.util.List;

import com.lecongtuan.model.UserDTO;

public interface UserService {

	void addUser(UserDTO userDto);

	UserDTO editUser(UserDTO userDto);
	
	void deleteUser(int id);
	
	List<UserDTO> getAllUsers();
	
	UserDTO getUser(int id);
}
