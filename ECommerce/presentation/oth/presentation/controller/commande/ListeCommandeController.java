package oth.presentation.controller.commande;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oth.metier.service.ServiceResponse;
import oth.metier.service.commande.IServiceCommande;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.commande.ListeCommandeDto;
import oth.presentation.dto.utilisateur.bean.UtilisateurDto;

/**
 * Controlleur pour l'écran de la liste des commandes.
 * 
 * @author Phil9175
 * 
 */
@Controller
@RequestMapping("/listeCommande")
public class ListeCommandeController {
	
	@Autowired
	public IServiceCommande serviceCommande;
	
	/**
	 * Affiche la page de la liste des commandes.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showListeCommande(final @ModelAttribute(value = "listeCommandeDto") ListeCommandeDto listeCommandeDto,
			final Model model, final HttpSession session) {
		
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_LISTE_COMMANDE, (String) session.getAttribute(
				AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}
		
		// TODO AFFECT
		UtilisateurDto utilisateurDto = (UtilisateurDto) session.getAttribute(AbstractGlobals.SESSION_USER_NAME);
		final ServiceResponse serviceResponse = serviceCommande.getListeCommande(utilisateurDto.getId());
		if (serviceResponse.isError()) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}
		model.addAttribute("listeCommandeDto", (ListeCommandeDto) serviceResponse.getDataResult());
		return AbstractGlobals.PAGE_LISTE_COMMANDE;
	}
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
