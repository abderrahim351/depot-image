package com.sdi.dimage.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdi.dimage.utils.LoginModel;

@RestController
@RequestMapping("/api")
public class UtilisateurController {

	@PostMapping("/login")
	public void login(@RequestBody LoginModel login, HttpServletRequest request) {
		
		System.out.println("Login : "+login.getUsername());
		
		if (request.getSession().getAttribute("logged-user")!=null) {
			System.err.println(" user already logged");
			
			LoginModel lm =  (LoginModel) request.getSession().getAttribute("logged-user");
			
			if (lm.getUsername().equals(login.getUsername())) {
				System.out.println("Vous etes deja connecté");
			}
			
		}else {
			request.getSession(true).setAttribute("logged-user", login);
			System.out.println("Vous etes connecté");
		}
		
		System.out.println("request : "+request.toString());
		
	}
	
}
