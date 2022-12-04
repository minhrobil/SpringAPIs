//package com.lecongtuan.controller;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.AccessDeniedException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.lecongtuan.entity.User;
//import com.lecongtuan.repository.UserRepository;
//import com.lecongtuan.validator.UserValidator;
//
//@Controller
//@RequestMapping("admin/user")
//public class UserController {
//
//	@Autowired
//	UserRepository userRepository;
//		
//	@Autowired
//	private UserValidator userValidator;
//	
//	private static String pathname = "C:/Users/Admin/eclipse/ThucHanhJavaWeb/SpringBoot/part_4/src/main/resources/static/img/";
//	
//	@GetMapping("list")
//	public String getAllUser(HttpServletRequest request) {
//		
//		request.setAttribute("users", userRepository.findAll());
//		return "admin/user/listUser";
//	}
//	
//	@GetMapping("view")
//	public String viewUser(HttpServletRequest request,
//			@RequestParam(name = "id", required = true) int id) {
//		request.setAttribute("user", userRepository.getReferenceById(id));
//		return "admin/user/viewUser";
//	}
//	
//	@GetMapping("add")
//	public String addUser(Model model) {
//		User user = new User();
//		model.addAttribute("user", user);
//		return "admin/user/addUser";
//	}
//	
//	@PostMapping("add")
//	public String addUser( @RequestParam(name = "fileAvatar") MultipartFile fileAvatar,
//			@ModelAttribute(value = "user") User user,
//			BindingResult bindingResult) {
//		
//		userValidator.validate(user, bindingResult);
//		if(bindingResult.hasErrors())
//			return "admin/user/addUser";
//		
//		user.setAvatar(uploadFile(fileAvatar).getOriginalFilename());
//		userRepository.save(user);
//		return "redirect:/admin/user/list";
//	}
//	
//	@GetMapping("edit")
//	public String editUser(Model model,
//			@RequestParam(name = "id", required = true) int id) {
//		User user = userRepository.getReferenceById(id);
//		if( !(user.getFavourite() == null)) {
//			user.setFavourite(user.getFavourite().replace(",", ""));
//		}
//		model.addAttribute("user", user);
//		return "admin/user/editUser";
//
//	}
//	
//	@PostMapping("edit")
//	public String editUser( @RequestParam(name = "fileAvatar") MultipartFile fileAvatar,
//			@ModelAttribute(value = "user") User user,
//			BindingResult bindingResult) {
//		
//		userValidator.validate(user, bindingResult);
//		if(bindingResult.hasErrors())
//			return "admin/user/editUser";
//		
//		if(!fileAvatar.getOriginalFilename().equals("")
//				&& !fileAvatar.getOriginalFilename().equals(user.getAvatar())) {
//			new File(pathname + user.getAvatar()).delete();
//			user.setAvatar(uploadFile(fileAvatar).getOriginalFilename());
//		}
//		
//		userRepository.save(user);
//		return "redirect:/admin/user/list";
//	}
//	
//	@GetMapping(value = "delete")
//	public String deleteUser( @RequestParam(name = "id", required = true) int id) {
//		User user = userRepository.getReferenceById(id);
//		new File(pathname + user.getAvatar()).delete();
//		userRepository.delete(user);
//		return "redirect:/admin/user/list";
//	}	
//
//	private MultipartFile uploadFile(MultipartFile fileUpload) {
//		try {		
//			File file = new File(pathname + fileUpload.getOriginalFilename());
//			FileOutputStream outputStream = new FileOutputStream(file);
//			outputStream.write(fileUpload.getBytes());
//			outputStream.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return fileUpload;
//	}
//	
//	@GetMapping("download-file")
//	public void downloadFile(HttpServletRequest request, HttpServletResponse response,
//			@RequestParam(name = "image", required = true) String image) throws AccessDeniedException {
//		Path file = Paths.get(pathname, image);
//		if(Files.exists(file)) {
//			response.setContentType("image/*");
//			response.addHeader("Content-Disposition", "attachment; " + image);
//			try {
//				Files.copy(file, response.getOutputStream());
//				response.getOutputStream().flush();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		
//		}
//	}
//}
