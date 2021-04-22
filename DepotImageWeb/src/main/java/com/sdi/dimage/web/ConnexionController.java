package com.sdi.dimage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdi.dimage.services.UtilisateurService;
import com.sdi.dimage.utils.LoginModel;
import com.sdi.dimage.utils.UtilisateurSessionDto;

@RestController
@RequestMapping("/api")
public class ConnexionController {

	private static final String LOGGED_USER = "logged-user";
	
	@Autowired
	private UtilisateurService uService;

	@PostMapping("login")
	public void login(@RequestBody LoginModel login,
			HttpServletRequest request) {

		System.out.println("Login : " + login.getUsername());

		UtilisateurSessionDto usd = uService.chercherUtilisateur(login);

		request.getSession(true).setAttribute(LOGGED_USER, usd);

	}

	@PostMapping("logout")
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(LOGGED_USER);
		    session.invalidate();
		}
	}

	@GetMapping("user-session")
	public UtilisateurSessionDto userSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (UtilisateurSessionDto) session.getAttribute(LOGGED_USER);
		}
		return null;
	}

}
