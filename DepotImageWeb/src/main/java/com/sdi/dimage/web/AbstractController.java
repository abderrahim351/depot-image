package com.sdi.dimage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sdi.dimage.utils.UtilisateurSessionDto;

public abstract class AbstractController {
	
	private static final String LOGGED_USER = "logged-user";
	
	protected UtilisateurSessionDto getUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return (UtilisateurSessionDto) session
					.getAttribute(LOGGED_USER);

		}
		return null;
	}
	
	protected void createUserSession(HttpServletRequest request, UtilisateurSessionDto usd) {
		request.getSession(true).setAttribute(LOGGED_USER, usd);
	}

	protected void deleteUserSession(HttpServletRequest request) {
		
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(LOGGED_USER);
		    session.invalidate();
		}
	}
	
}
