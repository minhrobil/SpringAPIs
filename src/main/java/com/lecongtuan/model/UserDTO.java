package com.lecongtuan.model;

import lombok.Data;

@Data
public class UserDTO {
	private int id;
	private String avatar;
	private String name;
	private String username;
//	private String password;
	private String gender;
	private String role;
	private int access;
	private String favourite;
	private String about;
}
