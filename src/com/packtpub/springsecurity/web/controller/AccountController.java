package com.packtpub.springsecurity.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.packtpub.springsecurity.security.IChangePassword;

/**
 * Used to service account requests.
 * 
 * @author Mularien
 */
@Controller
public class AccountController extends BaseController {
	
	@Autowired
	private JdbcUserDetailsManager userDetailsManager;
	
	@RequestMapping("/account/home.do")
	public void accountHome() {		
	}
	
	@RequestMapping(value="/account/changePassword.do",method=RequestMethod.GET)
	public void showChangePasswordPage() {		
	}
	
	@RequestMapping(value="/account/changePassword.do",method=RequestMethod.POST)
	public String submitChangePasswordPage(@RequestParam("oldpassword") String oldPassword, 
			@RequestParam("password") String newPassword) {
		userDetailsManager.changePassword(oldPassword, newPassword);
		GrantedAuthority ga = new GrantedAuthorityImpl("ROLE_USER");
		List list = new ArrayList();
		list.add(ga);	
		userDetailsManager.createUser(new User("guest2", "guest2", true, true, true, true, list));
		userDetailsManager.addUserToGroup("guest2", "USERS");
		SecurityContextHolder.clearContext();
		
		return "redirect:home.do";
	}

}
