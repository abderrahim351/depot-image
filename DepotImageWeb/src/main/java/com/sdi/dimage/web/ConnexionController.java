package com.sdi.dimage.web;

import javax.servlet.http.HttpServletRequest;

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
public class ConnexionController extends AbstractController {

	@Autowired
	private UtilisateurService uService;

	@PostMapping("login")
	public void login(@RequestBody LoginModel login,
			HttpServletRequest request) {

		System.out.println("Login : " + login.getUsername());

		UtilisateurSessionDto usd = uService.chercherUtilisateur(login);
		System.out.println("1" + usd.toString());

		createUserSession(request, usd);

	}

	@PostMapping("logout")
	public void logout(HttpServletRequest request) {

		deleteUserSession(request);
	}

	@GetMapping("user-session")
	public UtilisateurSessionDto userSession(HttpServletRequest request) {
		return getUserSession(request);
	}

}
