package com.lecongtuan.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lecongtuan.model.UserDTO;
import com.lecongtuan.service.UserService;

@RestController
@RequestMapping("admin/user")
public class UserAPIController {

	@Autowired
	UserService userService;
	
	private static String pathname = "/Users/nguyenminh/Documents/QuangMinh/Learn/Backend/Java/ThucHanhJavaWeb/part_4/src/main/resources/static/img/";

	@GetMapping("users")
	public List<UserDTO> getAllUser() {
		System.out.println("TEST2");
		return userService.getAllUsers();
	}	
	
	@GetMapping("user/{id}")
	public ResponseEntity<UserDTO> viewUser( @PathVariable(name = "id") int id) {
		return Optional.of(new ResponseEntity<UserDTO>(userService.getUser(id), HttpStatus.OK))
					.orElse(new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("user")
	public UserDTO addUser(@RequestParam(name = "fileAvatar") MultipartFile fileAvatar,
			@ModelAttribute("user") UserDTO userDto) {
		
		userDto.setAvatar(uploadFile(fileAvatar).getOriginalFilename());
		userService.addUser(userDto);
		return userDto;
	}

	@PutMapping("user")
	public ResponseEntity<UserDTO>  editUser( @RequestBody UserDTO userDto) {
		return Optional.of(new ResponseEntity<UserDTO>(userService.editUser(userDto), HttpStatus.OK))
				.orElse(new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("user/{id}")
	public void deleteUser( @PathVariable(name = "id") int id) {
		userService.deleteUser(id);
	}	
	
	private MultipartFile uploadFile(MultipartFile fileUpload) {
		try {		
			File file = new File(pathname + fileUpload.getOriginalFilename());
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(fileUpload.getBytes());
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileUpload;
	}

	@GetMapping("download-file")
	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "image", required = true) String image) throws AccessDeniedException {
		Path file = Paths.get(pathname, image);
		if(Files.exists(file)) {
			response.setContentType("image/*");
			response.addHeader("Content-Disposition", "attachment; " + image);
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
}
