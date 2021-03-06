package oth.presentation.controller.utilisateur;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oth.presentation.controller.AbstractGlobals;

/**
 * Controlleur pour la déconnexion.
 * 
 * @author Phil9175
 * 
 */
@Controller
@RequestMapping("/deconnexion")
public class DeconnexionController {
	/**
	 * Déconnecte l'utilisateur et le redirige sur l'accueil.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String deconnect(final Model model, HttpSession session) {
		if (session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME) == null) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		session.setAttribute(AbstractGlobals.SESSION_ROLE_NAME, null);
		session.setAttribute(AbstractGlobals.SESSION_USER_NAME, null);
		session.setAttribute(AbstractGlobals.SESSION_PANIER_NAME, null);

		session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, "deconnexion.success");
		return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
	}
}
