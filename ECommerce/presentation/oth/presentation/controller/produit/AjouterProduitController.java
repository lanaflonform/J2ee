package oth.presentation.controller.produit;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import oth.metier.service.ServiceResponse;
import oth.metier.service.produit.IServiceProduit;
import oth.presentation.controller.AbstractGlobals;
import oth.presentation.controller.AccessValidator;
import oth.presentation.dto.produit.bean.ProduitDto;
import oth.presentation.validator.ProduitValidator;

/**
 * Controlleur pour l'écran de l'ajout de produit.
 * 
 * @author Phil9175
 */
@Controller
@RequestMapping("/ajouterProduit")
public class AjouterProduitController {

	@Autowired
	private IServiceProduit serviceProduit;

	@Autowired
	private ProduitValidator produitValidator;

	/**
	 * Affiche la page d'ajout du produit.
	 * 
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.GET)
	String showAjoutProduit(final Model model, final HttpSession session) {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_AJOUTER_PRODUIT,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return "redirect:/" + AbstractGlobals.PAGE_ACCUEIL;
		}

		model.addAttribute("produitDto", new ProduitDto());
		return AbstractGlobals.PAGE_AJOUTER_PRODUIT;
	}

	/**
	 * Controlleur pour l'ajout d'un produit.
	 * 
	 * @param produitDto
	 *            Le DTO du produit à ajouter.
	 * @param model
	 *            Le modèle associé.
	 * @return Le nom de la JSP.
	 */
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView validerAjouterProduit(final @ModelAttribute("produitDto") ProduitDto produitDto,
			final BindingResult result, final HttpSession session, final SessionStatus sessionStatus,
			final MultipartFile file) throws IOException {
		// Check accès
		if (!AccessValidator.validateAccess(AbstractGlobals.PAGE_AJOUTER_PRODUIT,
				(String) session.getAttribute(AbstractGlobals.SESSION_ROLE_NAME))) {
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, "access.notAuthorized");
			return new ModelAndView("redirect:/" + AbstractGlobals.PAGE_ACCUEIL);
		}
		
		// Set photo
		if (!file.isEmpty()) {
			produitDto.setPhoto(file.getBytes());
		}
		// Validate product
		produitValidator.validate(produitDto, result);

		final ModelAndView model = new ModelAndView();
		// If no validation errors
		if (!result.hasErrors()) {
			final ServiceResponse serviceResponse = serviceProduit.ajouterProduit(produitDto);
			// If no service error
			if (!serviceResponse.isError()) {
				session.setAttribute(AbstractGlobals.GLOBAL_INFORMATION_MSG, serviceResponse.getMessage());
				model.addObject("produitDto", new ProduitDto());
				model.setViewName(AbstractGlobals.PAGE_AJOUTER_PRODUIT);
				return model;
			}
			session.setAttribute(AbstractGlobals.GLOBAL_ERROR_MSG, serviceResponse.getMessage());
		}
		model.addObject("produitDto", produitDto);
		model.setViewName(AbstractGlobals.PAGE_AJOUTER_PRODUIT);
		return model;
	}

}
